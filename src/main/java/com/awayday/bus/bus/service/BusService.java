package com.awayday.bus.bus.service;

import com.awayday.bus.bus.model.BusApiModel;
import com.awayday.bus.util.ApiUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BusService {
    private final WebClient openBusApiClient;

    private final ObjectMapper objectMapper;

    private final String decodedKey;

    public BusService(@Value("${openApi.busStation.key}") String apiKey,
                          @Autowired WebClient openBusApiClient,
                          @Autowired ObjectMapper objectMapper) {
        this.decodedKey = ApiUtil.getDecodedKey(apiKey);
        this.openBusApiClient = openBusApiClient;
        this.objectMapper = objectMapper;
    }

    public Flux<BusApiModel> requestArriveBus(String cityCode, String nodeId) {
        return requestArriveBusJson(cityCode, nodeId).map(s -> {
            try {
                JsonNode response = objectMapper.readTree(s).get("response");
                System.out.println(response.toString());
                if (ApiUtil.isResponseSuccess(response)) {
                    JsonNode body = response.get("body").get("items").get("item");
                    if (body.getNodeType() == JsonNodeType.ARRAY) {
                        BusApiModel[] array = objectMapper.readValue(body.toString(), BusApiModel[].class);
                        return new ArrayList<>(Arrays.asList(array));
                    } else {
                        BusApiModel model = objectMapper.readValue(body.toString(), BusApiModel.class);
                        List<BusApiModel> list = new ArrayList<>();
                        list.add(model);
                        return list;
                    }
                } else {
                    return new ArrayList<BusApiModel>();
                }
            } catch (IOException e) {
                return new ArrayList<BusApiModel>();
            }
        }).flatMapMany(Flux::fromIterable);
    }

    private Mono<String> requestArriveBusJson(String cityCode, String nodeId) {
        return openBusApiClient.get()
                .uri(builder -> builder
                        .path("/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList")
                        .queryParam("ServiceKey", decodedKey)
                        .queryParam("cityCode", cityCode)
                        .queryParam("nodeId", nodeId)
                        .build())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(String.class);
    }
}

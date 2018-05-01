package com.awayday.bus.station.service;

import com.awayday.bus.bus.model.BusApiModel;
import com.awayday.bus.station.model.StationApiModel;
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
public class StationService {

    private final WebClient openBusApiClient;

    private final ObjectMapper objectMapper;

    private final String decodedKey;

    public StationService(@Value("${openApi.busStation.key}") String apiKey,
                          @Autowired WebClient openBusApiClient,
                          @Autowired ObjectMapper objectMapper) {
        this.decodedKey = ApiUtil.getDecodedKey(apiKey);
        this.openBusApiClient = openBusApiClient;
        this.objectMapper = objectMapper;
    }

    public Flux<StationApiModel> requestLocationStation(double gpsLati, double gpsLong) {
        return requestLocationStationJson(gpsLati, gpsLong).map(s -> {
            try {
                JsonNode response = objectMapper.readTree(s).get("response");
                System.out.println(response.toString());
                if (ApiUtil.isResponseSuccess(response)) {
                    JsonNode body = response.get("body").get("items").get("item");
                    if (body.getNodeType() == JsonNodeType.ARRAY) {
                        StationApiModel[] array = objectMapper.readValue(body.toString(), StationApiModel[].class);
                        return new ArrayList<>(Arrays.asList(array));
                    } else {
                        StationApiModel model = objectMapper.readValue(body.toString(), StationApiModel.class);
                        List<StationApiModel> list = new ArrayList<>();
                        list.add(model);
                        return list;
                    }
                } else {
                    return new ArrayList<StationApiModel>();
                }
            } catch (IOException e) {
                return new ArrayList<StationApiModel>();
            }
        }).flatMapMany(Flux::fromIterable);
    }

    private Mono<String> requestLocationStationJson(double gpsLati, double gpsLong) {
        return openBusApiClient.get()
                .uri(builder -> builder
                        .path("/BusSttnInfoInqireService/getCrdntPrxmtSttnList")
                        .queryParam("ServiceKey", decodedKey)
                        .queryParam("gpsLati", gpsLati)
                        .queryParam("gpsLong", gpsLong)
                        .build())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(String.class);
    }
}

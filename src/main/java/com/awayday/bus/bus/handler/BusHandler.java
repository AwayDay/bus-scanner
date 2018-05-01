package com.awayday.bus.bus.handler;

import com.awayday.bus.bus.model.BusApiModel;
import com.awayday.bus.bus.service.BusService;
import com.awayday.bus.station.model.StationApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class BusHandler {

    @Autowired
    private BusService busService;

    public Mono<ServerResponse> getBusListOfNode(ServerRequest request){
        String nodeId = request.pathVariable("nodeId");
        String cityCode = request.pathVariable("cityCode");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(busService.requestArriveBus(cityCode, nodeId), BusApiModel.class);
    }
}

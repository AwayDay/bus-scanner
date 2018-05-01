package com.awayday.bus.station.handler;

import com.awayday.bus.station.model.StationApiModel;
import com.awayday.bus.station.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StationHandler {

    private final StationService stationService;

    public StationHandler(@Autowired StationService stationService) {
        this.stationService = stationService;
    }

    public Mono<ServerResponse> getNearStationList(ServerRequest request){
        String locationLati = request.queryParam("locationLati").orElse("0.0");
        String locationLong = request.queryParam("locationLong").orElse("0.0");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(stationService.requestLocationStation(Double.parseDouble(locationLati), Double.parseDouble(locationLong)), StationApiModel.class);
    }

}

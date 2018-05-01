package com.awayday.bus.config;

import com.awayday.bus.bus.handler.BusHandler;
import com.awayday.bus.station.handler.StationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterConfiguration {

    @Autowired
    private StationHandler stationHandler;

    @Autowired
    private BusHandler busHandler;

    @Bean
    public RouterFunction routerFunction() {
        return RouterFunctions.route(GET("/stations"), stationHandler::getNearStationList)
                .and(RouterFunctions.route(GET("/cities/{cityCode}/stations/{nodeId}/routes"), busHandler::getBusListOfNode))
                .and(RouterFunctions.route(GET("/hello"), r -> ServerResponse.ok().body(BodyInserters.fromObject("hello!"))));
    }
}

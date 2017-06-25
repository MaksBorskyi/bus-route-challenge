package com.maks.route.service.impl;

import com.maks.route.model.RouteData;
import com.maks.route.model.RouteResponse;
import com.maks.route.service.RouteProviderService;
import com.maks.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */
@Component
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteProviderService routeProviderService;

    @Override
    public RouteResponse findDirection(Integer departure, Integer arrival) {
        RouteData routeData = routeProviderService.getRouteData();
        Boolean isDirectPresent = routeData.getRoutes().parallelStream()
                .anyMatch(r -> r.getStationIds()
                        .containsAll(Arrays.asList(departure, arrival)));
        return new RouteResponse(departure, arrival, isDirectPresent);
    }
}

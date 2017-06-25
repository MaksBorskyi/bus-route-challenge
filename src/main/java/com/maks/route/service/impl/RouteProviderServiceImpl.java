package com.maks.route.service.impl;

import com.maks.route.exception.NumberBusRouterException;
import com.maks.route.exception.NumberStationLimit;
import com.maks.route.model.BusRoute;
import com.maks.route.model.RouteData;
import com.maks.route.service.RouteProviderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */
@Data
@Slf4j
@Component
public class RouteProviderServiceImpl implements RouteProviderService {

    public static final int ALL_STATION_LIMIT = 1000000;
    public static final int BUS_ROUTES_LIMIT = 100000;
    public static final int STATION_LIMIT = 1000;
    public static final String EMPTY_SPACE = "\\s+";
    private RouteData data;

    @Value("${path}")
    private String filePath;

    @Override
    public RouteData getRouteData() {
        if (data == null) {
            data = parseRouterData();
        }
        return data;
    }

    @Scheduled(cron = "1 1 * * * mon")
    private void updateRouterData() {
        data = parseRouterData();
    }

    private RouteData parseRouterData() {
        RouteData routeData = new RouteData();
        List<String> routes = readRouteFile();
        for (int i = 0; i <= routes.size() - 1; i++) {
            String routeLine = routes.get(i);
            if (i == 0) {
                Integer numberBusRouter = Integer.parseInt(routeLine);
                if (numberBusRouter > BUS_ROUTES_LIMIT) {
                    throw new NumberBusRouterException("Exceeded upper limit for the number of bus routes");
                }
                routeData.setBusRouts(numberBusRouter);
            } else {
                AtomicInteger station = new AtomicInteger();
                Pattern.compile(EMPTY_SPACE)
                        .splitAsStream(routeLine).map(l -> Integer.parseInt(l))
                        .forEach(new Consumer<Integer>() {
                            int index = 0;

                            BusRoute busRoute = null;

                            @Override
                            public void accept(Integer integer) {
                                if (index == 0) {
                                    busRoute = new BusRoute();
                                    busRoute.setRouteId(integer);
                                    routeData.getRoutes().add(busRoute);
                                } else if (index > STATION_LIMIT || station.get() > ALL_STATION_LIMIT) {
                                    throw new NumberStationLimit("Exceeded upper limit for the number of station");
                                } else {
                                    station.intValue();
                                    busRoute.getStationIds().add(integer);
                                }
                                index++;
                            }
                        });

            }

        }
        return routeData;
    }

    private List<String> readRouteFile() {
        List<String> routes = null;
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            routes = lines.collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return routes;
    }
}

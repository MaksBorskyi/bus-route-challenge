package com.maks.route.service;


import com.maks.route.model.RouteResponse;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */

public interface RouteService {
    RouteResponse findDirection(Integer departure, Integer arrival);
}

package com.maks.route.controller;

import com.maks.route.model.RouteResponse;
import com.maks.route.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */
@RestController
@RequestMapping("/api")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(value = "/direct", produces = MediaType.APPLICATION_JSON_VALUE)
    public RouteResponse getDirection(@RequestParam(name = "dep_sid") Integer depSid, @RequestParam(name = "arr_sid") Integer arrSid){
       return routeService.findDirection(depSid, arrSid);
    }
}

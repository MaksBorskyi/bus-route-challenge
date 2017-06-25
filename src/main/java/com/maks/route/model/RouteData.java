package com.maks.route.model;

import lombok.Data;
import lombok.ToString;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */
@Data
@ToString
public class RouteData {
    private Integer busRouts;
    private Collection<BusRoute> routes = new HashSet<>();
}

package com.maks.route.model;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */
@Data
public class BusRoute {
    private Integer routeId;
    private Collection<Integer> stationIds = new HashSet<>();
}

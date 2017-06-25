package com.maks.route.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Maksym_Borskyi on 6/24/2017.
 */
@Data
@AllArgsConstructor
public class RouteResponse {
    @JsonProperty("dep_sid")
    private Integer depSid;
    @JsonProperty("arr_sid")
    private Integer arrSid;
    @JsonProperty("direct_bus_route")
    private Boolean directBusRoute;

}

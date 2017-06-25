package com.maks.route;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusRouteApplication {

    public static void main(String[] args) {
        if (args.length <= 0) {
            throw new IllegalArgumentException("Application can't start without argument 'path'");
        }
        SpringApplication.run(BusRouteApplication.class, args);
    }

}

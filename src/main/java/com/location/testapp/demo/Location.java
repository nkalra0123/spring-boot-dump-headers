package com.location.testapp.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;
import java.util.logging.Logger;

@Controller
public class Location {

    Logger logger = Logger.getLogger(getClass().getName());

    @GetMapping("/")
    public String home(Model model, @RequestHeader Map<String, String> headers) {

        String country = headers.getOrDefault(Constants.COUNTRY,"Unknown");
        String region = headers.getOrDefault(Constants.REGION,"Unknown");
        String city = headers.getOrDefault(Constants.CITY,"Unknown");
        String latLong = headers.getOrDefault(Constants.CITY_LAT_LONG,"Unknown");

        model.addAttribute("country", country);
        model.addAttribute("region", region);
        model.addAttribute("city", city);
        model.addAttribute("latLong", latLong);

        return "index"; //view
    }

    @GetMapping("/listHeaders")
    public ResponseEntity<String> listAllHeaders(
            @RequestHeader Map<String, String> headers) {
        headers.forEach((key, value) -> {
            //logger.info(String.format("Header '%s' = %s", key, value));
        });

        return new ResponseEntity<String>(
                String.format("Listed %s headers", headers), HttpStatus.OK);
    }
}

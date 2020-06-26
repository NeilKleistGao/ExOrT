package com.exort.controller.restful;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

@RestController
public class ExortGetController {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/arrangement/{date}", method = RequestMethod.GET)
    public Object getArrangementAt(@PathVariable("date") String date) {
        //TODO:
        return null;
    }
}

package com.exort.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

@Controller
public class ExortController {

    @RequestMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @RequestMapping("/timetable/{year}/{week}")
    public String getTimetable(@PathVariable("year") String year, @PathVariable("week") String week, Model model) {
        if ("0".equals(year) && "0".equals(week)) {
            Date now = new Date();
            year = String.valueOf(now.getYear() + 1900);

            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(now);
            week = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));
        }

        model.addAttribute("year", year);
        model.addAttribute("week", week);

        return "timetable";
    }
}

package com.exort.controller;

import com.exort.dao.ArrangementDAO;
import com.exort.dao.CharacterDAO;
import org.springframework.boot.Banner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class ExortController {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

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

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(week));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        Date start = calendar.getTime();

        for (int i = 0; i < 7; i++) {
            calendar.setTime(start);
            calendar.add(Calendar.DATE, i);
            model.addAttribute("date" + String.valueOf(i), dateFormat.format(calendar.getTime()));
        }

        calendar.setTime(start);
        calendar.add(Calendar.DATE, 7);
        if (calendar.get(Calendar.WEEK_OF_YEAR) == 1) {
            calendar.add(Calendar.YEAR, 1);
        }
        model.addAttribute("next", String.valueOf(calendar.get(Calendar.YEAR)) + "/" + String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
        calendar.setTime(start);
        calendar.add(Calendar.DATE, -6);
        model.addAttribute("previous", String.valueOf(calendar.get(Calendar.YEAR)) + "/" + String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));


        model.addAttribute("today", dateFormat.format(new Date()));

        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);

        List timeline = new LinkedList();

        for (int i = 0; i < 32; i++) {
            Date d1 = calendar.getTime();
            calendar.add(Calendar.MINUTE, 30);
            Date d2 = calendar.getTime();
            timeline.add(timeFormat.format(d1) + " - " + timeFormat.format(d2));
        }

        model.addAttribute("year", year);
        model.addAttribute("week", week);
        model.addAttribute("timeline", timeline);

        return "timetable";
    }

    @RequestMapping("/arrangement")
    public String getArrangement(Model model) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ArrangementDAO dao = (ArrangementDAO) context.getBean("arrangementDao");

        List arrangements = dao.find();
        model.addAttribute("arrangements", arrangements);

        return "arrangement";
    }

    @RequestMapping("/character")
    public String getCharacter(Model model) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        CharacterDAO dao = (CharacterDAO)context.getBean("characterDao");

        List characters = dao.find();
        model.addAttribute("characters", characters);

        return "character";
    }

    @RequestMapping("/settings")
    public String getSettings(Model model) {
        return "settings";
    }
}

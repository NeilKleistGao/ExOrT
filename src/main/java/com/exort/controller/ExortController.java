package com.exort.controller;

import com.exort.dao.ArrangementDAO;
import com.exort.dao.CharacterDAO;
import com.exort.dao.ParticipationDAO;
import com.exort.entity.Arrangement;
import com.exort.entity.ParticipationWithContent;
import org.springframework.boot.Banner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ExortController {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private List getTimetableList (String year ,Date start) {
        List<Map<String, Object>> res = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);

        for (int i = 0; i < 32; i++) {
            Date d1 = calendar.getTime();
            calendar.add(Calendar.MINUTE, 30);
            Date d2 = calendar.getTime();
            Map<String, Object> map = new HashMap<>();
            map.put("time", timeFormat.format(d1) + " - " + timeFormat.format(d2));
            map.put("list", new LinkedList<List>());
            res.add(map);
        }

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ParticipationDAO dao = (ParticipationDAO)context.getBean("participationDao");

        for (int i = 0; i < 7; i++) {
            calendar.setTime(start);
            calendar.add(Calendar.DATE, i);
            Date now = calendar.getTime();

            List<Map<Integer, ParticipationWithContent>> data = dao.findBetween(now);

            for (int j = 0; j < 32; j++) {
                Map<Integer, ParticipationWithContent> map = data.get(j);
                List<ParticipationWithContent> arrangements = new LinkedList<>();
                for (Map.Entry<Integer, ParticipationWithContent> entry : map.entrySet()) {
                    arrangements.add(entry.getValue());
                }

                List lst = (LinkedList)res.get(j).get("list");
                lst.add(arrangements);
                res.get(j).put("list", lst);
            }
        }

        return res;
    }

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

        model.addAttribute("year", year);
        model.addAttribute("week", week);
        model.addAttribute("timetable", getTimetableList(year, start));

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
        ArrangementDAO dao2 = (ArrangementDAO)context.getBean("arrangementDao");

        List characters = dao.find();
        model.addAttribute("characters", characters);
        List arrangements = dao2.find();
        model.addAttribute("arrangements", arrangements);

        return "character";
    }

    @RequestMapping("/settings")
    public String getSettings(Model model) {
        return "settings";
    }
}

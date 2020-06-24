package com.exort.controller;

import com.exort.dao.ArrangementDAO;
import com.exort.entity.Arrangement;
import com.exort.entity.TimetableItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ExortGetController {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/arrangement/{date}", method = RequestMethod.GET)
    public List<TimetableItem> getArrangementAt(@PathVariable("date") String date) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        LinkedList<TimetableItem> res = new LinkedList<>();

        for (int i = 0; i < 32; i++) {
            res.add(new TimetableItem());
        }

        return res;
    }
}

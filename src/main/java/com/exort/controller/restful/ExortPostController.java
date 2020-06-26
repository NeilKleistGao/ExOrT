package com.exort.controller.restful;

import com.exort.dao.ArrangementDAO;
import com.exort.dao.CharacterDAO;
import com.exort.entity.Arrangement;
import com.exort.entity.Character;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

@RestController
public class ExortPostController {
    @RequestMapping(value = "/update/character", method = RequestMethod.POST)
    public void postUpdateCharacter(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        CharacterDAO dao = (CharacterDAO)context.getBean("characterDao");

        Character character = new Character();
        if (!param.get("id").equals("")) {
            character.setId(Integer.valueOf(param.get("id")));
        }
        character.setName(param.get("name"));
        if (!param.get("id").equals("")) {
            character.setArea(param.get("area"));
        }
        if (!param.get("id").equals("")) {
            character.setSchool(param.get("school"));
        }

        if (dao.find(character.getId()) == null) {
            dao.insert(character);
        }
        else {
            dao.update(character);
        }
    }

    @RequestMapping(value = "/update/arrangement", method = RequestMethod.POST)
    public void postUpdateArrangement(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ArrangementDAO dao = (ArrangementDAO)context.getBean("arrangementDao");

        Arrangement arrangement = new Arrangement();
        if (!param.get("id").equals("")) {
            arrangement.setId(Integer.valueOf(param.get("id")));
        }

        arrangement.setName(param.get("name"));
        arrangement.setStart_date(Date.valueOf(param.get("start_date")));
        arrangement.setEnd_date(Date.valueOf(param.get("end_date")));
        arrangement.setRepeat(Integer.valueOf(param.get("repeat")));
        arrangement.setStart_time(Time.valueOf(param.get("start_time")));
        arrangement.setEnd_time(Time.valueOf(param.get("end_time")));

        if (dao.find(arrangement.getId()) == null) {
            dao.insert(arrangement);
        }
        else {
            dao.update(arrangement);
        }
    }

    @RequestMapping(value = "/delete/character", method = RequestMethod.POST)
    public void postDeleteCharacter(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        CharacterDAO dao = (CharacterDAO)context.getBean("characterDao");

        if (!param.get("id").equals("")) {
            dao.delete(Integer.valueOf(param.get("id")));
        }
    }

    @RequestMapping(value = "/delete/arrangement", method = RequestMethod.POST)
    public void postDeleteArrangement(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ArrangementDAO dao = (ArrangementDAO)context.getBean("arrangementDao");

        if (!param.get("id").equals("")) {
            dao.delete(Integer.valueOf(param.get("id")));
        }
    }
}

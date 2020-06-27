package com.exort.controller.restful;

import com.exort.dao.ArrangementDAO;
import com.exort.dao.CharacterDAO;
import com.exort.dao.ParticipationDAO;
import com.exort.entity.Arrangement;
import com.exort.entity.Character;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
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
        if (!param.get("area").equals("")) {
            character.setArea(param.get("area"));
        }
        if (!param.get("school").equals("")) {
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
        ParticipationDAO dao2 = (ParticipationDAO)context.getBean("participationDao");

        if (!param.get("id").equals("")) {
            dao2.deleteByCID(Integer.valueOf(param.get("id")));
            dao.delete(Integer.valueOf(param.get("id")));
        }
    }

    @RequestMapping(value = "/delete/arrangement", method = RequestMethod.POST)
    public void postDeleteArrangement(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ArrangementDAO dao = (ArrangementDAO)context.getBean("arrangementDao");
        ParticipationDAO dao2 = (ParticipationDAO)context.getBean("participationDao");

        if (!param.get("id").equals("")) {
            dao2.deleteByAID(Integer.valueOf(param.get("id")));
            dao.delete(Integer.valueOf(param.get("id")));
        }
    }

    @RequestMapping(value = "/update/participation", method = RequestMethod.POST)
    public void postUpdateParticipation(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ParticipationDAO dao = (ParticipationDAO)context.getBean("participationDao");

        if (!param.get("id").equals("")) {
            Integer cid = Integer.valueOf(param.get("id"));
            param.remove("id", cid.toString());
            List<Integer> participation = dao.findAID(cid);
            for (Integer aid : participation) {
                if (Boolean.valueOf(param.get(aid.toString())) == false) {
                    dao.delete(cid, aid);
                    participation.remove((Object)aid);
                }
            }

            for (Map.Entry<String, String> entry : param.entrySet()) {
                if (Boolean.valueOf(entry.getValue()) == true && !participation.contains(entry.getKey())) {
                    dao.insert(cid, Integer.valueOf(entry.getKey()));
                }
            }
        }
    }
}

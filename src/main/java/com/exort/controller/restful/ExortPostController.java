package com.exort.controller.restful;

import com.exort.dao.ArrangementDAO;
import com.exort.dao.CharacterDAO;
import com.exort.dao.ParticipationDAO;
import com.exort.dao.SettingsDAO;
import com.exort.entity.Arrangement;
import com.exort.entity.Character;
import com.exort.util.GitUtil;
import com.exort.util.MySQLUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;


/**
 * This controller class is in charge of all post requests in restful form
 * @author NeilKleistGao
 * @version 1.0.1
 */
@RestController
public class ExortPostController {
    /**
     * This method receives characters' data and updates them in database
     * @param param Json object from frontend including characters' data
     */
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

    /**
     * This method receives arrangement' data and updates them in database
     * @param param Json object from frontend including arrangements' data
     */
    @RequestMapping(value = "/update/arrangement", method = RequestMethod.POST)
    public void postUpdateArrangement(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ArrangementDAO dao = (ArrangementDAO)context.getBean("arrangementDao");

        Arrangement arrangement = new Arrangement();
        String ext = ":00";
        if (!param.get("id").equals("")) {
            arrangement.setId(Integer.valueOf(param.get("id")));
            ext = "";
        }

        arrangement.setName(param.get("name"));
        arrangement.setStart_date(Date.valueOf(param.get("start_date")));
        arrangement.setEnd_date(Date.valueOf(param.get("end_date")));
        arrangement.setRepeat(Integer.valueOf(param.get("repeat")));
        arrangement.setStart_time(Time.valueOf(param.get("start_time") + ext));
        arrangement.setEnd_time(Time.valueOf(param.get("end_time") + ext));

        if (dao.find(arrangement.getId()) == null) {
            dao.insert(arrangement);
        }
        else {
            dao.update(arrangement);
        }
    }

    /**
     * This method receives characters' id and removes them from database
     * @param param Json object from frontend including characters' id
     */
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

    /**
     * This method receives arrangements' id and removes them from database
     * @param param Json object from frontend including arrangements' id
     */
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

    /**
     * This method receives participation objects and updates them in database
     * @param param Json object from frontend including participation information
     */
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
                if (Boolean.valueOf(entry.getValue()) == true && !participation.contains(Integer.valueOf(entry.getKey()))) {
                    dao.insert(cid, Integer.valueOf(entry.getKey()));
                }
            }
        }
    }

    /**
     * This method receives settings data and updates them in database. If user changes the git link, it clones data from the new one
     * @param param Json object from frontend including settings information
     */
    @RequestMapping(value = "/update/settings", method = RequestMethod.POST)
    public void postUpdateSettings(@RequestParam Map<String, String> param) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        SettingsDAO dao = (SettingsDAO)context.getBean("settingsDao");

        for (Map.Entry<String, String> entry : param.entrySet()) {
            dao.update(entry.getKey(), entry.getValue());

            if (entry.getKey().equals("git")) {
                GitUtil.checkUpdate();
            }
        }
    }

    /**
     * This method deals with the request asking for synchronizing data
     * @param param Json object from frontend including username and password
     */
    @RequestMapping(value = "/sync", method = RequestMethod.POST)
    public void postSync(@RequestParam Map<String, String> param) {
        MySQLUtil.exportSQL(GitUtil.getFilename());
        GitUtil.push(GitUtil.createCredentialsProvider(param.get("username").toString(), param.get("password").toString()));
    }
}

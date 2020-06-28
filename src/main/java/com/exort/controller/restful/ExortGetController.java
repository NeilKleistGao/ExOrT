package com.exort.controller.restful;

import com.exort.dao.ParticipationDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This controller class is in charge of all get requests in restful form
 * @author NeilKleistGao
 * @version 1.0.0
 */
@RestController
public class ExortGetController {

    /**
     * This method query all arrangement ids some character takes participation
     * @param cid The character id
     * @return The List of arrangement id
     */
    @RequestMapping(value = "/get/participation/aid/{cid}", method = RequestMethod.GET)
    public List<Integer> getArrangementId(@PathVariable("cid") String cid) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:mysql.xml");
        ParticipationDAO dao = (ParticipationDAO)context.getBean("participationDao");

        return dao.findAID(Integer.valueOf(cid));
    }
}

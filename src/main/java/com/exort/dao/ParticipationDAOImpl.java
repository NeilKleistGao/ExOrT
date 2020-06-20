package com.exort.dao;

import com.exort.entity.Participation;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ParticipationDAOImpl extends BaseDAO implements ParticipationDAO {

    @Override
    public void insert(Participation participation) {
        int cid = participation.getCharacter_id();
        int aid = participation.getArrangement_id();

        String sql = "insert into participation(character_id, arrangement_id) values(?, ?)";
        this.getJdbcTemplate().update(sql, new Object[]{cid, aid});
    }

    @Override
    public Participation find(Integer cid, Integer aid) {
        List rows = this.getJdbcTemplate().queryForList("select * from participation where character_id=" + cid + "and arrangement_id=" + aid);
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            Map map = (Map)it.next();
            Participation participation = new Participation();
            participation.setCharacter_id(Integer.valueOf(map.get("character_id").toString()));
            participation.setArrangement_id(Integer.valueOf(map.get("arrangement_id").toString()));
        }

        return null;
    }
}

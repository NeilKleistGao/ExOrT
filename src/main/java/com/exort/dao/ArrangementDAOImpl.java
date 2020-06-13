package com.exort.dao;

import com.exort.entity.Arrangement;

import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArrangementDAOImpl extends BaseDAO implements ArrangementDAO {
    @Override
    public void insert(Arrangement arrangement) {
        int id = arrangement.getId().intValue();
        String name = arrangement.getName();
        Date start_date = arrangement.getStart_date();
        Date end_date = arrangement.getEnd_date();
        int repeat = arrangement.getRepeat().intValue();
        Time start_time = arrangement.getStart_time();
        Time end_time = arrangement.getEnd_time();

        String sql = "insert into arrangement(id, name, start_date, end_date, repeat, start_time, end_time) values(?, ?, ?, ?, ?, ?, ?);";
        this.getJdbcTemplate().update(sql, new Object[] {id, name, start_date, end_date, repeat, start_time, end_time});
    }

    @Override
    public Arrangement find(Integer id) {
        List rows = getJdbcTemplate().queryForList("select * from arrangement where id=" + id.intValue());
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            Map map = (Map)it.next();
            Arrangement arrangement = new Arrangement();
            arrangement.setId(Integer.valueOf(map.get("id").toString()));
            arrangement.setName(map.get("name").toString());
            arrangement.setStart_date(Date.valueOf(map.get("start_date").toString()));
            arrangement.setEnd_date(Date.valueOf(map.get("end_date").toString()));
            arrangement.setRepeat(Integer.valueOf(map.get("repeat").toString()));
            arrangement.setStart_time(Time.valueOf(map.get("start_time").toString()));
            arrangement.setEnd_time(Time.valueOf(map.get("end_time").toString()));

            return arrangement;
        }

        return null;
    }
}

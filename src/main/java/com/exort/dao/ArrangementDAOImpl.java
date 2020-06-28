package com.exort.dao;

import com.exort.entity.Arrangement;

import java.sql.Date;
import java.sql.Time;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @see com.exort.dao.ArrangementDAO
 * @see com.exort.dao.BaseDAO
 */
public class ArrangementDAOImpl extends BaseDAO implements ArrangementDAO {

    @Override
    public void insert(Arrangement arrangement) {
        String name = arrangement.getName();
        Date start_date = arrangement.getStart_date();
        Date end_date = arrangement.getEnd_date();
        int repeat = arrangement.getRepeat().intValue();
        Time start_time = arrangement.getStart_time();
        Time end_time = arrangement.getEnd_time();

        String sql = "insert into arrangement(name, start_date, end_date, `repeat`, start_time, end_time) values(?, ?, ?, ?, ?, ?);";
        this.getJdbcTemplate().update(sql, new Object[] {name, start_date, end_date, repeat, start_time, end_time});
    }

    private Arrangement getArrangementFromMap(Map map) {
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

    @Override
    public Arrangement find(Integer id) {
        if (id == null) {
            return null;
        }

        List rows = getJdbcTemplate().queryForList("select * from arrangement where id=" + id.intValue());
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            return getArrangementFromMap((Map)it.next());
        }

        return null;
    }

    @Override
    public List<Arrangement> find() {
        List<Arrangement> res = new LinkedList<>();
        List rows = getJdbcTemplate().queryForList("select * from arrangement");
        Iterator it = rows.iterator();

        while (it.hasNext()) {
            res.add(getArrangementFromMap((Map)it.next()));
        }

        return res;
    }

    @Override
    public void update(Arrangement arrangement) {
        String sql = "update `arrangement` set name=?, start_date=?, end_date=?, `repeat`=?, start_time=?, end_time=? where id=?;";
        int id = arrangement.getId().intValue();
        String name = arrangement.getName();
        Date start_date = arrangement.getStart_date();
        Date end_date = arrangement.getEnd_date();
        int repeat = arrangement.getRepeat().intValue();
        Time start_time = arrangement.getStart_time();
        Time end_time = arrangement.getEnd_time();

        this.getJdbcTemplate().update(sql, new Object[] {name, start_date, end_date, repeat, start_time, end_time, id});
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from `arrangement` where id=" + id.toString();
        this.getJdbcTemplate().update(sql);
    }
}

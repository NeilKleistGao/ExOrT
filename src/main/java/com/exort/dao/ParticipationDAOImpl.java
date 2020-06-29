package com.exort.dao;

import com.exort.entity.Participation;
import com.exort.entity.ParticipationWithContent;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @see com.exort.dao.BaseDAO
 * @see com.exort.dao.ParticipationDAO
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class ParticipationDAOImpl extends BaseDAO implements ParticipationDAO {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void insert(Integer cid, Integer aid) {
        String sql = "insert into participation(character_id, arrangement_id) values(?, ?)";
        this.getJdbcTemplate().update(sql, new Object[]{cid, aid});
    }

    @Override
    public Participation find(Integer cid, Integer aid) {
        List rows = this.getJdbcTemplate().queryForList("select * from participation where character_id="
                + cid.toString() + "and arrangement_id=" + aid.toString());
        Iterator it = rows.iterator();

        if (it.hasNext()) {
            Map map = (Map)it.next();
            Participation participation = new Participation();
            participation.setCharacter_id(Integer.valueOf(map.get("character_id").toString()));
            participation.setArrangement_id(Integer.valueOf(map.get("arrangement_id").toString()));
        }

        return null;
    }

    @Override
    public List<Integer> findAID(Integer cid) {
        List rows = this.getJdbcTemplate().queryForList("select arrangement_id from participation where character_id=" + cid.toString());
        Iterator it = rows.iterator();

        List<Integer> res = new LinkedList<>();
        while (it.hasNext()) {
            Map map = (Map)it.next();
            res.add(Integer.valueOf(map.get("arrangement_id").toString()));
        }

        return res;
    }

    @Override
    public void delete(Integer cid, Integer aid) {
        String sql = "delete from participation where character_id=" +
                cid.toString() + " and arrangement_id=" + aid.toString();

        this.getJdbcTemplate().update(sql);
    }

    @Override
    public void deleteByAID(Integer aid) {
        String sql = "delete from participation where arrangement_id=" + aid.toString();

        this.getJdbcTemplate().update(sql);
    }

    @Override
    public void deleteByCID(Integer cid) {
        String sql = "delete from participation where character_id=" + cid.toString();

        this.getJdbcTemplate().update(sql);
    }

    @Override
    public List<Map<Integer, ParticipationWithContent>> findBetween(Date today) {
        String sql = "select arrangement.id as aid, arrangement.name as aname, `character`.name as cname, start_time, end_time from (arrangement, participation, `character`) " +
                "where arrangement.id = participation.arrangement_id and " +
                "`character`.id = participation.character_id and " +
                "date(\"" + dateFormat.format(today) + "\") between start_date and end_date and " +
                "((select datediff(date(\"" + dateFormat.format(today) + "\"), arrangement.start_date) as df) % arrangement.repeat) = 0;";
        List rows = this.getJdbcTemplate().queryForList(sql);
        Iterator it = rows.iterator();
        List<Map<Integer, ParticipationWithContent>> res = new LinkedList<>();

        for (int i = 0; i < 32; i++) {
            res.add(new HashMap<>());
        }

        while (it.hasNext()) {
            Map map = (Map)it.next();
            Time start_time = Time.valueOf(map.get("start_time").toString());
            Time end_time = Time.valueOf(map.get("end_time").toString());

            int sub = (int)(end_time.getTime() - start_time.getTime()) / 1800000;
            int start = (int)(start_time.getTime() - Time.valueOf("07:00:00").getTime()) / 1800000;
            for (int i = start; i < start + sub; i++) {
                Integer aid = Integer.valueOf(map.get("aid").toString());
                if (res.get(i).containsKey(aid)) {
                    ParticipationWithContent participation = res.get(i).get(aid);
                    participation.setCharacters(participation.getCharacters() + ", " + map.get("cname").toString());
                    res.get(i).put(aid, participation);
                }
                else {
                    ParticipationWithContent participation = new ParticipationWithContent();
                    participation.setTitle(map.get("aname").toString());
                    participation.setCharacters(map.get("cname").toString());
                    res.get(i).put(aid, participation);
                }
            }
        }

        return res;
    }
}

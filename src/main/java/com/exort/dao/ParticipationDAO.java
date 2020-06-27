package com.exort.dao;

import com.exort.entity.Participation;
import com.exort.entity.ParticipationWithContent;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ParticipationDAO {
    public void insert(Integer cid, Integer aid);
    public Participation find(Integer cid, Integer aid);
    public List<Integer> findAID(Integer cid);
    public void delete(Integer cid, Integer aid);
    public void deleteByAID(Integer aid);
    public void deleteByCID(Integer cid);
    public List<Map<Integer, ParticipationWithContent>> findBetween(Date today);
}

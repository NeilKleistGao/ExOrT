package com.exort.dao;

import com.exort.entity.Participation;

public interface ParticipationDAO {
    public void insert(Participation participation);
    public Participation find(Integer cid, Integer aid);
}

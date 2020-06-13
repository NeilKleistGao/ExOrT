package com.exort.dao;

import com.exort.entity.Arrangement;

public interface ArrangementDAO {
    public void insert(Arrangement arrangement);
    public Arrangement find(Integer id);
}

package com.exort.dao;

import com.exort.entity.Arrangement;

import java.text.ParseException;
import java.util.List;

public interface ArrangementDAO {
    public void insert(Arrangement arrangement);
    public Arrangement find(Integer id);
    public List<Arrangement> find();
}

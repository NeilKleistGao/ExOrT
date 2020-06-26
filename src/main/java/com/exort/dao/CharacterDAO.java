package com.exort.dao;

import com.exort.entity.Character;

import java.util.List;

public interface CharacterDAO {
    public void insert(Character character);
    public Character find(Integer id);
    public List<Character> find();
    public void update(Character character);
    public void delete(Integer id);
}

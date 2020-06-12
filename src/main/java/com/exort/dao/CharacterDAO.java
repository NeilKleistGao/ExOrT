package com.exort.dao;

import com.exort.entity.Character;

public interface CharacterDAO {
    public void insert(Character character);
    public Character find(int id);
}

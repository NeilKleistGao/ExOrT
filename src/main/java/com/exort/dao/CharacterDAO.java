package com.exort.dao;

import com.exort.entity.Character;

import java.util.List;

/**
 * This interface determines all operations on the table character in database
 * @author NeilKleistGao
 * @version 1.0.0
 * @see Character
 */
public interface CharacterDAO {
    /**
     *
     * @param character
     */
    public void insert(Character character);

    /**
     *
     * @param id
     * @return
     */
    public Character find(Integer id);

    /**
     *
     * @return
     */
    public List<Character> find();

    /**
     *
     * @param character
     */
    public void update(Character character);

    /**
     *
     * @param id
     */
    public void delete(Integer id);
}

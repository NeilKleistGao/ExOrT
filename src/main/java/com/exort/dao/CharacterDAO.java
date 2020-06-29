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
     * This method inserts a character object into database
     * @param character The character object
     */
    public void insert(Character character);

    /**
     * This method searches for the character object having specified id
     * @param id The specified id
     * @return The character object having specified id, or null if it's not found
     */
    public Character find(Integer id);

    /**
     * This method collects all character objects in database
     * @return The list of all character objects
     */
    public List<Character> find();

    /**
     * This method updates a specified character object
     * @param character The character to be updated
     */
    public void update(Character character);

    /**
     * This method removes the character object having specified id
     * @param id The specified id
     */
    public void delete(Integer id);
}

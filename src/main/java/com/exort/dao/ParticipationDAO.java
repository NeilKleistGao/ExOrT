package com.exort.dao;

import com.exort.entity.Participation;
import com.exort.entity.ParticipationWithContent;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * This interface determines all operations on the table participation in database
 * @author NeilKleistGao
 * @version 1.0.1
 * @see Participation
 * @see ParticipationWithContent
 */
public interface ParticipationDAO {
    /**
     * This method inserts a participation object into database
     * @param cid The character id
     * @param aid The arrangement id
     */
    public void insert(Integer cid, Integer aid);

    /**
     * This method searches for the unique participation object
     * @param cid The character id
     * @param aid The arrangement id
     * @return The unique participation, or null if it's not found
     */
    public Participation find(Integer cid, Integer aid);

    /**
     * This method searches for all arrangements' id a character takes part in
     * @param cid The character id
     * @return The list of arrangements' id
     */
    public List<Integer> findAID(Integer cid);

    /**
     * This method removes the specified participation object
     * @param cid The character id
     * @param aid The arrangement id
     */
    public void delete(Integer cid, Integer aid);

    /**
     * This method removes all participation objects linked to some arrangement
     * @param aid The arrangement id
     */
    public void deleteByAID(Integer aid);

    /**
     * This method removes all participation objects linked to some character
     * @param cid The character id
     */
    public void deleteByCID(Integer cid);

    /**
     * This method searches for all participation objects whose date range contains given date
     * @param today The given date
     * @return A list including all participation divided by arrangement id
     */
    public List<Map<Integer, ParticipationWithContent>> findContaining(Date today);
}

package com.exort.dao;

import com.exort.entity.Arrangement;

import java.util.List;

/**
 * This interface determines all operations on the table arrangement in database
 * @author NeilKleistGao
 * @version 1.0.0
 * @see Arrangement
 */
public interface ArrangementDAO {
    /**
     * This method inserts an arrangement object into database
     * @param arrangement The arrangement object
     */
    public void insert(Arrangement arrangement);

    /**
     * This method searches for the arrangement object having specified id
     * @param id The specified id
     * @return The arrangement object having specified id
     */
    public Arrangement find(Integer id);

    /**
     * This method collects all arrangement objects in database
     * @return The list of all arrangement objects
     */
    public List<Arrangement> find();

    /**
     * This method updates a specified arrangement object
     * @param arrangement The arrangement to be updated
     */
    public void update(Arrangement arrangement);

    /**
     * This method removes the arrangement object having specified id
     * @param id The specified id
     */
    public void delete(Integer id);
}

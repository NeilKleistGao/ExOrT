package com.exort.entity;

/**
 * This class have the same meaning with Participation, combining all data into a string together
 * @see Participation
 * @author NeilKleistGao
 * @version 1.0.0
 */
public class ParticipationWithContent {
    private String title;
    private String characters;

    public String getCharacters() {
        return characters;
    }

    public String getTitle() {
        return title;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.exort.entity;

import java.util.LinkedList;
import java.util.List;

public class TimetableItem {
    private List<String> titles;
    private List<String> participants;

    public TimetableItem() {
        titles = new LinkedList<>();
        participants = new LinkedList<>();
    }

    public void addTitle(String title) {
        titles.add(title);
    }

    public void addParticipant(String participant) {
        participants.add(participant);
    }

    public List<String> getParticipants() {
        return participants;
    }

    public List<String> getTitles() {
        return titles;
    }
}

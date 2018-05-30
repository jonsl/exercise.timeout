package com.exercise.timeout.model;

import com.exercise.timeout.api.Venue;

import java.util.*;

public class Fixture {

    private Map<String/*venue name*/, Set<String/*member names*/>> avoidEatMap;
    private Map<String/*venue name*/, Set<String/*member names*/>> canDrinkMap;

    Fixture(List<Venue> venueList) {
        this.avoidEatMap = new HashMap<String, Set<String>>();
        this.canDrinkMap = new HashMap<String, Set<String>>();
    }

    public Map<String, Set<String>> getAvoidEatMap() {
        return avoidEatMap;
    }

    public Map<String, Set<String>> getCanDrinkMap() {
        return canDrinkMap;
    }
}

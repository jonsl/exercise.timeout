package com.exercise.timeout.model;

import com.exercise.timeout.api.Venue;

import java.util.*;

public class Fixture {

    private Set<Venue> goVenueSet;
    private Set<Venue> avoidVenueSet;
    private Map<String/*venue name*/, Set<String/*member names*/>> avoidEatMap;
    private Map<String/*venue name*/, Set<String/*member names*/>> avoidDrinkMap;

    Fixture(List<Venue> venueList) {
        this.goVenueSet = new HashSet<Venue>(venueList);
        this.avoidVenueSet = new HashSet<Venue>();

        this.avoidEatMap = new HashMap<String, Set<String>>();
        this.avoidDrinkMap = new HashMap<String, Set<String>>();
    }

    public Set<Venue> getGoVenueSet() {
        return goVenueSet;
    }

    public Set<Venue> getAvoidVenueSet() {
        return avoidVenueSet;
    }

    public Map<String, Set<String>> getAvoidEatMap() {
        return avoidEatMap;
    }

    public Map<String, Set<String>> getAvoidDrinkMap() {
        return avoidDrinkMap;
    }
}

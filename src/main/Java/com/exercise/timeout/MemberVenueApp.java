package com.exercise.timeout;

import com.exercise.timeout.api.Venue;
import com.exercise.timeout.model.Fixture;
import com.exercise.timeout.model.SocialManager;

public class MemberVenueApp {

    private static final String USERS_FILE_NAME = "users.json";
    private static final String VENUES_FILE_NAME = "venues.json";

    public static void main(String[] args) {

        try {
            SocialManager socialManager = new SocialManager(USERS_FILE_NAME, VENUES_FILE_NAME);

            Fixture fixture = socialManager.getFixtures(args);

            System.out.println("Places to go:");
            for (Venue venue : fixture.getGoVenueSet()) {
                System.out.println("\t" + venue.getName());
            }
            System.out.println("Places to avoid:");
            for (Venue venue : fixture.getAvoidVenueSet()) {
                System.out.println("\t" + venue.getName());
                if (fixture.getAvoidEatMap().get(venue.getName()) != null) {
                    for (String name : fixture.getAvoidEatMap().get(venue.getName())) {
                        System.out.println("\t\tThere is nothing for " + name + " to eat");
                    }
                }
                if (fixture.getAvoidDrinkMap().get(venue.getName()) != null) {
                    for (String name : fixture.getAvoidDrinkMap().get(venue.getName())) {
                        System.out.println("\t\tThere is nothing for " + name + " to drink");
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

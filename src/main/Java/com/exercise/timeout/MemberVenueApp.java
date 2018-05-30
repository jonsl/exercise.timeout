package com.exercise.timeout;

import com.exercise.timeout.api.Member;
import com.exercise.timeout.api.Venue;
import com.exercise.timeout.model.Fixture;
import com.exercise.timeout.model.SocialManager;

import java.util.Set;

public class MemberVenueApp {

    private static final String USERS_FILE_NAME = "users.json";
    private static final String VENUES_FILE_NAME = "venues.json";

    public static void main(String[] args) {

        try {
            SocialManager socialManager = new SocialManager(USERS_FILE_NAME, VENUES_FILE_NAME);

            Fixture fixture = socialManager.getFixtures(args);

            System.out.println("Places to go:");
            for (Venue venue : socialManager.getVenueList()) {
                if (fixture.getAvoidEatMap().get(venue.getName()) == null
                        && fixture.getCanDrinkMap().get(venue.getName()) != null) {
                    System.out.println("\t" + venue.getName());
                }
            }
            System.out.println("Places to avoid:");
            for (Venue venue : socialManager.getVenueList()) {
                Set<String> avoidEatNameSet = fixture.getAvoidEatMap().get(venue.getName());
                Set<String> canDrinkNameSet = fixture.getCanDrinkMap().get(venue.getName());
                if (avoidEatNameSet != null || canDrinkNameSet == null) {
                    System.out.println("\t" + venue.getName());
                }
                for (String name : args) {
                    if (avoidEatNameSet != null) {
                        if (avoidEatNameSet.contains(name)) {
                            System.out.println("\t\tThere is nothing for " + name + " to eat");
                        }
                    }
                    if (canDrinkNameSet != null) {
                        if (!canDrinkNameSet.contains(name))
                            System.out.println("\t\tThere is nothing for " + name + " to drink");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

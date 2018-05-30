package com.exercise.timeout;

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
                Set<String> avoidEatSet = fixture.getAvoidEatMap().get(venue.getName());
                Set<String> avoidDrinkSet = fixture.getAvoidDrinkMap().get(venue.getName());
                if (avoidEatSet == null && avoidDrinkSet == null) {
                    System.out.println("\t" + venue.getName());
                }
            }
            System.out.println("\nPlaces to avoid:");
            for (Venue venue : socialManager.getVenueList()) {
                Set<String> avoidEatSet = fixture.getAvoidEatMap().get(venue.getName());

                if (avoidEatSet != null) {
                    System.out.println("\tcant eat : " + venue.getName());
                    for (String avoidEatName : avoidEatSet) {
                        System.out.println("\t\t" + avoidEatName);
                    }
                }

                Set<String> avoidDrinkSet = fixture.getAvoidDrinkMap().get(venue.getName());
                if (avoidDrinkSet != null) {
                    System.out.println("\tcant drink : " + venue.getName());
                    for (String avoidDrinkName : avoidDrinkSet) {
                        System.out.println("\t\t" + avoidDrinkName);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

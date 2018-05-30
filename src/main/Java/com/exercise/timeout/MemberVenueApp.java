package com.exercise.timeout;

import com.exercise.timeout.api.Member;
import com.exercise.timeout.api.Venue;
import com.exercise.timeout.model.JsonFeeds;

import java.util.*;

public class MemberVenueApp {

    public static void main(String[] args) {

        try {
            JsonFeeds jsonFeeds = new JsonFeeds();

            // start with everything in places to go
            // and remove depending on who cant eat
            Set<Venue> goVenueSet = new HashSet<Venue>(jsonFeeds.getVenueList());
            Set<Venue> avoidVenueSet = new HashSet<Venue>();

            Map<String/*venue name*/, Set<String/*member names*/>> avoidMap = new HashMap<String, Set<String>>();

            for (String name : args) {

                // arg is (hopefully) a name
                Member member = jsonFeeds.getMember(name);

                if (member != null) {

                    for (String wontEatItem : member.getWontEat()) {
                        for (Venue venue : jsonFeeds.getVenueList()) {

                            boolean canEat = false;

                            for (String food : venue.getFood()) {
                                if (!food.equals(wontEatItem)) {
                                    canEat = true;
                                }
                            }

                            if (!canEat) {
                                // we cant go here
                                avoidVenueSet.add(venue);
                                Set<String> values = avoidMap.computeIfAbsent(venue.getName(), k -> new HashSet<String>());
                                values.add(name);

                                goVenueSet.remove(venue);
                            }
                        }
                    }

                    for (String willDrinkItem : member.getDrinks()) {
                        for (Venue venue : jsonFeeds.getVenueList()) {

                            boolean canDrink = false;

                            for (String drink : venue.getDrinks()) {
                                if (drink.equals(willDrinkItem)) {
                                    canDrink = true;
                                }
                            }

                            if (!canDrink) {
                                // we cant go here
                                avoidVenueSet.add(venue);
                                Set<String> values = avoidMap.computeIfAbsent(venue.getName(), k -> new HashSet<String>());
                                values.add(name);

                                goVenueSet.remove(venue);
                            }
                        }
                    }
                }
            }

            System.out.println("Places to go:");
            for (Venue venue : goVenueSet) {
                System.out.println("\t" + venue.getName());
            }
            System.out.println("Places to avoid:");
            for (Venue venue : avoidVenueSet) {
                System.out.println("\t" + venue.getName());
                for (String name : avoidMap.get(venue.getName())) {
                    System.out.println("\t\tThere is nothing for " + name);
                }
            }

        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

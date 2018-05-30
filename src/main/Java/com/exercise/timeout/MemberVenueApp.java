package com.exercise.timeout;

import com.exercise.timeout.api.Member;
import com.exercise.timeout.api.Venue;
import com.exercise.timeout.model.JsonFeeds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberVenueApp {

    public static void main(String[] args) {

        try {
            JsonFeeds jsonFeeds = new JsonFeeds();

            // start with everything in places to go
            // and remove depending on who cant eat
            ArrayList<Venue> placesToGo = new ArrayList<Venue>(jsonFeeds.getVenueList());
            Map<String/*venue name*/, Member> avoidMap = new HashMap<String, Member>();
            ArrayList<Venue> placesToAvoid = new ArrayList<Venue>();

            for (String name : args) {

                // arg is (hopefully) a name
                Member member = jsonFeeds.getMember(name);

                if (member != null) {

                    for (String wontEatItem : member.getWontEat()) {

                        for (Venue venue : jsonFeeds.getVenueList()) {

                            ArrayList<Member> membersWhoCantEat = new ArrayList<Member>();
                            boolean canEat = false;

                            for (String food : venue.getFood()) {
                                if (!food.equals(wontEatItem)) {
                                    canEat = true;
                                }
                            }

                            if (!canEat) {
                                placesToAvoid.add(venue);
                                placesToGo.remove(venue);
                            }
                        }
                    }
                }
            }

            System.out.println("Places to go:");
            for (Venue venue : placesToGo) {
                System.out.println("\t" + venue.getName());
            }
            System.out.println("Places to avoid:");
            for (Venue venue : placesToAvoid) {
                System.out.println("\t" + venue.getName());
            }

        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

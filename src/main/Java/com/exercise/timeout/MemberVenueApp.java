package com.exercise.timeout;

import com.exercise.timeout.api.Member;
import com.exercise.timeout.model.JsonFeeds;

public class MemberVenueApp {

    public static void main(String[] args) {

        try {
            JsonFeeds jsonFeeds = new JsonFeeds();


            for (String name : args) {

                // arg is (hopefully) a name
                Member member = jsonFeeds.getMember(name);

                if (member != null) {

                }

            }

        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
}

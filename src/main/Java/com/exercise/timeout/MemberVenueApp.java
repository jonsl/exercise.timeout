package com.exercise.timeout;

import com.exercise.timeout.model.JsonFeeds;

public class MemberVenueApp {

    public static void main(String[] args) {

        try {
            JsonFeeds jsonFeeds = new JsonFeeds();




        } catch (Exception e) {
            System.err.println("IOException: " + e.getMessage());
        }


    }
}

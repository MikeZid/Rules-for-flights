package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Create Rules
        FlightRule secondRule = new SecondRule();
        FlightRule firstRule = new FirstRule();
        FlightRule thirdRule = new ThirdRule();

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Source list: ");
        System.out.println(flights);

        //Test rules
        System.out.println("Rule #1: " + firstRule.description());
        System.out.println(firstRule.delIrrelevantFromList(flights));

        System.out.println("Rule #2: " + secondRule.description());
        System.out.println(secondRule.delIrrelevantFromList(flights));

        System.out.println("Rule #3: " + thirdRule.description());
        System.out.println(thirdRule.delIrrelevantFromList(flights));

        //What is removed
        System.out.println("Deleted");
        System.out.println(firstRule.showIrrelevantFromList(flights));
        System.out.println(secondRule.showIrrelevantFromList(flights));
        System.out.println(thirdRule.showIrrelevantFromList(flights));
    }
}

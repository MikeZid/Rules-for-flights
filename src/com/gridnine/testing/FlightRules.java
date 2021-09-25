package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/** Rule for flight's list
 *
 */
interface FlightRule {

    /** Apply rule to the list - delete all flights, which don't conforms to the rule
     * @param flights source list
     * @return the new modified list
     */

    public List<Flight> delIrrelevantFromList(List<Flight> flights);

    /** Show all flights, which don't conforms to the rule
     * @param flights source list
     * @return the new list with irrelevant flights
     */
    public List<Flight> showIrrelevantFromList(List<Flight> flights);

    /** Check element for compliance
     * @param flight source element of list
     * @return true, if flight conforms to the rule, else false
     */

    public boolean checkFlight(Flight flight);

    /** Return more information about rule.
     * @return description - what is the rule
     */
    public String description();

}

/** Test rule #1
 *  Description: Delete flights where departure date is past
 */
class FirstRule implements FlightRule {
    @Override
    public List<Flight> delIrrelevantFromList(List<Flight> flights) {
        List<Flight> result = new ArrayList<>(flights);
        result.removeIf(this::checkFlight);
        return result;
    }

    @Override
    public List<Flight> showIrrelevantFromList(List<Flight> flights) {
        return new ArrayList<>(flights).stream().filter(this::checkFlight).collect(Collectors.toList());
    }

    @Override
    public boolean checkFlight(Flight flight) {
        return flight.getSegments().get(0).getDepartureDate().compareTo(LocalDateTime.now()) < 0;
    }

    @Override
    public String description() {
        return "Delete flights where departure date is past";
    }
}

/** Test rule #2
 *  Description: Delete flights where segments with arrival date earlier than departure date
 */
class SecondRule implements FlightRule {

    @Override
    public List<Flight> delIrrelevantFromList(List<Flight> flights) {
        List<Flight> result = new ArrayList<>(flights);
        result.removeIf(this::checkFlight);
        return result;
    }

    @Override
    public List<Flight> showIrrelevantFromList(List<Flight> flights) {
        return new ArrayList<>(flights).stream().filter(this::checkFlight).collect(Collectors.toList());
    }

    @Override
    public boolean checkFlight(Flight flight) {
        for (Segment segment:flight.getSegments()) {
            if (segment.getDepartureDate().compareTo(segment.getArrivalDate()) >= 0) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String description() {
        return "Delete flights where segments with arrival date earlier than departure date";
    }
}

/** Test rule #2
 *  Description: Delete flights where, ground time more than 2 hours
 */

class ThirdRule implements FlightRule {

    @Override
    public List<Flight> delIrrelevantFromList(List<Flight> flights) {
        List<Flight> result = new ArrayList<>(flights);
        result.removeIf(this::checkFlight);
        return result;
    }

    @Override
    public List<Flight> showIrrelevantFromList(List<Flight> flights) {
        return new ArrayList<>(flights).stream().filter(this::checkFlight).collect(Collectors.toList());
    }

    @Override
    public boolean checkFlight(Flight flight) {
        List<Segment> segments = flight.getSegments();
        int groundTime = 0;
        for (int i = 0; i < segments.size()-1; i++) {
            groundTime -= segments.get(i).getArrivalDate().getHour() -
                    segments.get(i+1).getDepartureDate().getHour();
        }
        if (groundTime > 2) {
            return true;
        }
        return false;
    }

    @Override
    public String description() {
        return "Delete flights where, ground time more than 2 hours";
    }

}


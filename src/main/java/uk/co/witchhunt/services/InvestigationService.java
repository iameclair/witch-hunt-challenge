package uk.co.witchhunt.services;

import uk.co.witchhunt.models.FlightMap;
import uk.co.witchhunt.models.Witch;

import java.util.List;

/**
 * Created by ${Eclair} on 5/13/2019.
 */
public interface InvestigationService {
    /**
     * this method gets directions from the forensics API.
     *
     * @return the directions
     */
    FlightMap getDirections();

    /**
     * Send search party to recover the kittens by giving them the location of the witch lair.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the message from the search party
     */
    String sendSearchParty(int x, int y);

    /**
     * this method follows the flight coordinates to locate the witch location
     * to Find kittens.
     *
     * @param witch          the witch
     * @param flightEvidence the flight evidence map detailing the route taken by the witch
     * @return the witch object with updated x and y coordinate of the witch
     */
    Witch guessKittensLocation(Witch witch, List<String> flightEvidence);
}

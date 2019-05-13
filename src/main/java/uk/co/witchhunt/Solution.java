package uk.co.witchhunt;

import uk.co.witchhunt.models.FlightMap;
import uk.co.witchhunt.models.Witch;
import uk.co.witchhunt.services.InvestigationService;
import uk.co.witchhunt.services.InvestigationServiceImpl;

/**
 * Created by ${Eclair} on 5/13/2019.
 */
public class Solution {
    public static void main(String[] args) {
        /*initialise the which with coordinates (0,0) facing North*/
        final Witch witch = new Witch();
        /*get the directions from the investigationService*/
        InvestigationService investigationService = new InvestigationServiceImpl();
        /*get the flight map from investigationService api*/
        FlightMap flightMap = investigationService.getDirections();
        /*get guess the location of the kittens*/
        Witch kittensLocation = investigationService.guessKittensLocation(witch, flightMap.getDirections());
        /*send search party to go find the kittens*/
        String message = investigationService.sendSearchParty(kittensLocation.getX(), kittensLocation.getY());
        System.out.println(message);
    }
}

package uk.co.witchhunt.services;

import uk.co.witchhunt.models.FacingDirection;
import uk.co.witchhunt.models.FlightMap;
import uk.co.witchhunt.models.SearchParty;
import uk.co.witchhunt.models.Witch;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by ${Eclair} on 5/13/2019.
 */
public class InvestigationServiceImpl implements InvestigationService {
    private final Client client = ClientBuilder.newClient();
    private final String baseUrl = "http://which-technical-exercise.herokuapp.com";
    private final String EMAIL = "eclairlumu@gmail.com";
    private final WebTarget webTarget = client.target(baseUrl);

    @Override
    public FlightMap getDirections(){
        try{
            final WebTarget directionWebTarget = this.webTarget.path("/api/"+this.EMAIL+"/directions");
            final Invocation.Builder request = directionWebTarget.request(MediaType.APPLICATION_JSON);
            return request.get(FlightMap.class);
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public String sendSearchParty(int x, int y) {
        try {
            final WebTarget locationWebTarget = this.webTarget.path("/api/"+this.EMAIL+"/location/"+x+"/"+y);
            final Invocation.Builder request = locationWebTarget.request(MediaType.APPLICATION_JSON);
            final SearchParty response = request.get(SearchParty.class);
            return response.getMessage();
        }catch (Exception e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public Witch guessKittensLocation(Witch witch, List<String> flightEvidence) {
        for (String aFlightEvidence : flightEvidence) {
            final FacingDirection currentFacingDirection = witch.getFacingDirection();
            move(currentFacingDirection, aFlightEvidence, witch);
        }
        return witch;
    }

    private void move(FacingDirection currentFacingDirection, String relativeMovingDirection, Witch witch) {
        switch (currentFacingDirection){
            case NORTH:
                if(relativeMovingDirection.equalsIgnoreCase("forward")){
                    witch.setY(witch.getY()+1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("backward")){
                    witch.setY(witch.getY()-1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("right")){
                    updateFacingDirection(witch, FacingDirection.EAST);
                }
                if(relativeMovingDirection.equalsIgnoreCase("left")){
                    updateFacingDirection(witch, FacingDirection.WEST);
                }
                break;
            case SOUTH:
                if(relativeMovingDirection.equalsIgnoreCase("forward")){
                    witch.setY(witch.getY()-1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("backward")){
                    witch.setY(witch.getY()+1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("right")){
                    updateFacingDirection(witch, FacingDirection.WEST);
                }
                if(relativeMovingDirection.equalsIgnoreCase("left")){
                    updateFacingDirection(witch, FacingDirection.EAST);
                }
                break;
            case EAST:
                if(relativeMovingDirection.equalsIgnoreCase("forward")){
                    witch.setX(witch.getX()+1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("backward")){
                    witch.setX(witch.getX()-1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("right")){
                    updateFacingDirection(witch, FacingDirection.SOUTH);
                }
                if(relativeMovingDirection.equalsIgnoreCase("left")){
                    updateFacingDirection(witch, FacingDirection.NORTH);
                }
                break;
            case WEST:
                if(relativeMovingDirection.equalsIgnoreCase("forward")){
                    witch.setX(witch.getX()-1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("backward")){
                    witch.setX(witch.getX()+1);
                }
                if(relativeMovingDirection.equalsIgnoreCase("right")){
                    updateFacingDirection(witch, FacingDirection.NORTH);
                }
                if(relativeMovingDirection.equalsIgnoreCase("left")){
                    updateFacingDirection(witch, FacingDirection.SOUTH);
                }
            break;
        }
    }

    private void updateFacingDirection(final Witch witch, final FacingDirection newFacingDirection) {
        witch.setFacingDirection(newFacingDirection);
    }
}

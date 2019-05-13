package uk.co.witchhunt.models;

import static uk.co.witchhunt.models.FacingDirection.NORTH;

/**
 * Created by ${Eclair} on 5/13/2019.
 */
public class Witch {
    private int x;
    private int y;
    private FacingDirection facingDirection;

    public Witch(){
        this.x = 0;
        this.y = 0;
        this.facingDirection = NORTH;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public FacingDirection getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(FacingDirection facingDirection) {
        this.facingDirection = facingDirection;
    }
}

package days.day12;

public class Ferry {

    int north = 0, south = 0, west = 0, east = 0;
    String facingDirection = "E";
    int degrees = 90;


    public void doAction(String action, int value) {
        if("NSEW".contains(action)) {
            moveFerry(action, value);
        } else if(action.equals("F")) {
            moveFerry(facingDirection, value);
        } else if ( "LR".contains(action)) {
            changeDirection(action, value);
        }
    }

    public void moveFerry(String location, int value) {
        if(location.equals("N")) {
            north += value;
        } else if(location.equals("E")) {
            east += value;
        } else if(location.equals("S")) {
            south += value;
        } else if(location.equals("W")) {
            west += value;
        }
    }

    public void changeDirection(String direction, int degrees) {
        if(direction.equals("R")) {
            this.degrees += degrees;
        } else {
            this.degrees -= degrees;
        }

        if(this.degrees >= 360) {
            this.degrees -= 360;
        }

        if(this.degrees < 0) {
            this.degrees += 360;
        }

        if(this.degrees == 0) {
            facingDirection = "N";
        } else if(this.degrees == 90) {
            facingDirection = "E";
        } else if(this.degrees == 180) {
            facingDirection = "S";
        } else if(this.degrees == 270) {
            facingDirection = "W";
        }
    }

    public int getLocation() {
        return Math.abs(north - south) + Math.abs(east - west);
    }

}



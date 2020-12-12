package days.day12;

public class FerryB {

    long shipNorth = 0, shipSouth = 0, shipWest = 0, shipEast = 0;

    long wayPointNorth = 1, wayPointSouth = 0, wayPointEast = 10, wayPointWest = 0;

    public void doAction(String action, int value) {
        if("NSEW".contains(action)) {
            moveWaypoint(action, value);
        } else if(action.equals("F")) {
            moveFerryToWayPoint(value);
        } else if ( "LR".contains(action)) {
            changeDirection(action, value);
        }
    }

    public long getLocation() {
        return Math.abs(shipNorth - shipSouth) + Math.abs(shipEast - shipWest);
    }

    private void moveFerryToWayPoint(int amountOfTimes) {

        if ((wayPointNorth-wayPointSouth) > 0) {
            shipNorth += (amountOfTimes * (wayPointNorth-wayPointSouth));
        } else {
            shipSouth += (amountOfTimes * (wayPointSouth-wayPointNorth));
        }

        if ((wayPointEast-wayPointWest) > 0) {
            shipEast += (amountOfTimes * (wayPointEast-wayPointWest));
        } else {
            shipWest += (amountOfTimes * (wayPointWest-wayPointEast));
        }
    }

    private void moveWaypoint(String location, int value) {
        if(location.equals("N")) {
            wayPointNorth += value;
        } else if(location.equals("E")) {
            wayPointEast += value;
        } else if(location.equals("S")) {
            wayPointSouth += value;
        } else if(location.equals("W")) {
            wayPointWest += value;
        }
    }

    private void changeDirection(String direction, int degrees) {
        int positionSteps = degrees / 90; // getting 1 step, 2 steps, 3 steps etc.
        long tempWayPointNorth = 0, tempWayPointSouth = 0, tempWayPointEast = 0, tempWayPointWest = 0;

        if(direction.equals("R")) {
            if(positionSteps == 1) {
                tempWayPointEast = wayPointNorth;
                tempWayPointSouth = wayPointEast;
                tempWayPointWest = wayPointSouth;
                tempWayPointNorth = wayPointWest;
            } else if(positionSteps == 2) {
                tempWayPointSouth = wayPointNorth;
                tempWayPointWest = wayPointEast;
                tempWayPointNorth = wayPointSouth;
                tempWayPointEast = wayPointWest;
            } else if(positionSteps == 3) {
                tempWayPointWest = wayPointNorth;
                tempWayPointNorth = wayPointEast;
                tempWayPointEast = wayPointSouth;
                tempWayPointSouth = wayPointWest;
            }
        } else if(direction.equals("L")) {
            if(positionSteps == 1) {
                tempWayPointWest = wayPointNorth;
                tempWayPointNorth = wayPointEast;
                tempWayPointEast = wayPointSouth;
                tempWayPointSouth = wayPointWest;
            } else if(positionSteps == 2) {
                tempWayPointSouth = wayPointNorth;
                tempWayPointWest = wayPointEast;
                tempWayPointNorth = wayPointSouth;
                tempWayPointEast = wayPointWest;
            } else if(positionSteps == 3) {
                tempWayPointEast = wayPointNorth;
                tempWayPointSouth = wayPointEast;
                tempWayPointWest = wayPointSouth;
                tempWayPointNorth = wayPointWest;
            }
        }

        wayPointWest = tempWayPointWest;
        wayPointEast = tempWayPointEast;
        wayPointNorth = tempWayPointNorth;
        wayPointSouth = tempWayPointSouth;

    }
}

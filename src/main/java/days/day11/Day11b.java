package days.day11;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11b {

    private Utils utils = new Utils();
    ArrayList<String> planeSeats = new ArrayList<>();
    List<List<Integer>> lookUpAdjustSeats = new ArrayList<>();
    List<Coordinate> coordinateList = new ArrayList<>();

    int rowLength = 0;
    int rows = 0;

    public Day11b() throws FileNotFoundException, URISyntaxException {
        coordinateList.add(new Coordinate(-1, -1));
        coordinateList.add(new Coordinate(0, -1));
        coordinateList.add(new Coordinate(1, -1));

        coordinateList.add(new Coordinate(-1, 0));
        // we wont use this one
        coordinateList.add(new Coordinate(0, 0));
        coordinateList.add(new Coordinate(1, 0));

        coordinateList.add(new Coordinate(-1, 1));
        coordinateList.add(new Coordinate(0, 1));
        coordinateList.add(new Coordinate(1, 1));
        Scanner scanner = utils.getScannerFromFileName("Day11.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            rowLength = line.length();

            for(char ch : line.toCharArray()) {

                planeSeats.add("" + ch);

            }
            rows++;
        }

        int y = 0, x = 0;
        for(int i = 0; i < planeSeats.size(); i++) {
                lookUpAdjustSeats.add(getAdjustSeat(y, x));
                if( x == rowLength-1) {
                    x = 0;
                    y++;
                } else {
                    x++;
                }
        }
        System.out.println("Day11 B = " + changeSeats(0, planeSeats));
    }


    private int changeSeats(int occupiedSeats, ArrayList<String> localPlaneSeats) {

        ArrayList<String> changedSeats = new ArrayList<>(localPlaneSeats);

        for( int i = 0; i < localPlaneSeats.size(); i++) {
            String seat = localPlaneSeats.get(i);
            if( seat.equals("L")) {
                int adjustSeatCounter = 0;

                List<Integer> adjustSeats = lookUpAdjustSeats.get(i);

                for( Integer seatId : adjustSeats) {
                    if(localPlaneSeats.get(seatId).equals("#")) {
                        adjustSeatCounter++;
                    }
                }

                if(adjustSeatCounter == 0) {
                    changedSeats.set(i, "#");
                }
                // check to see if we can make this seat occupied
            } else if( seat.equals("#")) {
                int adjustSeatCounter = 0;
                // Check all n
                List<Integer> adjustSeats = lookUpAdjustSeats.get(i);

                for( Integer seatId : adjustSeats) {
                    if(localPlaneSeats.get(seatId).equals("#")) {
                        adjustSeatCounter++;
                    }
                }

                if(adjustSeatCounter >= 5) {
                    changedSeats.set(i, "L");
                }
            }
        }
        int seatCounter = 0;
        for(String seat : changedSeats) {
            if(seat.equals("#")) {
                seatCounter++;
            }
        }

        if(seatCounter != occupiedSeats) {
            return changeSeats(seatCounter, changedSeats);
        }
        return seatCounter;
    }

    private List<Integer> getAdjustSeat(int row, int rowSeat){
        List<Integer> adjustSeats = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            if( i != 4) {
                Coordinate coordinate = coordinateList.get(i);
                boolean resultFound = false;
                int tryCounter = 1;
                while (!resultFound) {
                    //System.out.println("tryCounter*coordinate.getY()) +row = " + (tryCounter*coordinate.getY()) +row);
                    //System.out.println("(tryCounter*coordinate.getX()) +rowSeat)" + (tryCounter*coordinate.getX()) +rowSeat);
                    if(isSeatInRow((tryCounter*coordinate.getY()) +row, (tryCounter*coordinate.getX()) +rowSeat)) {
                        int adjustIndexSeat = getSeat((tryCounter*coordinate.getY()) +row, (tryCounter*coordinate.getX()) +rowSeat);
                        //System.out.println("Seat index found in grid! " + adjustIndexSeat);
                        //System.out.println("Seat found = " + planeSeats.get(adjustIndexSeat));
                        if(!planeSeats.get(adjustIndexSeat).equals(".")) {
                            adjustSeats.add(adjustIndexSeat);
                            resultFound = true;
                        }
                    } else {
                        //End of the row, also a result but wont add a seat to the list.
                        resultFound = true;
                    }

                    tryCounter++;
                }
            }
        }

        return adjustSeats;
    }

    private int getSeat(int row, int indexInRow) {
        if(row >= 0 && row < rows && indexInRow >= 0 && indexInRow < rowLength) {
            return (row * rowLength) + indexInRow;
        }

        return -1;
    }

    private boolean isSeatInRow(int row, int indexInRow) {
        if(row >= 0 && row < rows && indexInRow >= 0 && indexInRow < rowLength) {
            return true;
        }

        return false;
    }
}

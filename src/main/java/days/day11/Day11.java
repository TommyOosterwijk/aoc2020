package days.day11;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day11 {

    private Utils utils = new Utils();
    ArrayList<String> planeSeats = new ArrayList<>();
    List<List<Integer>> lookUpAdjustSeats = new ArrayList<>();
    int rowLength = 0;

    public Day11() throws FileNotFoundException, URISyntaxException {

        int x = 0, y = 0;
        Scanner scanner = utils.getScannerFromFileName("Day11.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            rowLength = line.length();

            for(char ch : line.toCharArray()) {

                planeSeats.add("" + ch);
                lookUpAdjustSeats.add(getAdjustSeat(y, x));

                x++;
            }
            y++;
            x=0;
        }

        //remove fake row (last row that never came)
        for(int i = rowLength * (y-1); i < rowLength * y; i++) {
            List<Integer> lastRowList = lookUpAdjustSeats.get(i);
            List<Integer> removeValues = new ArrayList<>();

            for(int lastRowIndex = 0; lastRowIndex < lastRowList.size(); lastRowIndex++) {
                if(lastRowList.get(lastRowIndex) >= rowLength * y) {
                    removeValues.add(lastRowList.get(lastRowIndex));
                }
            }
            lastRowList.removeAll(removeValues);
        }

        System.out.println("Day11 = " + changeSeats(0, planeSeats));
        Day11b day11b = new Day11b();
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

                if(adjustSeatCounter >= 4) {
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

    private List<Integer> getAdjustSeat(int row, int indexInRow){
        //Start leftabove in the 3x3 grid instead of center
        int adjustRow = row - 1;
        int adjustIndex = indexInRow - 1;

        List<Integer> adjustSeats = new ArrayList<>();

        for(int i = adjustRow; i < adjustRow+3; i++) {
            for( int x = adjustIndex; x < adjustIndex+3; x++) {
                if(!(indexInRow == x && row == i) && isSeatInRow(i, x)) {
                    adjustSeats.add(getSeat(i,x));
                }

            }
        }

        return adjustSeats;
    }

    private int getSeat(int row, int indexInRow) {
        if(row >= 0 && indexInRow >= 0 && indexInRow < rowLength) {
            return (row * rowLength) + indexInRow;
        }

        return -1;
    }

    private boolean isSeatInRow(int row, int indexInRow) {
        if(row >= 0 && indexInRow >= 0 && indexInRow < rowLength) {
            return true;
        }

        return false;
    }
}

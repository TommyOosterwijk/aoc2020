package days.day5;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day5 {

    private Utils utils = new Utils();
    ArrayList<BoardingPass> boardingPassList = new ArrayList<>();

    public Day5() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day5.txt");

        while (scanner.hasNextLine()) {
            BoardingPass tempPass = new BoardingPass();
            tempPass.setBoardingPassID(scanner.nextLine());
            boardingPassList.add(tempPass);
        }

        Collections.sort(boardingPassList, (pass1, pass2) -> pass1.getBinaryLeftSide().compareTo(pass2.getBinaryLeftSide()));
        String highestLeftSide = boardingPassList.get(0).getBinaryLeftSide();
        boardingPassList.removeIf(pass -> !pass.getBinaryLeftSide().equals(highestLeftSide));

        Collections.sort(boardingPassList, (pass1, pass2) -> pass2.getBinaryRightSide().compareTo(pass1.getBinaryRightSide()));
        calculateSeat(boardingPassList.get(0));

        Day5b day5b = new Day5b();
    }

    private void calculateSeat(BoardingPass bp) {
        bp.setRow(calculateBinary(0, 127, 'B', bp.getBinaryLeftSide().substring(0, bp.getBinaryLeftSide().length()-1), bp.getBinaryLeftSide().charAt(6)));
        bp.setColumn(calculateBinary(0, 7, 'R', bp.getBinaryRightSide().substring(0, bp.getBinaryRightSide().length()-1), bp.getBinaryRightSide().charAt(2)));
        bp.setSeatID((bp.getRow() * 8) + bp.getColumn());

        System.out.println("Day5 = " + bp.getSeatID());
    }

    private int calculateBinary(int min, int max, char maxChar, String value, char lastChar) {
        for(char ch : value.toCharArray()) {
            if(ch == maxChar) {
                min = min + ((max+1-min) / 2);
            } else {
                max = max - ((max+1-min) / 2);
            }
        }

        if(lastChar == maxChar) {
            return max;
        } else {
            return min;
        }
    }
}

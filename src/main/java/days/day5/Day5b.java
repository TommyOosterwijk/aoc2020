package days.day5;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Day5b {

    private Utils utils = new Utils();
    ArrayList<BoardingPass> boardingPassList = new ArrayList<>();
    ArrayList<String> leftSide = new ArrayList<>();
    ArrayList<String> columnOptions = new ArrayList<>(
            List.of("RRR",
                    "RRL",
                    "RLR",
                    "RLL",
                    "LRR",
                    "LRL",
                    "LLR",
                    "LLL"));
    ArrayList<String> columnsFound = new ArrayList<>();

    public Day5b() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day5.txt");

        while (scanner.hasNextLine()) {
            BoardingPass tempPass = new BoardingPass();
            tempPass.setBoardingPassID(scanner.nextLine());
            boardingPassList.add(tempPass);
            leftSide.add(tempPass.getBinaryLeftSide());

        }

        Collections.sort(leftSide);
        String frontSeats = leftSide.get(0);
        String backSeats = leftSide.get(leftSide.size()-1);
        //Filter first and last row
        leftSide.removeIf(s -> s.equals(frontSeats) || s.equals(backSeats));

        Map<String, Long > map = leftSide.stream().collect( Collectors.groupingBy(c ->c, Collectors.counting()));

        List<Map.Entry<String, Long>> list = map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByKey()).filter(c -> c.getValue() != 8).collect(Collectors.toList());
        String leftSide = list.get(0).getKey();

        for(BoardingPass bp : boardingPassList) {
            if(bp.getBinaryLeftSide().equals(leftSide)) {
                columnsFound.add(bp.getBinaryRightSide());
            }
        }

        for(String option : columnOptions) {
            if(!columnsFound.contains(option)) {
                BoardingPass lastOneAndDuplicate = new BoardingPass();
                lastOneAndDuplicate.setBoardingPassID(leftSide+option);
                calculateSeat(lastOneAndDuplicate);
            }
        }
    }

    private void calculateSeat(BoardingPass bp) {
        bp.setRow(calculateBinary(0, 127, 'B', bp.getBinaryLeftSide().substring(0, bp.getBinaryLeftSide().length()-1), bp.getBinaryLeftSide().charAt(6)));
        bp.setColumn(calculateBinary(0, 7, 'R', bp.getBinaryRightSide().substring(0, bp.getBinaryRightSide().length()-1), bp.getBinaryRightSide().charAt(2)));
        bp.setSeatID((bp.getRow() * 8) + bp.getColumn());

        System.out.println("Day5b = " + bp.getSeatID());
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

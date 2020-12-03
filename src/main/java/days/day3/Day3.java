package days.day3;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {

    private Utils utils = new Utils();
    ArrayList<ArrayList<Integer>> grid = new ArrayList<>();

    public Day3() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day3.txt");

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ArrayList<Integer> gridLine = new ArrayList<>();

            for(char c : line.toCharArray()) {
                if(c == '.') {
                    gridLine.add(0); // open is 0
                } else if (c == '#') {
                    gridLine.add(1); // tree is 1
                }
            }
            grid.add(gridLine);
        }

        System.out.println("Result countTreesA = " +countTrees(3, 1));
        System.out.println("Result countTreesB = " +countTreesB());
    }


    private int countTrees(int right, int down) {
        int treesFound = 0;
        int maxPosX = grid.get(0).size() -1;
        int posX = 0;

        for( int i = down; i < grid.size(); i += down) {
            posX += right;
            if(posX > maxPosX) {
                posX = posX - maxPosX - 1;
            }

            if(grid.get(i).get(posX) == 1) {
                treesFound++;
            }
        }

        return treesFound;
    }

    private long countTreesB() {
        long result = countTrees(1, 1);
        result *= countTrees(3, 1);
        result *= countTrees(5, 1);
        result *= countTrees(7, 1);
        result *= countTrees(1, 2);
        return result;
    }
}

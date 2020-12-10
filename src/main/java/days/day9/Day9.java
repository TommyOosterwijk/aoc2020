package days.day9;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day9 {

    private Utils utils = new Utils();
    ArrayList<Integer> xmasData = new ArrayList<>();


    public Day9() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day9.txt");
        while (scanner.hasNextInt()) {
            xmasData.add(scanner.nextInt());

        }

        findFirstInvalidNumber(5);
        findTargetNumber(731031916);
    }

    public void findFirstInvalidNumber(int preambleSize) {
        for( int i = preambleSize; i < xmasData.size(); i++) {
            boolean isValidNumber = false;
            int target = xmasData.get(i);

            for(int x = i - preambleSize; x < i; x++) {
                int numberX = xmasData.get(x);

                for( int y = x+1; y < i; y++){
                    if(numberX + xmasData.get(y) == target) {
                        isValidNumber = true;
                        break;
                    }
                }

                if(isValidNumber) {
                    break;
                }
            }
            if(!isValidNumber) {
                System.out.println("Day9 first invalid number = " + xmasData.get(i));
                break;
            }
        }
    }

    public void findTargetNumber(Integer target) {
        for(int numberSize = 2; numberSize < xmasData.size(); numberSize++) {
            boolean solutionFound = false;
            ArrayList<Integer> numberList = new ArrayList<>();

            for(int i =0; i < xmasData.size() - numberSize; i++) {
                int sum = 0;

                for(int x = i; x < i+numberSize; x++) {
                    sum += xmasData.get(x);
                    numberList.add(xmasData.get(x));
                }
                if( sum == target) {
                    Collections.sort(numberList);

                    System.out.println("Day9b = " + (numberList.get(0) + numberList.get(numberSize-1)));
                    solutionFound = true;
                    break;
                }
                numberList.clear();
            }
            if(solutionFound) {
                break;
            }
        }
    }
}

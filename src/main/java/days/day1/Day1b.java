package days.day1;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1b {

    Utils utils = new Utils();
    ArrayList<Integer> highNumbers = new ArrayList<>();
    ArrayList<Integer> lowNumbers = new ArrayList<>();

    public int expenseReport(int target) throws FileNotFoundException, URISyntaxException {
        int result = 0;

        Scanner scanner = utils.getScannerFromFileName("Day1.txt");

        while (scanner.hasNextInt()) {
            int tempInt = scanner.nextInt();
            if (tempInt >= 1000) {
                highNumbers.add(tempInt);
            } else {
                lowNumbers.add(tempInt);
            }
        }

        Collections.sort(highNumbers);
        Collections.sort(lowNumbers);

        for (int i = 0; i < lowNumbers.size(); i++) {
            int tempLow = lowNumbers.get(i);
            for (int i2 = i + 1; i2 < lowNumbers.size(); i2++) {
                int tempLow2 = lowNumbers.get(i2);
                for (int x = 0; x < highNumbers.size(); x++) {
                    int tempHigh = highNumbers.get(x);
                    if (tempLow + tempLow2 + tempHigh == target) {
                        return tempLow * tempHigh * tempLow2;
                    } else if (tempLow + tempHigh > target) {
                        break;
                    }
                }
            }
        }

        return result;
    }

}

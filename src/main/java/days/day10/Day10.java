package days.day10;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day10 {

    private Utils utils = new Utils();
    ArrayList<Integer> adapters = new ArrayList<>();
    int oneJolt =0, twoJolt =0, threeJolt = 1;


    public Day10() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day10.txt");
        while (scanner.hasNextInt()) {
            adapters.add(scanner.nextInt());
        }

        Collections.sort(adapters);

        addAdapterJointToCount(adapters.get(0));

        for(int i = 0; i < adapters.size()-1; i++) {
            if(!(adapters.get(i)+3 >= adapters.get(i+1))) {
                break;
            } else {
                int difference = adapters.get(i+1) - adapters.get(i);
                addAdapterJointToCount(difference);
            }
        }
        System.out.println("Day 10 =" + (oneJolt * threeJolt));

    }

    private void addAdapterJointToCount(int jointDifference) {
        switch (jointDifference) {
            case 1:
                oneJolt++;
                break;
            case 2:
                twoJolt++;
                break;
            case 3:
                threeJolt++;
                break;
        }
    }
}

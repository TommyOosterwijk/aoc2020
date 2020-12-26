package days.day13;

import utils.Utils;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Day13b {

    private Utils utils = new Utils();
    ArrayList<Bus> busList = new ArrayList<>();
    ArrayList<BigDecimal> provenValues = new ArrayList<>();

    public Day13b() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day13.txt");
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(firstLine) {
                firstLine = false;
            } else {
                shuttleC(line);
                //shuttleBruteForce(line);
            }
        }
    }

    private void shuttleC(String line) {
        int indexCounter = 0;

        for(String stringID : line.split(",")) {
            if(!stringID.equals("x")) {
                int id = Integer.parseInt(stringID);
                Bus bus = new Bus(indexCounter, id);
                busList.add(bus);
            }
            indexCounter++;
        }

        long departureTime = 0l;
        long LCM = 1l;

        for(Bus bus : busList) {
            for(long i = departureTime;; i+=LCM) {
                if( (i+ bus.getIndex()) % bus.getId() == 0) {
                    departureTime = i;
                    break;
                }
            }
            LCM *= bus.getId();
        }

        System.out.println("Day13 B = " + departureTime);

    }
}

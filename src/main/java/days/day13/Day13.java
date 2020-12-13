package days.day13;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Day13 {

    private Utils utils = new Utils();
    int lowestTimeToWaitForBusID;
    long lowestTimeToWaitForBus = 10000000l;

    long timestamp;


    public Day13() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day13.txt");
        boolean firstLine = true;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(firstLine) {
                timestamp = Integer.parseInt(line);
                firstLine = false;
            } else {
                line = line.replaceAll("x,", "");
                for(String stringID : line.split(",")) {
                    int id = Integer.parseInt(stringID);
                    long dividedBy =  timestamp / id;
                    long timeToWait = ((dividedBy+1) * id) - timestamp;
                    if( timeToWait < lowestTimeToWaitForBus) {
                        lowestTimeToWaitForBus = timeToWait;
                        lowestTimeToWaitForBusID = id;
                    }
                }
            }
        }
        System.out.println("Day13 = " + (lowestTimeToWaitForBus * lowestTimeToWaitForBusID));
    }
}

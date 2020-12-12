package days.day12;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Day12 {

    private Utils utils = new Utils();
    private Ferry ferry = new Ferry();
    private FerryB ferryb = new FerryB();


    public Day12() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day12.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ferry.doAction(line.substring(0,1), Integer.parseInt(line.substring(1)));
            ferryb.doAction(line.substring(0,1), Integer.parseInt(line.substring(1)));

        }

        System.out.println("Day12 = " + ferry.getLocation());
        System.out.println("Day12B = " + ferryb.getLocation());
    }
}

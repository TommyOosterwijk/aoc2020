package days;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day15 {

    private Utils utils = new Utils();
    Map<Long, Integer> memory = new HashMap<Long, Integer>();

    public Day15() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day15.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] stringNumbers = line.split(",");
            int[] numbers = new int[stringNumbers.length];
            for(int i = 0; i < stringNumbers.length; i++) {
                memory.put(Long.parseLong(stringNumbers[i]), i +1);
            }

        }
        playMemory(0l);
    }

    public void playMemory(long lastNumber) {
        for(int i = memory.size()+1; i < 30000000; i++) {
            if(!memory.containsKey(lastNumber)) {
                memory.put(lastNumber, i);
                lastNumber = 0;
            } else {
                long value = lastNumber;
                lastNumber = i - memory.get(lastNumber);
                memory.put(value, i);
            }
        }
        System.out.println("Day 15 = " + lastNumber);

    }
}

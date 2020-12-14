package days;

import utils.Utils;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day14 {

    private Utils utils = new Utils();
    Map<Integer, BigInteger> memory = new HashMap<Integer, BigInteger>();
    String[] mask = new String[36];



    public day14() throws FileNotFoundException, URISyntaxException {

        for(int i = 0; i < mask.length; i++) {
            // default
            mask[i] = "X";
        }

        Scanner scanner = utils.getScannerFromFileName("Day14.txt");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            line = line.replaceAll(" = ", " ");
            String[] action = line.split(" ");
            if(action[0].equals("mask")) {
                setMask(action[1]);
            } else if(action[0].contains("mem")) {

                int memoryLocation = Integer.parseInt(action[0].replaceAll("mem\\[", "").replaceAll("]",""));
                BigInteger preMaskValue = new BigInteger(action[1]);
                for(int i =0; i < mask.length; i++) {
                    if(mask[i].equals("1")) {
                        preMaskValue = preMaskValue.setBit(i);
                    } else if(mask[i].equals("0")) {
                        preMaskValue = preMaskValue.clearBit(i);
                    }
                }
                memory.put(memoryLocation, preMaskValue);
            }
        }
        BigInteger result = new BigInteger("0");

        for (BigInteger value : memory.values()) {
            result = result.add(value);
        }

        System.out.println(result);
        new day14b();
    }

    public void setMask(String incomingMask) {
        int counter = 0;
        for( int i = 35; counter < 36; i--) {
            char ch = incomingMask.charAt(i);
            mask[counter] = "" + ch;
            counter++;
        }
    }
}

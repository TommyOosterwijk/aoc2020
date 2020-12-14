package days;

import utils.Utils;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.*;

public class day14b {

    private Utils utils = new Utils();
    Map<BigInteger, BigInteger> memory = new HashMap<BigInteger, BigInteger>();
    String[] mask = new String[36];



    public day14b() throws FileNotFoundException, URISyntaxException {

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
                List<Integer> indexes = new ArrayList<>();
                BigInteger preMemoryLocation = new BigInteger(action[0].replaceAll("mem\\[", "").replaceAll("]",""));
                BigInteger value = new BigInteger(action[1]);

                //Mask memoryLocationTarget and set all possible outcomes with value.
                for(int i =0; i < mask.length; i++) {
                    if(mask[i].equals("1")) {
                        preMemoryLocation = preMemoryLocation.setBit(i);
                    } else if(mask[i].equals("X") ) {
                        preMemoryLocation = preMemoryLocation.clearBit(i);
                        indexes.add(i);
                    }
                }

                for(int i = 0; i < indexes.size(); i++) {

                }
                memoryMasking(indexes, preMemoryLocation, value);
                //memory.put(memoryLocation, preMaskValue);
            }
        }
        BigInteger result = new BigInteger("0");

        for (BigInteger value : memory.values()) {
            result = result.add(value);
        }

        System.out.println("Day14b = " + result);
    }

    public void memoryMasking(List<Integer> indexes, BigInteger maskingMemoryValue, BigInteger value) {
        if(indexes.size() > 0) {
            Integer index = indexes.get(0);
            indexes.remove(0);
            memoryMasking(new ArrayList<>(indexes), maskingMemoryValue.setBit(index), value);
            memoryMasking(new ArrayList<>(indexes), maskingMemoryValue.clearBit(index), value);
        } else {
            memory.put(maskingMemoryValue, value);
        }
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

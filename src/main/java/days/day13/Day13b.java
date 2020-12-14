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
            LCM = lcm(LCM, Long.valueOf((bus.getId())));
        }

        System.out.println("Day13 B = " + departureTime);

    }


    long gcd(Long a, Long b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    long lcm(Long a, Long b)
    {
        return (a / gcd(a, b)) * b;
    }

    private void shuttleBruteForce(String line) {
        for(String stringID : line.split(",")) {
            provenValues.add(new BigDecimal(0));
        }



        Collections.sort(busList, Comparator.comparing(Bus::getId));
        Collections.reverse(busList);

        boolean valueNotFound = true;
        BigDecimal firstValue = new BigDecimal(busList.get(0).getId());
        //BigDecimal highestTargetValue = new BigDecimal(startIndex);
        long counter = 0;
        BigDecimal highestTargetValue = firstValue;
        while(valueNotFound) {
            highestTargetValue = highestTargetValue.add(firstValue);
            BigDecimal lowestTargetValue = highestTargetValue;
            counter++;
            if(counter == 112931831l) {
                System.out.println("Highest target to find = " + highestTargetValue);
            counter = 0;
            }
            Boolean targetValueFound = true;

            for( int  i = 1; i < busList.size(); i++) {
                BigDecimal currentTarget = highestTargetValue.subtract(provenValues.get(i));
                Bus bus = busList.get(i);
//                System.out.println(busList.get(0).getIndex());
//                System.out.println(busList.get(i).getIndex());
//                System.out.println(busList.get(0).getIndex() - busList.get(i).getIndex());
//                System.out.println(currentTarget);
//                System.out.println("test " + currentTarget.subtract(new BigDecimal(3)));
                currentTarget = currentTarget.subtract(new BigDecimal(busList.get(0).getIndex() - bus.getIndex()));
                //System.out.println("New target for lower index = " + currentTarget);

                BigDecimal remainder = currentTarget.remainder(new BigDecimal(bus.getId()));
                if (BigDecimal.ZERO.compareTo(remainder) == 0) {
                    BigDecimal realValue = currentTarget.add(provenValues.get(i));
                    if(lowestTargetValue.compareTo(realValue) == 1) {
                        lowestTargetValue = realValue;
                    }
                    provenValues.set(i, currentTarget);
                    //System.out.println( currentTarget  + " can be divided by " + bus.getId());
                } else {
                    targetValueFound = false;
                    break;
                }
            }

            if( targetValueFound) {
                System.out.println("Day13B = " + lowestTargetValue);
                valueNotFound = false;
            }
        }
    }
}

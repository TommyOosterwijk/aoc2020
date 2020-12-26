package days.day25;

import java.math.BigInteger;

public class Day25 {
    BigInteger publicDoorKey = new BigInteger("10441485");
    BigInteger publicCardKey = new BigInteger("1004920");
    BigInteger modulo = new BigInteger("20201227");


    public void getEncryptionKey() {
        int loops = getLoopCounter();
        getEncrpytionValue(loops);
    }

    private int getLoopCounter() {
        int counter =0;

        BigInteger value = new BigInteger("1");
        BigInteger subject = new BigInteger("7");

        while(true) {
            counter++;

            value = value.multiply(subject);

            value = value.mod(modulo);

            if(value.equals(publicDoorKey)) {
                break;
            }
        }
        return counter;
    }

    private void getEncrpytionValue(int loops) {
        BigInteger value = new BigInteger("1");

        for(int i = 0; i < loops; i++) {

            value = value.multiply(publicCardKey);

            value = value.mod(modulo);
        }

        System.out.println("Day25 = " + value);
    }
}

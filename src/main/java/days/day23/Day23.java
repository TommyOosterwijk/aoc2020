package days.day23;

import java.util.ArrayList;

public class Day23 {
    //String entry = "389125467";
    String entry = "247819356";
    //Integer[] leftSide = new Integer[1000001];
    Integer[] rightSide = new Integer[1000001];
    int playValue = 0;

    //Day23B = 12621748849
    //time = 3228078256

    public Day23() {
        System.out.println();
        System.out.println();
        System.out.println();

        // this will stay blank
        //leftSide[0] = 0;
        rightSide[0] = 0;

        playValue = Integer.parseInt(entry.charAt(0) + "");
        int previousValue = Integer.parseInt(entry.charAt(0) + "");
        for(int i = 1; i < entry.length(); i++) {
            //leftSide[Integer.parseInt(entry.charAt(i) + "")] = previousValue;
            rightSide[previousValue] = Integer.parseInt(entry.charAt(i) + "");
            previousValue = Integer.parseInt(entry.charAt(i) + "");
        }

        for(int i = 10; i <= 1000000; i++) {
            //leftSide[i] = previousValue;
            rightSide[previousValue] = i;
            previousValue = i;
        }
        //leftSide[playValue] = previousValue;
        rightSide[previousValue] = playValue;

        playGame();
    }

    private void playGame() {
        long startTime = System.nanoTime();

        for(int i = 1; i <= 10000000; i++) {

            //System.out.println("-- move " + i + " --");

            //System.out.println("current cub: " +playValue);
            int cup1 = rightSide[playValue];
            int cup2 = rightSide[cup1];
            int cup3 = rightSide[cup2];

            int destination = playValue - 1;
            if(destination == 0) {
                destination =rightSide.length-1;
            }
            while(destination == cup1 || destination == cup2 || destination == cup3) {
                destination--;
                if(destination == 0) {
                    destination =rightSide.length-1;
                }
            }

//            System.out.println("Pickup: " + cup1 + ", " + cup2 + ", " + cup3);
//            System.out.println("Destination: " + destination);

            int rightSideCup3 = rightSide[cup3];
            int leftSideCup1 = //leftSide[cup1];

            rightSide[playValue]= rightSideCup3;
            //leftSide[rightSideCup3] = leftSideCup1;

            int endIndex = rightSide[destination];
            rightSide[destination] = cup1;
            //leftSide[cup1] = destination;

            rightSide[cup3] = endIndex;
            //leftSide[endIndex] = cup3;
            playValue = rightSide[playValue];
        }



        //System.out.println("Final cubs " + cubs);
        //day23A();
        day23B();
        long stopTime = System.nanoTime();
        System.out.println("time = " + (stopTime - startTime));
    }

    private void day23A() {
        int value = rightSide[1];
        for(int i =1; i < 9; i++) {
            System.out.print(value);
            value = rightSide[value];
        }
        System.out.println();
    }

    private void day23B() {
        long value = rightSide[1];
        value *= rightSide[rightSide[1]];
        System.out.println("Day23B = " + value);
    }


}

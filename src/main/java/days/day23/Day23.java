package days.day23;

import java.util.ArrayList;

public class Day23 {
    String entry = "247819356";
    int maxValue = 9;
    ArrayList<Integer> cubs = new ArrayList<>();


    public Day23() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for(int i = 0; i < entry.length(); i++) {
            cubs.add(Integer.parseInt(entry.charAt(i) + ""));
        }
//        for(int i = 10; i <= 100; i++) {
//            cubs.add(i);
//        }
        maxValue = cubs.size();
        playGame();
        new Day23Rework2();
    }

    private void playGame() {
        int index = 0;
        long startTime = System.nanoTime();

        for(int i = 1; i <= 100; i++) {
            int currentCub = cubs.get(index);
            printCubsStack();
            //System.out.println("Current cub = " + currentCub);
            ArrayList<Integer> roundCubs = new ArrayList<>();
            roundCubs.add(cubs.get(getValidIndex(index+1)));
            roundCubs.add(cubs.get(getValidIndex(index+2)));
            roundCubs.add(cubs.get(getValidIndex(index+3)));
            //System.out.println("pick up: " + roundCubs);

            cubs.removeAll(roundCubs);
//            System.out.println("cubs after removed = " + cubs);
            for(int i2= 1; i2 < maxValue; i2++) {
                int targetCub = getValidTargetValue(currentCub - i2);
                if(targetCub < 1) {
                    targetCub += maxValue;
                }

                if (cubs.contains(targetCub)) {
                    for(int x = 0; x < cubs.size(); x++) {
                        if(cubs.get(x) == targetCub) {
                            if(x < index) {
                                index += 3;
                                break;
                            }
                        }
                    }
                    //System.out.println("Destination: " + targetCub);
                    int targetIndex = cubs.indexOf(targetCub) + 1;
                    cubs.addAll(targetIndex, roundCubs);
                    int temp = 0;

                    index = getIndexBasedOnValue(currentCub);
                    break;
                }
            }
            index++;
            if(index >= cubs.size()) {
                index -= cubs.size();
            }
        }

        //System.out.println("Final cubs " + cubs);
        //day23A();
        long stopTime = System.nanoTime();
        System.out.println(stopTime - startTime);
        day23B();

        System.out.println(cubs);
    }

    private void day23B() {
        int index = cubs.indexOf(1);
        long value = 1;
        value = cubs.get(index+1) *  cubs.get(index+2);
        System.out.println("Day 23 B = "+ value);
    }

    private void day23A() {
        int index = cubs.indexOf(1);

        for(int i =1; i < maxValue; i++) {
            System.out.print(cubs.get(getValidIndex(index + i)));
        }
        System.out.println();
    }

    private int getIndexBasedOnValue(int value) {
        for(int i = 0; i < cubs.size(); i++) {
            if(cubs.get(i) == value) {
                return i;
            }
        }

        return 0;
    }

    private int getValidIndex(int index) {

        if(index >= maxValue) {
            index -= maxValue;
        }

        return index;
    }

    private int getValidTargetValue(int value) {
        if( value == 0) {
            value = maxValue;
        }

        return value;
    }

    private void printCubsStack() {
        //System.out.println(cubs);
    }
}

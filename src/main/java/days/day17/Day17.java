package days.day17;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day17 {
    int zSize = 0, ySize = 0, xSize = 0;

    private Utils utils = new Utils();
    ArrayList<ArrayList<ArrayList<String>>> z = new ArrayList<>();

    public Day17() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day17-example.txt");

        //Will always start with 1 demontion.
        ArrayList<ArrayList<String>> y = new ArrayList<>();
        while (scanner.hasNextLine()) {
            ArrayList<String> x = new ArrayList<>();
            String line = scanner.nextLine();
            for(char ch : line.toCharArray()) {
                x.add(ch + "");
            }
            y.add(x);
        }
        z.add(y);

        zSize = 1;
        ySize = z.get(0).size();
        xSize = z.get(0).get(0).size();

        printDimension(0);
        startCycle(1);
    }


    private void addDementionsWithInactiveCubes(int indexToCheck, int indexToAdd) {
        //add z= 0 if needed;
        ArrayList<ArrayList<String>> yList = z.get(indexToCheck);
        int activeCounter = 0;

        for(int y =0; y < yList.size(); y++){
            ArrayList<String> xList = yList.get(y);
            for(int x =0; x < xList.size(); x++){
                if(xList.get(x).equals("#")) {
                    activeCounter++;
                }
            }
        }

        if(activeCounter >= 3) {
            //add new deminion
            ArrayList<ArrayList<String>> newY = new ArrayList<>();
            for(int y =0; y < ySize; y++) {
                ArrayList<String> newX = new ArrayList<>();
                for(int x =0; x < xSize; x++) {
                    newX.add(".");
                }
                newY.add(newX);
            }
            z.add(indexToAdd, newY);
            zSize++;
        }

    }

    private void addYToActiveDementions(int indexToCheck, int indexToAdd) {
        int yCounter = 0;
        boolean addRow = false;

        for(int zIndex = 0; zIndex < zSize; zIndex++) {
            ArrayList<String> y = z.get(zIndex).get(indexToCheck);
            for(String string : y) {
                if(string.equals("#")) {
                    yCounter++;
                }
            }
            if(yCounter >= 3) {
                addRow = true;
                break;
            }
        }

        if(addRow){
            ArrayList<String> newRow = new ArrayList<>();
            for( int i = 0; i < xSize; i++) {
                newRow.add(".");
            }

            for( int zIndex = 0; zIndex < zSize; zIndex++){
                z.get(zIndex).add(indexToAdd, newRow);
            }
            ySize++;
        }
    }

    private void addXToActiveDementions(int indexToCheck, int indexToAdd) {
        int xCounter = 0;
        boolean addRow = false;

        for(int zIndex = 0; zIndex < zSize; zIndex++) {
            ArrayList<ArrayList<String>> yIndex = z.get(zIndex);
            for( int y = 0; y < ySize; y++) {
                if( yIndex.get(y).get(indexToCheck).equals("#")) {
                    xCounter++;
                }
            }

            if( xCounter >= 3) {
                addRow = true;
                break;
            }
        }
        if(addRow){
            ArrayList<ArrayList<String>> yList = z.get(0);
            for(int i = 0; i < z.size(); i++) {
                for(int y = 0; y < ySize; y++) {
                    z.get(i).get(y).add(indexToAdd, "!");
                }
            }

            System.out.println(z);

            xSize++;

//           for(int zIndex = 0; zIndex < zSize; zIndex++) {
//                List<List<String>> yIndex = new ArrayList(z.get(zIndex));
//                System.out.println("Z index " + zIndex + " has Y size " + ySize + "and xSize =" + xSize);
//               for( int y = 0; y < ySize; y++) {
//                   z.get(zIndex).get(y).add(indexToAdd, ".");
//               }
//            }
//            xSize++;
        }
    }

    public void printDimension(int counter) {
        System.out.println("Round =" + counter);
        for( int i = 0; i < z.size(); i++) {
            System.out.println("z="+ i);
            for(int y =0; y < z.get(i).size(); y++){
                for(int x =0; x < z.get(i).get(y).size(); x++){
                    System.out.print(z.get(i).get(y).get(x));
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void startCycle(int cycleCounter) {
        addDementionsWithInactiveCubes(0,0);
        addDementionsWithInactiveCubes(zSize-1,zSize);
        addYToActiveDementions(0,0);
        addYToActiveDementions(ySize-1, ySize);
        addXToActiveDementions(0,0);
        addXToActiveDementions(xSize-1, xSize);

        printDimension(cycleCounter);
        if(cycleCounter < 3) {
            //startCycle(cycleCounter + 1);
        }

        z.get(0).get(3).set(3, "Test");
        printDimension(cycleCounter);

    }
}

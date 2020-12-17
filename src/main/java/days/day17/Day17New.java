package days.day17;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day17New {

    private Utils utils = new Utils();
    ArrayList<Dimension> dimensions = new ArrayList<>();

    public Day17New() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day17.txt");

        Dimension dimension = new Dimension();

        while (scanner.hasNextLine()) {
            dimension.addYRow(null, false);
            String line = scanner.nextLine();
            for(char ch : line.toCharArray()) {
                dimension.addValue(dimension.ySize-1, null,ch + "");
            }
        }
        dimensions.add(dimension);

        printDimension(0);
        startCycle(1);
        System.out.println("Day17 = " + countActives());
    }

    private int countActives() {
        int counter = 0;
        for(int z = 0; z < dimensions.size(); z++){
            for(int y = 0; y < dimensions.get(z).xy.size(); y++) {
                for(int x = 0; x < dimensions.get(z).xy.get(y).size(); x++) {
                    if(dimensions.get(z).xy.get(y).get(x).equals("#")) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private void addDementionsWithInactiveCubes(int indexToCheck, int indexToAdd) {
        Dimension dimension = dimensions.get(indexToCheck);
        ArrayList<ArrayList<String>> xy = dimension.xy;
        int activeCounter = 0;

        for(int y =0; y < xy.size(); y++){
            ArrayList<String> xList = xy.get(y);
            for(int x =0; x < xList.size(); x++){
                if(xList.get(x).equals("#")) {
                    activeCounter++;
                }
            }
        }

        if(activeCounter >= 3) {
            //add new deminion
            Dimension newDimension = new Dimension(dimension.ySize, dimension.xSize);
            dimensions.add(indexToAdd, newDimension);
        }

    }

    private void addYToActiveDementions(int indexToCheck, int indexToAdd) {
        int yCounter = 0;
        boolean addRow = false;

        for(int d = 0; d < dimensions.size(); d++) {
            ArrayList<String> x = dimensions.get(d).xy.get(indexToCheck);
            for(String string : x) {
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
            for(int d = 0; d < dimensions.size(); d++) {
                Dimension dimension = dimensions.get(d);
                dimension.addYRow(indexToAdd, true);
            }
        }
    }

    private void addXToActiveDementions(int indexToCheck, int indexToAdd) {
        int xCounter = 0;
        boolean addRow = false;

        for(int d = 0; d < dimensions.size(); d++) {
            Dimension dimension = dimensions.get(d);
            ArrayList<ArrayList<String>> xy = dimension.xy;
            for( int y = 0; y < xy.size(); y++) {
                if( xy.get(y).get(indexToCheck).equals("#")) {
                    xCounter++;
                }
            }

            if( xCounter >= 3) {
                addRow = true;
                break;
            }
        }
        if(addRow){
            for(int d = 0; d < dimensions.size(); d++) {
                Dimension dimension = dimensions.get(d);
                ArrayList<ArrayList<String>> xy = dimension.xy;
                for (int y = 0; y < xy.size(); y++) {
                    dimension.addValue(y, indexToAdd, ".");
                }
            }
        }
    }

    public void printDimension(int counter) {
        System.out.println("Round =" + counter);
        for( int i = 0; i < dimensions.size(); i++) {
            ArrayList<ArrayList<String>> xy = dimensions.get(i).xy;
            System.out.println("z="+ i);
            for(int y =0; y < xy.size(); y++){
                for(int x =0; x < xy.get(y).size(); x++){
                    System.out.print(xy.get(y).get(x));
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void startCycle(int cycleCounter) {
        addDementionsWithInactiveCubes(0,0);
        addDementionsWithInactiveCubes(dimensions.size()-1,dimensions.size());
        addYToActiveDementions(0,0);
        addYToActiveDementions(dimensions.get(0).ySize-1, dimensions.get(0).ySize);
        addXToActiveDementions(0,0);
        addXToActiveDementions(dimensions.get(0).xSize-1, dimensions.get(0).xSize);

        printDimension(cycleCounter);
        calculateChanges();
        printDimension(cycleCounter);
        if(cycleCounter < 6) {
            startCycle(cycleCounter + 1);
        }
    }

    private void calculateChanges() {


        int dimensionsSize = dimensions.size();
        int maxX = 0, maxY= 0;
        int removingCounter = 0;
        int[][] toRemove = new int[10000][3];
        int addingCounter = 0;
        int[][] toAdd = new int[10000][3];
        for( int d= 0; d < dimensionsSize; d++) {
            Dimension dimension = dimensions.get(d);
            maxX = dimension.xSize;
            maxY = dimension.ySize;
            ArrayList<ArrayList<String>> yList = dimension.xy;
            for( int y = 0; y < yList.size(); y++) {
                ArrayList<String> xList = yList.get(y);
                for( int x = 0; x < xList.size(); x++) {
                    int activeNeighborCounter = 0;

                    for(int z = d -1; z <= d+1; z++) {
                        if (z >= 0 && z < dimensionsSize) {
                            for (int tempY = y - 1; tempY <= y + 1; tempY++) {
                                if (tempY >= 0 && tempY < maxY) {
                                    for (int tempX = x - 1; tempX <= x + 1; tempX++) {
                                        if (tempX >= 0 && tempX < maxX) {
                                            if (!(z == d && tempY == y && tempX == x)) {
                                                if (dimensions.get(z).xy.get(tempY).get(tempX).equals("#")) {
                                                    activeNeighborCounter++;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if( xList.get(x).equals("#"))  {
                        if( !(activeNeighborCounter == 2 || activeNeighborCounter == 3)) {
                            toRemove[removingCounter][0] = d;
                            toRemove[removingCounter][1] = y;
                            toRemove[removingCounter][2] = x;
                            removingCounter++;
                        }
                    } else if(xList.get(x).equals(".")) {
                        if(activeNeighborCounter == 3) {
                            toAdd[addingCounter][0] = d;
                            toAdd[addingCounter][1] = y;
                            toAdd[addingCounter][2] = x;
                            addingCounter++;
                        }
                    }
                }
            }
        }

        for( int i = 0; i < removingCounter; i++) {
            dimensions.get(toRemove[i][0]).updateValue(toRemove[i][1], toRemove[i][2], ".");
        }

        for( int i = 0; i < addingCounter; i++) {
            dimensions.get(toAdd[i][0]).updateValue(toAdd[i][1], toAdd[i][2], "#");
        }
    }
}

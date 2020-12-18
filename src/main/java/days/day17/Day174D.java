package days.day17;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day174D {

    private Utils utils = new Utils();
    ArrayList<ArrayList<Dimension>> dimensionsW = new ArrayList<>();

    public Day174D() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day17.txt");
        ArrayList<Dimension> dimensions = new ArrayList<>();
        Dimension dimension = new Dimension();

        while (scanner.hasNextLine()) {
            dimension.addYRow(null, false);
            String line = scanner.nextLine();
            for(char ch : line.toCharArray()) {
                dimension.addValue(dimension.ySize-1, null,ch + "");
            }
        }
        dimensions.add(dimension);
        dimensionsW.add(dimensions);

        startCycle(1);
        System.out.println("Day174d = " + countActives());
    }

    private int countActives() {
        int counter = 0;
        for(int w = 0; w < dimensionsW.size(); w++) {
            ArrayList<Dimension> dimensions = dimensionsW.get(w);
            for (int z = 0; z < dimensions.size(); z++) {
                for (int y = 0; y < dimensions.get(z).xy.size(); y++) {
                    for (int x = 0; x < dimensions.get(z).xy.get(y).size(); x++) {
                        if (dimensions.get(z).xy.get(y).get(x).equals("#")) {
                            counter++;
                        }
                    }
                }
            }
        }
        return counter;
    }


    private void addDimensionsWithDimensions(int indexToCheck, int indexToAdd) {
        ArrayList<Dimension> dimensionList = dimensionsW.get(indexToCheck);
        int counter = 0;
        for(int d = 0; d < dimensionList.size(); d++) {
            Dimension dimension = dimensionList.get(d);
            ArrayList<ArrayList<String>> xy = dimension.xy;
            for (int y = 0; y < xy.size(); y++) {
                ArrayList<String> xList = xy.get(y);
                for (int x = 0; x < xList.size(); x++) {
                    if (xList.get(x).equals("#")) {
                        counter++;
                        if(counter >= 3) {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(counter);
        if(counter >= 3) {
            System.out.println("Adding dimension!");
            ArrayList<Dimension> newDimensionList = new ArrayList<>();
            for(int d = 0; d < dimensionList.size(); d++) {
                Dimension dimension = new Dimension(dimensionList.get(0).ySize, dimensionList.get(0).xSize);
                newDimensionList.add(dimension);
            }
            dimensionsW.add(indexToAdd, newDimensionList);
        }
    }
    private void addDimensionsWithInactiveCubes(int indexToCheck, int indexToAdd) {
        int activeCounter = 0;

        for(int w = 0; w < dimensionsW.size(); w++) {
            ArrayList<Dimension> dimensions = dimensionsW.get(w);
            Dimension dimension = dimensions.get(indexToCheck);
            ArrayList<ArrayList<String>> xy = dimension.xy;

            for (int y = 0; y < xy.size(); y++) {
                ArrayList<String> xList = xy.get(y);
                for (int x = 0; x < xList.size(); x++) {
                    if (xList.get(x).equals("#")) {
                        activeCounter++;
                    }
                }
            }
        }

        if(activeCounter >= 3) {
            //add new deminion
            for(int w = 0; w < dimensionsW.size(); w++) {
                ArrayList<Dimension> dimensions = dimensionsW.get(w);
                Dimension dimension = dimensions.get(indexToCheck);
                Dimension newDimension = new Dimension(dimension.ySize, dimension.xSize);
                dimensions.add(indexToAdd, newDimension);
            }
        }
    }

    private void addYToActiveDimensions(int indexToCheck, int indexToAdd) {
        int yCounter = 0;
        boolean addRow = false;
        for(int w = 0; w < dimensionsW.size(); w++) {
            ArrayList<Dimension> dimensions = dimensionsW.get(w);
            for (int d = 0; d < dimensions.size(); d++) {
                ArrayList<String> x = dimensions.get(d).xy.get(indexToCheck);
                for (String string : x) {
                    if (string.equals("#")) {
                        yCounter++;
                    }
                }
                if (yCounter >= 3) {
                    addRow = true;
                    break;
                }
            }
        }

        if(addRow){
            for(int w = 0; w < dimensionsW.size(); w++) {
                ArrayList<Dimension> dimensions = dimensionsW.get(w);
                for (int d = 0; d < dimensions.size(); d++) {
                    Dimension dimension = dimensions.get(d);
                    dimension.addYRow(indexToAdd, true);
                }
            }
        }
    }

    private void addXToActiveDimensions(int indexToCheck, int indexToAdd) {
        int xCounter = 0;
        boolean addRow = false;
        for(int w = 0; w < dimensionsW.size(); w++) {
            ArrayList<Dimension> dimensions = dimensionsW.get(w);
            for (int d = 0; d < dimensions.size(); d++) {
                Dimension dimension = dimensions.get(d);
                ArrayList<ArrayList<String>> xy = dimension.xy;
                for (int y = 0; y < xy.size(); y++) {
                    if (xy.get(y).get(indexToCheck).equals("#")) {
                        xCounter++;
                    }
                }

                if (xCounter >= 3) {
                    addRow = true;
                    break;
                }
            }
        }
        if(addRow){
            for(int w = 0; w < dimensionsW.size(); w++) {
                ArrayList<Dimension> dimensions = dimensionsW.get(w);
                for (int d = 0; d < dimensions.size(); d++) {
                    Dimension dimension = dimensions.get(d);
                    ArrayList<ArrayList<String>> xy = dimension.xy;
                    for (int y = 0; y < xy.size(); y++) {
                        dimension.addValue(y, indexToAdd, ".");
                    }
                }
            }
        }
    }

    public void printDimension(int counter) {
        System.out.println("Round =" + counter);
        for(int w = 0; w < dimensionsW.size(); w++) {
            ArrayList<Dimension> dimensions = dimensionsW.get(w);
            System.out.println("w=" + w);
            for (int i = 0; i < dimensions.size(); i++) {
                ArrayList<ArrayList<String>> xy = dimensions.get(i).xy;
                System.out.println("z=" + i);
                for (int y = 0; y < xy.size(); y++) {
                    for (int x = 0; x < xy.get(y).size(); x++) {
                        System.out.print(xy.get(y).get(x));
                    }
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void startCycle(int cycleCounter) {
        addDimensionsWithDimensions(0,0);
        addDimensionsWithDimensions(dimensionsW.size()-1,dimensionsW.size());
        addDimensionsWithInactiveCubes(0,0);
        addDimensionsWithInactiveCubes(dimensionsW.get(0).size()-1,dimensionsW.get(0).size());
        addYToActiveDimensions(0,0);
        addYToActiveDimensions(dimensionsW.get(0).get(0).ySize-1, dimensionsW.get(0).get(0).ySize);
        addXToActiveDimensions(0,0);
        addXToActiveDimensions(dimensionsW.get(0).get(0).xSize-1, dimensionsW.get(0).get(0).xSize);
        calculateChanges();
        //printDimension(cycleCounter);
        if(cycleCounter < 6) {
            startCycle(cycleCounter + 1);
        }

        //printDimension(cycleCounter);
    }

    private void calculateChanges() {


        int dimensionsSize = dimensionsW.get(0).size();
        int dimensionWSize = dimensionsW.size();

        int maxX = 0, maxY = 0;
        int removingCounter = 0;
        int[][] toRemove = new int[10000000][4];
        int addingCounter = 0;
        int[][] toAdd = new int[10000000][4];
        for (int w = 0; w < dimensionWSize; w++) {
            ArrayList<Dimension> dimensions = dimensionsW.get(w);
            for (int d = 0; d < dimensionsSize; d++) {
                Dimension dimension = dimensions.get(d);
                maxX = dimension.xSize;
                maxY = dimension.ySize;
                ArrayList<ArrayList<String>> yList = dimension.xy;
                for (int y = 0; y < yList.size(); y++) {
                    ArrayList<String> xList = yList.get(y);
                    for (int x = 0; x < xList.size(); x++) {
                        int activeNeighborCounter = 0;

                        for (int tempW = w -1; tempW <=  w + 1; tempW++) {
                            if (tempW >= 0 && tempW < dimensionWSize) {
                                for (int z = d - 1; z <= d + 1; z++) {
                                    if (z >= 0 && z < dimensionsSize) {
                                        for (int tempY = y - 1; tempY <= y + 1; tempY++) {
                                            if (tempY >= 0 && tempY < maxY) {
                                                for (int tempX = x - 1; tempX <= x + 1; tempX++) {
                                                    if (tempX >= 0 && tempX < maxX) {
                                                        if (!(z == d && tempY == y && tempX == x && tempW == w)) {
                                                            if (dimensionsW.get(tempW).get(z).xy.get(tempY).get(tempX).equals("#")) {
                                                                activeNeighborCounter++;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (xList.get(x).equals("#")) {
                            if (!(activeNeighborCounter == 2 || activeNeighborCounter == 3)) {
                                toRemove[removingCounter][0] = w;
                                toRemove[removingCounter][1] = d;
                                toRemove[removingCounter][2] = y;
                                toRemove[removingCounter][3] = x;
                                removingCounter++;
                            }
                        } else if (xList.get(x).equals(".")) {
                            if (activeNeighborCounter == 3) {
                                toAdd[addingCounter][0] = w;
                                toAdd[addingCounter][1] = d;
                                toAdd[addingCounter][2] = y;
                                toAdd[addingCounter][3] = x;
                                addingCounter++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < removingCounter; i++) {
            dimensionsW.get(toRemove[i][0]).get(toRemove[i][1]).updateValue(toRemove[i][2], toRemove[i][3], ".");
        }

        for (int i = 0; i < addingCounter; i++) {
            dimensionsW.get(toAdd[i][0]).get(toAdd[i][1]).updateValue(toAdd[i][2], toAdd[i][3], "#");

        }
    }
}

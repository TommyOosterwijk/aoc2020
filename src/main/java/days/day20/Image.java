package days.day20;

import java.util.ArrayList;

public class Image {

    int id;
    String[][] imageGrid = new String[10][10];
    ArrayList<String> edgeLines = new ArrayList<>();
    ArrayList<String> validatedEdgeLines = new ArrayList<>();
    PuzzleType puzzleType = null;

    public void setImageGrid(String[][] originalGrid) {
        imageGrid = originalGrid;
        calculateEdges();
    }

    public void flip() {
        //System.out.println("Flipping image " + id);
        String[][] tempGrid = new String[10][10];

        for(int y = 0; y < 10; y++) {
            int newX = 9;
            for(int x = 0; x < 10; x++) {
                tempGrid[y][newX] =  imageGrid[y][x];
                newX--;
            }
        }

        imageGrid = tempGrid;
        calculateEdges();
    }

    public void rotate() {
        //System.out.println("Rotating image " + id);
        String[][] tempGrid = new String[10][10];
        int newX = 9;
        int newY;
        for(int y = 0; y < 10; y++) {
            newY = 0;
            for(int x = 0; x < 10; x++) {
                tempGrid[newY][newX] =  imageGrid[y][x];
                newY++;
            }
            newX--;
        }

        imageGrid = tempGrid;
        calculateEdges();
    }

    private void calculateEdges() {
        ArrayList<String> tempLines = new ArrayList<>();

        String links = "";
        String rechts= "";
        String top = "";
        String onder = "";
        for(int i = 0; i < 10; i++) {
            top+= imageGrid[0][i];
            onder+= imageGrid[9][i];
            links+= imageGrid[i][0];
            rechts+= imageGrid[i][9];
        }
        tempLines.add(top);
        tempLines.add(rechts);
        tempLines.add(onder);
        tempLines.add(links);

        edgeLines.clear();
        edgeLines.addAll(tempLines);
    }

    enum PuzzleType {
        CORNER,
        SIDE,
        MIDDLE
    }
}

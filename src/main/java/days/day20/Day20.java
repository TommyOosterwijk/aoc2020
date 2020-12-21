package days.day20;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day20 {

    private Utils utils = new Utils();
    ArrayList<Image> cameraTiles = new ArrayList();
    int finalGridColumnSize = 3;
    //int finalGridColumnSize = 12;
    Image[][] finalGrid = new Image[finalGridColumnSize][finalGridColumnSize]; //TEST

    public Day20() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day20-example.txt");
        Image image = null;
        String[][] tempGrid = null;
        int rowCounter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("Tile")) {
                image = new Image();
                tempGrid = new String[10][10];
                rowCounter = 0;
                image.id = Integer.parseInt(line.replaceAll("Tile ", "").replaceAll(":", ""));
            } else if (line.equals("")) {
                image.setImageGrid(tempGrid);
                cameraTiles.add(image);
            } else {
                for (int i = 0; i < line.length(); i++) {
                    tempGrid[rowCounter][i] = line.charAt(i) + "";
                }
                rowCounter++;
            }
        }
        image.setImageGrid(tempGrid);
        cameraTiles.add(image);
        puzzle();
    }

    private void puzzle() {
        labelImageType();
        calculateAnswerA();
        addFirstTile();
        printGrandTile();
        addOtherTiles();
        printGrandTile();
    }

    private boolean doesEdgeExistInTilesLeft(String edge, ArrayList<Integer> usedImages) {
        for (int i = 0; i < cameraTiles.size(); i++) {
            Image targetImage = cameraTiles.get(i);
            if (!usedImages.contains(targetImage.id)) {
                for (int i2 = 0; i2 < 2; i2++) {
                    if (targetImage.edgeLines.contains(edge)) {
                        return true;
                    }
                    targetImage.rotate();
                }
            }
        }
        return false;
    }

    //This should work if we got first row
    private void addOtherTiles() {
        ArrayList<Integer> usedImages = new ArrayList<>();
        usedImages.add(finalGrid[0][0].id);

        for (int y = 0; y < finalGridColumnSize; y++) {
            for (int x = 0; x < finalGridColumnSize; x++) {
                boolean targetFound = false;
                if (!(y == 0 && x == 0)) {

                    if (y == 0) {
                        Image neighbor = finalGrid[y][x - 1];

                        String rightSideValue = neighbor.edgeLines.get(1);
                        for (Image targetImage : cameraTiles) {
                            if (!usedImages.contains(targetImage.id)) {
                                Image.PuzzleType type = Image.PuzzleType.SIDE;
                                if (x == finalGridColumnSize - 1) {
                                    type = Image.PuzzleType.CORNER;
                                }
                                if (targetImage.puzzleType == type) {
                                    if (targetImage.edgeLines.contains(rightSideValue)) {
                                        targetFound = true;
                                    }
                                    if (!targetFound) {
                                        targetImage.rotate();
                                        targetImage.rotate();
                                        if (targetImage.edgeLines.contains(rightSideValue)) {
                                            targetFound = true;
                                        }
                                    }

                                    if (targetFound) {
                                        if (targetImage.puzzleType == Image.PuzzleType.SIDE) {
                                            boolean fitsNeighbors = false;
                                            boolean didFlip = false;
                                            int rotateCounter = 0;
                                            while (!fitsNeighbors && targetFound) {
                                                if (!targetImage.edgeLines.get(3).equals(rightSideValue)) {
                                                    targetImage.rotate();
                                                    rotateCounter++;


                                                    if (rotateCounter == 4 && didFlip) {
                                                        System.out.println("No Match! Should not happen I think");
                                                        targetFound = false;
                                                    }

                                                    if (rotateCounter == 4) {
                                                        targetImage.flip();
                                                        didFlip = true;
                                                        rotateCounter = 0;
                                                    }
                                                } else {
                                                    String belowSide = targetImage.edgeLines.get(2);
                                                    String rightSide = targetImage.edgeLines.get(1);

                                                    if (doesEdgeExistInTilesLeft(belowSide, usedImages) && doesEdgeExistInTilesLeft(rightSide, usedImages)) {
                                                        fitsNeighbors = true;
                                                        finalGrid[y][x] = targetImage;
                                                        usedImages.add(targetImage.id);
                                                        targetFound = false;
                                                    }
                                                }
                                            }
                                        } else if (targetImage.puzzleType == Image.PuzzleType.CORNER) {
                                            boolean fitsNeighbors = false;
                                            boolean didFlip = false;
                                            int rotateCounter = 0;
                                            while (!fitsNeighbors) {
                                                if (!targetImage.edgeLines.get(3).equals(rightSideValue)) {
                                                    targetImage.rotate();
                                                    rotateCounter++;

                                                    if (rotateCounter == 4) {
                                                        targetImage.flip();
                                                        didFlip = true;
                                                        rotateCounter = 0;
                                                    }
                                                    if (rotateCounter == 4 && didFlip) {
                                                        fitsNeighbors = false;
                                                        targetFound = false;
                                                    }
                                                }

                                                String belowSide = targetImage.edgeLines.get(2);

                                                if (doesEdgeExistInTilesLeft(belowSide, usedImages)) {
                                                    fitsNeighbors = true;
                                                    finalGrid[y][x] = targetImage;
                                                    usedImages.add(targetImage.id);
                                                }
                                            }
                                        }
                                    }
                                }
                                if (targetFound) {
                                    break;
                                }
                            }

                        }
                    } else if (x == 0) {
                        // look up
                        Image neighborTop = finalGrid[y - 1][x];
                        String bottomSideValue = neighborTop.edgeLines.get(2);
                        for (Image targetImage : cameraTiles) {
                            if (!usedImages.contains(targetImage.id)) {
                                if (targetImage.edgeLines.contains(bottomSideValue)) {
                                    targetFound = true;
                                }
                                if (!targetFound) {
                                    targetImage.rotate();
                                    targetImage.rotate();
                                    if (targetImage.edgeLines.contains(bottomSideValue)) {
                                        targetFound = true;
                                    }
                                }

                                if (targetFound) {
                                    System.out.println("Found correct ID " + targetImage.id);
                                    boolean fitsNeighbors = false;
                                    boolean didFlip = false;
                                    int rotateCounter = 0;
                                    while (!fitsNeighbors && targetFound) {
                                        if (!targetImage.edgeLines.get(0).equals(bottomSideValue)) {
                                            targetImage.rotate();
                                            rotateCounter++;

                                            if (rotateCounter == 4 && didFlip) {
                                                System.out.println("No Match! Should not happen I think");
                                                targetFound = false;
                                            }

                                            if (rotateCounter == 4) {
                                                targetImage.flip();
                                                didFlip = true;
                                                rotateCounter = 0;
                                            }
                                        } else {
                                            boolean validation = false;

                                            String rightSide = targetImage.edgeLines.get(1);
                                            if (targetImage.puzzleType == Image.PuzzleType.CORNER) {
                                                validation = doesEdgeExistInTilesLeft(rightSide, usedImages);
                                            } else if (targetImage.puzzleType == Image.PuzzleType.SIDE) {
                                                String belowSide = targetImage.edgeLines.get(2);
                                                validation = doesEdgeExistInTilesLeft(belowSide, usedImages) && doesEdgeExistInTilesLeft(rightSide, usedImages);
                                            }

                                            if (validation) {
                                                fitsNeighbors = true;
                                                finalGrid[y][x] = targetImage;
                                                usedImages.add(targetImage.id);
                                                targetFound = false;
                                            }
                                        }
                                    }
                                }
                            }
                            if (targetFound) {
                                break;
                            }
                        }
                    } else {
                        Image neighborTop = finalGrid[y - 1][x];
                        Image neighborLeft = finalGrid[y][x-1];
                        String bottomSideValue = neighborTop.edgeLines.get(2);
                        String rightSideValue = neighborLeft.edgeLines.get(1);

                        for (Image targetImage : cameraTiles) {
                            if (!usedImages.contains(targetImage.id)) {
                                if (targetImage.edgeLines.contains(bottomSideValue) && targetImage.edgeLines.contains(rightSideValue)) {
                                    targetFound = true;
                                }
                                if (!targetFound) {
                                    targetImage.rotate();
                                    if (targetImage.edgeLines.contains(bottomSideValue) && targetImage.edgeLines.contains(rightSideValue)) {
                                        targetFound = true;
                                    }
                                }
                                if (!targetFound) {
                                    targetImage.rotate();
                                    if (targetImage.edgeLines.contains(bottomSideValue) && targetImage.edgeLines.contains(rightSideValue)) {
                                        targetFound = true;
                                    }
                                }
                                if (!targetFound) {
                                    targetImage.rotate();
                                    if (targetImage.edgeLines.contains(bottomSideValue) && targetImage.edgeLines.contains(rightSideValue)) {
                                        targetFound = true;
                                    }
                                }

                                if (targetFound) {
                                    System.out.println("Found correct ID " + targetImage.id);
                                    boolean fitsNeighbors = false;
                                    boolean didFlip = false;
                                    int rotateCounter = 0;
                                    while (!fitsNeighbors && targetFound) {
                                        if (!(targetImage.edgeLines.get(0).equals(bottomSideValue) && targetImage.edgeLines.get(3).equals(rightSideValue))) {
                                            targetImage.rotate();
                                            rotateCounter++;

                                            if (rotateCounter == 4 && didFlip) {
                                                System.out.println("No Match! Should not happen I think");
                                                targetFound = false;
                                            }

                                            if (rotateCounter == 4) {
                                                targetImage.flip();
                                                didFlip = true;
                                                rotateCounter = 0;
                                            }
                                        } else {
                                            fitsNeighbors = true;
                                            finalGrid[y][x] = targetImage;
                                            usedImages.add(targetImage.id);
                                            targetFound = false;
                                        }
                                    }
                                }
                            }
                            if (targetFound) {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    private void printGrandTile() {
        System.out.println("Grand Tile");
        for (int finalY = 0; finalY < finalGridColumnSize; finalY++) {
            Image[] lineGrid = finalGrid[finalY];
            for (int y = 0; y < 10; y++) {
                for (int i = 0; i < lineGrid.length; i++) {
                    Image tempImage = lineGrid[i];
                    if (tempImage != null) {
                        for (int x = 0; x < 10; x++) {
                            System.out.print(tempImage.imageGrid[y][x]);
                        }
                    } else {
                        System.out.print(" ");
                    }
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private void printTile(Image image) {
        System.out.println("Camera id " + image.id);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                System.out.print(image.imageGrid[y][x] + " ");
            }
            System.out.println();
        }
    }

    private void addFirstTile() {
        boolean firstTileFound = false;
        for (int index = 0; index < cameraTiles.size(); index++) {
            Image image = cameraTiles.get(index);
            if (image.puzzleType.equals(Image.PuzzleType.CORNER)) {
                for (int times = 0; times < 2; times++) {
                    for (int i = 0; i < 4; i++) {

                        if ((image.edgeLines.get(1).equals(image.validatedEdgeLines.get(0)) || image.edgeLines.get(2).equals(image.validatedEdgeLines.get(0))) && (image.edgeLines.get(1).equals(image.validatedEdgeLines.get(1)) || image.edgeLines.get(2).equals(image.validatedEdgeLines.get(1)))) {
                            //This is the topleft image
                            finalGrid[0][0] = image;
                            firstTileFound = true;
                            break;
                        }
                        image.rotate();
                    }
                    if (firstTileFound) {
                        break;
                    }
                    image.flip();
                }
            }
            if (firstTileFound) {
                break;
            }
        }
    }

    private void calculateAnswerA() {
        long value = 1l;
        for (Image image : cameraTiles) {
            if (image.puzzleType.equals(Image.PuzzleType.CORNER)) {
                value *= image.id;
            }
        }
        System.out.println("Day 20 = " + value);
    }

    private void labelImageType() {
        for (int i = 0; i < cameraTiles.size(); i++) {
            Image image = cameraTiles.get(i);
            ArrayList<String> lines = image.edgeLines;
            int matchCounter = 0;
            for (int i2 = 0; i2 < cameraTiles.size(); i2++) {
                if (i != i2) {
                    Image image2 = cameraTiles.get(i2);
                    for (int i3 = 0; i3 < lines.size(); i3++) {
                        if (image2.edgeLines.contains(lines.get(i3))) {
                            matchCounter++;
                            image.validatedEdgeLines.add(lines.get(i3));
                        }
                    }
                }
            }
            image.rotate();
            image.rotate();
            lines = image.edgeLines;
            for (int i2 = 0; i2 < cameraTiles.size(); i2++) {
                if (i != i2) {
                    Image image2 = cameraTiles.get(i2);
                    for (int i3 = 0; i3 < lines.size(); i3++) {
                        if (image2.edgeLines.contains(lines.get(i3))) {
                            image.validatedEdgeLines.add(lines.get(i3));
                            matchCounter++;
                        }
                    }
                }
            }
            if (matchCounter == 2) {
                image.puzzleType = Image.PuzzleType.CORNER;
            } else if (matchCounter == 3) {
                image.puzzleType = Image.PuzzleType.SIDE;
            } else {
                image.puzzleType = Image.PuzzleType.MIDDLE;
            }
        }
    }
}

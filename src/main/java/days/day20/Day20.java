package days.day20;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Scanner;

public class Day20 {

    private Utils utils = new Utils();
    ArrayList<CameraImage> cameraImages = new ArrayList();

    public Day20() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day20.txt");
        CameraImage cameraImage = null;
        String[][] tempGrid = null;
        int rowCounter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains("Tile")) {
                cameraImage = new CameraImage();
                tempGrid = new String[10][10];
                rowCounter = 0;
                cameraImage.setId(Integer.parseInt(line.replaceAll("Tile ", "").replaceAll(":", "")));
            } else if(line.equals("")) {
                cameraImage.setOriginalImageGrid(tempGrid);
                cameraImages.add(cameraImage);
            } else {
                for(int i = 0; i < line.length(); i++) {
                    tempGrid[rowCounter][i] = line.charAt(i) + "";
                }
                rowCounter++;
            }
        }
        cameraImage.setOriginalImageGrid(tempGrid);
        cameraImages.add(cameraImage);

        puzzle();
    }

    private void puzzle() {
        long value = 1l;
        for(int i = 0; i < cameraImages.size(); i++) {
            CameraImage cameraImage = cameraImages.get(i);
            int matchCounter =0;
            for(int i2 = 0; i2 < cameraImages.size(); i2++) {
                if(i != i2){
                    CameraImage cameraImage2 = cameraImages.get(i2);
                    for(int i3 =0; i3 < 4; i3 ++) {
                        if(cameraImage2.lines.contains(cameraImage.lines.get(i3))) {
                            matchCounter++;
                        }
                    }
                }
            }

            if(matchCounter == 2) {
                value*= cameraImage.id;
            }
        }
        System.out.println("Day 20 = " + value);
    }

}

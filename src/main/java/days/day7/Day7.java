package days.day7;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day7 {

    private Utils utils = new Utils();
    ArrayList<Bag> bagList = new ArrayList<>();

    public Day7() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day7-example.txt");
        int goldBagCounter = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replaceAll(" bags", "");
            line = line.replaceAll(" bag", "");
            line = line.replace(".", "");
            String[] array = line.split(" contain ");

            Bag bag = new Bag();
            bag.setBagColor(array[0]);

            if(!array[1].contains("no other")) {

                String[] childBags = array[1].split(", ");

                for (String child : childBags) {
                    String[] splitAmountAndName = child.split(" ", 2);
                    bag.addBag(splitAmountAndName[1], Integer.parseInt(splitAmountAndName[0]));
                }
            }

            bagList.add(bag);

        }
        countParentBags("shiny gold");
        for(Bag bag : bagList) {
            if(bag.hasGold()) {
                goldBagCounter++;
            }
        }

        System.out.println("Day7 = " + goldBagCounter + " bags that contain gold. (self or by child)");
    }


    private void countParentBags(String childBag) {
        for(Bag bag : bagList) {
            if(bag.hasBagThatContainsGold(childBag)) {
                countParentBags(bag.getBagColor());
            }
        }
    }
}

package days.day20.day21;

import days.day20.CameraImage;
import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class Day21 {

    private Utils utils = new Utils();
    Map<String, Integer> finalIngredientList = new HashMap<>();
    Map<String, ArrayList<String>> allergenList = new HashMap<>();

    public Day21() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day21.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("\\(contains ");
            ArrayList<String> ingredientList = new ArrayList<>();
            for(String ingredient : split[0].split(" ")) {
                if(finalIngredientList.containsKey(ingredient)) {
                    finalIngredientList.put(ingredient,finalIngredientList.get(ingredient)+1);
                } else {
                    finalIngredientList.put(ingredient,1);
                }

                ingredientList.add(ingredient);
            }

            String allergenString = split[1].replaceAll("\\)", "");

            for(String allergen : allergenString.split(", ")) {
                if(allergenList.containsKey(allergen)) {
                    ArrayList<String> currentIngredientList = allergenList.get(allergen);
                    ArrayList<String> newIngredientList = new ArrayList<>();
                    for( int i = 0; i < currentIngredientList.size(); i++) {
                        if(ingredientList.contains(currentIngredientList.get(i))) {
                            newIngredientList.add(currentIngredientList.get(i));
                        }
                    }
                    allergenList.put(allergen, newIngredientList);

                } else {
                    allergenList.put(allergen, ingredientList);
                }
            }
        }
        countMissingIngredients();

        matchIngredientWithAllergen();
        Map<String, ArrayList<String>> sortedMap = new TreeMap<String, ArrayList<String>>(allergenList);
        String result = "";
        for(ArrayList<String> string : sortedMap.values()){
            if(string != null) {
                if(string.size() ==1){
                    result += string.get(0)+",";
                }
            }
        }
        System.out.println("Day 21B = " + result.substring(0, result.length()-1));
    }

    private void matchIngredientWithAllergen() {
        ArrayList<String> filtered = new ArrayList<>();
        String toRemove = "";
        for(int i =0; i < 100; i++) {

            boolean startRemoving = false;

            for(Iterator<Map.Entry<String, ArrayList<String>>> it = allergenList.entrySet().iterator(); it.hasNext(); ) {

                Map.Entry<String, ArrayList<String>> entry = it.next();
                ArrayList<String> allergie = entry.getValue();
                for (int x = 0; x <allergie.size(); x++) {
                    if(allergie.get(x) != null) {
                        if (!startRemoving) {
                            if (allergie.size() == 1) {
                                if(!filtered.contains(allergie.get(x))) {
                                    toRemove = allergie.get(x);
                                    startRemoving = true;
                                    filtered.add(allergie.get(x));
                                }
                            }
                        } else {
                            if (entry.getValue().size() > 1 && entry.getValue().contains(toRemove)) {
                                entry.getValue().remove(toRemove);
                            }
                        }
                    }
                }

            }
            for(Iterator<Map.Entry<String, ArrayList<String>>> it = allergenList.entrySet().iterator(); it.hasNext(); ) {

                Map.Entry<String, ArrayList<String>> entry = it.next();
                ArrayList<String> allergie = entry.getValue();
                for (int x = 0; x <allergie.size(); x++) {
                    if(allergie.get(x) != null) {
                        if (!startRemoving) {
                            if (allergie.size() == 1) {
                                toRemove = allergie.get(x);
                                startRemoving = true;
                            }
                        } else {
                            if (entry.getValue().size() > 1 && entry.getValue().contains(toRemove)) {
                                System.out.println("Removing");
                                entry.getValue().remove(toRemove);
                            }
                        }
                    }
                }

            }
        }
    }

    private void countMissingIngredients() {
        int counter = 0;
        for(String ingredient : finalIngredientList.keySet()) {
            boolean found = false;
            for(String allergen : allergenList.keySet()) {
                ArrayList<String> allergen2 = allergenList.get(allergen);
                if(allergen2 != null) {
                    if (allergen2.contains(ingredient)) {
                        found = true;
                        break;
                    }
                }
            }
            if(!found) {
                counter+= finalIngredientList.get(ingredient);
            }
        }

        System.out.println("Day 21A = " + counter);
    }
}

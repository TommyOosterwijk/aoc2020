package days.day6;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Day6b {

    private Utils utils = new Utils();


    public Day6b() throws FileNotFoundException, URISyntaxException {
        int count = 0;
        Scanner scanner = utils.getScannerFromFileName("Day6.txt");
        boolean firstEntry = true;

        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> toRemove = new ArrayList<>();


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.equals("")) {

                count += list.size();
                firstEntry = true;
                list.clear();
            } else {
                if(firstEntry) {
                    for (char ch : line.toCharArray()) {
                        list.add("" + ch);
                    }
                    firstEntry = false;
                } else if(list.size() > 0) {
                    for( String string : list) {
                        if(!line.contains(string)) {
                            toRemove.add(string);
                        }
                    }
                    list.removeAll(toRemove);
                    toRemove.clear();
                }
            }
        }
        count += list.size();
        System.out.println("Day6 result = " + count);
    }
}

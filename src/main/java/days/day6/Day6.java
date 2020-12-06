package days.day6;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Day6 {

    private Utils utils = new Utils();


    public Day6() throws FileNotFoundException, URISyntaxException {
        int count = 0;
        int groupSize = 0;
        Scanner scanner = utils.getScannerFromFileName("Day6.txt");
        boolean firstEntry = false;

        ArrayList<String> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if(line.equals("")) {

                Set<String> set = new LinkedHashSet<>();

                // Add the elements to set
                set.addAll(list);

                count += set.size();
                list.clear();
            } else {
                for( char ch : line.toCharArray()) {
                    list.add(ch + "");
                }
            }
        }

        Set<String> set = new LinkedHashSet<>();

        // Add the elements to set
        set.addAll(list);

        count += set.size();
        list.clear();

        System.out.println("Day6 result = " + count);

        Day6b day6b = new Day6b();
    }
}

package days.day10;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.*;

public class Day10b {

    private Utils utils = new Utils();
    ArrayList<Integer> adapters = new ArrayList<>();
    Map<Integer, Long> adapterList = new HashMap<Integer, Long>();


    public Day10b() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day10.txt");
        while (scanner.hasNextInt()) {
            adapters.add(scanner.nextInt());
        }

        adapters.add(0);
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        Collections.sort(adapters, Collections.reverseOrder());
        System.out.println("Day10b = " + calculateAdapters());
    }

    public long calculateAdapters() {
        for( int i =1; i < 4; i++) {
            if(!(adapters.get(0) - adapters.get(i) <= 3)) {
                break;
            }
            // index 1 has 1 valid route to endAdapter.
            adapterList.put(i, 1l);
        }

        for( int i = 2; i < adapters.size(); i++) {
            long adaterRouteCounter = 0;
            int adapterI = adapters.get(i);

            for(int x =i-1; x > i-4; x--) {
                if( x > 0) {
                    if (adapters.get(x) - adapterI <= 3) {
                        adaterRouteCounter += adapterList.get(x);
                    }
                }
            }
            adapterList.put(i, adaterRouteCounter);

        }
        return adapterList.get(adapters.size()-1);
    }
}

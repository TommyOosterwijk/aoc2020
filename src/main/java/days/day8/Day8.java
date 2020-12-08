package days.day8;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day8 {


    private Utils utils = new Utils();
    ArrayList<FlightAction> flightActions = new ArrayList<>();


    public Day8() throws FileNotFoundException, URISyntaxException {

        Scanner scanner = utils.getScannerFromFileName("Day8.txt");
        while (scanner.hasNextLine()) {
            FlightAction fa = new FlightAction();
            String line = scanner.nextLine();
            String[] flightAction = line.split(" ");
            fa.setActionValue(Integer.parseInt(flightAction[1].replaceAll("\\+", "")));
            fa.setAction(flightAction[0]);

            flightActions.add(fa);
        }

        doActionA();

        doActionB();
    }

    private void doActionA() {
        int flightActionIndex = 0;
        int accumulator = 0;
        boolean duplicateActionFound = false;

        while(!duplicateActionFound) {
            FlightAction fa = flightActions.get(flightActionIndex);
            if(!fa.isExecuted()) {
                if (fa.getAction().equals("acc")) {
                    accumulator += fa.getActionValue();
                    flightActionIndex++;
                    fa.setExecuted(true);
                } else if (fa.getAction().equals("nop")) {
                    flightActionIndex++;
                    fa.setExecuted(true);
                } else if (fa.getAction().equals("jmp")) {
                    flightActionIndex += fa.getActionValue();
                    fa.setExecuted(true);
                }
            } else {
                duplicateActionFound = true;
            }

        }



        System.out.println("Day8 accyumulator = " + accumulator);
    }

    private void doActionB() {

        for(int i = 0; i < flightActions.size(); i++) {

            // Restarting search
            for( FlightAction fa : flightActions) {
                fa.setExecuted(false);
            }

            FlightAction actionToChange = flightActions.get(i);
            int originalValue = actionToChange.getActionValue();
            String originalAction = actionToChange.getAction();

            if(!actionToChange.getAction().equals("acc")) {

                if (actionToChange.getAction().equals("nop")) {
                    actionToChange.setAction("jmp");
                } else if (actionToChange.getAction().equals("jmp")) {
                    actionToChange.setAction("nop");
                }

                int flightActionIndex = 0;
                int accumulator = 0;
                boolean duplicateActionFound = false;

                while ( !duplicateActionFound && flightActionIndex < flightActions.size()) {
                    FlightAction fa = flightActions.get(flightActionIndex);
                    if (!fa.isExecuted()) {
                        if (fa.getAction().equals("acc")) {
                            accumulator += fa.getActionValue();
                            flightActionIndex++;
                            fa.setExecuted(true);
                        } else if (fa.getAction().equals("nop")) {
                            flightActionIndex++;
                            fa.setExecuted(true);
                        } else if (fa.getAction().equals("jmp")) {
                            flightActionIndex += fa.getActionValue();
                            fa.setExecuted(true);
                        }
                    } else {
                        duplicateActionFound = true;
                    }
                }
                actionToChange.setAction(originalAction);
                actionToChange.setActionValue(originalValue);
                if (!duplicateActionFound) {
                    System.out.println("Day7b = " + accumulator);
                }
            }

        }
    }

}

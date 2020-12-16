package days;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day16 {

    ArrayList<Boolean> found = new ArrayList<>();

    private Utils utils = new Utils();
    ArrayList<int[]> fieldList = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> lookuplist = new ArrayList<>();
    List<Ticket> ticketList = new ArrayList<>();
    List<Integer> departureIndexes = new ArrayList<>();
    int ticketNumberCounter = 0;

    int invalidTickets = 0;

    public Day16() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day16.txt");
        boolean rules = true;
        int rulesCounter = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(":");


            if (split[0].contains("your")) {
                rules = false;
            } else if (split[0].contains("nearby")) {
                // do nothing
            } else if (split[0].equals("")) {
                // do nothing
            } else {
                if (rules) {
                    String valueLine = split[1];
                    if (split[0].startsWith("departure")) {
                        departureIndexes.add(rulesCounter);
                    }
                    rulesCounter++;

                    valueLine = valueLine.replaceAll(" or ", "-");
                    valueLine = valueLine.replaceAll(" ", "");
                    String[] values = valueLine.replaceAll(" ", "").split("-");
                    int[] temp = new int[4];

                    for (int i = 0; i < 4; i++) {
                        temp[i] = Integer.parseInt(values[i]);
                    }
                    fieldList.add(temp);
                } else {
                    generalTicketScanning(split[0].split(","));
                    specificTicketScanning(split[0].split(","));
                }
            }
        }
        System.out.println("Day16 = " + invalidTickets);
        ticketNumberCounter = ticketList.get(0).ticketNumbers.size();

        for( int i = 0; i < fieldList.size(); i++)  {
            found.add(false);
            ArrayList<Boolean> list = new ArrayList<>();
            for(int x = 0; x < ticketNumberCounter; x++) {
                list.add(true);
            }
            lookuplist.add(list);
        }
        setGrid();
    }

    private void setGrid(){
        for(Ticket ticket : ticketList) {
            for (int i = 0; i < ticketNumberCounter; i++) {
                for (int x = 0; x < fieldList.size(); x++) {

                    int[] validations = fieldList.get(x);
                    int targetValue = ticket.getTicketNumbers().get(i);
                    if ((targetValue >= validations[0] && targetValue <= validations[1]) || (targetValue >= validations[2] && targetValue <= validations[3])) {
                    } else {
                        lookuplist.get(x).set(i, false);
                    }
                }
            }
        }

        calculate();
        long value = 1l;
        Ticket myTicket = ticketList.get(0);

        for(int i = 0; i < departureIndexes.size(); i++) {
            value = value * myTicket.getValueFromPos(getTrueIndexFromFields(i));
        }

        System.out.println("Day16b = " + value);
    }

    private int getTrueIndexFromFields(int index) {
        ArrayList<Boolean> list = lookuplist.get(index);
        for(int i =0; i < list.size(); i++) {
            if(list.get(i)) {
                return i;
            }
        }
        return 0;
    }

    private void calculate() {
        boolean gotEm = false;
        for( int i = 0; i < lookuplist.size(); i++){
            if(!found.get(i)) {
                ArrayList<Boolean> list = lookuplist.get(i);
                int trueCounter = 0;
                int lastTrue = 0;
                for (int x = 0; x < list.size(); x++) {
                    if (list.get(x)) {
                        trueCounter++;
                        lastTrue = x;
                    }
                }

                if (trueCounter == 1) {
                    for (int y = 0; y < lookuplist.size(); y++) {
                        if (y != i) {
                            lookuplist.get(y).set(lastTrue, false);
                            found.set(i, true);
                            gotEm = true;
                        }
                    }
                }
            }
        }

        if(gotEm) {
            calculate();
        }
    }

    public void generalTicketScanning(String[] ticketValues) {
        for( int i = 0; i < ticketValues.length; i++) {
            int value = Integer.parseInt(ticketValues[i]);
            if(!checkValidation(value)) {
                invalidTickets += value;
            }
        }
    }

    public void specificTicketScanning(String[] ticketValues) {
        Ticket ticket = new Ticket();

        for( int i = 0; i < ticketValues.length; i++) {
            int value = Integer.parseInt(ticketValues[i]);
            if(checkValidation(value)) {
                ticket.addTicketNumber(value);
            } else {
                return;
            }
        }

        // only add valid tickets
        ticketList.add(ticket);
    }

    private boolean checkValidation(int value) {
        for( int i = 0; i < fieldList.size(); i++) {
            int[] validations = fieldList.get(i);
            if( (value >= validations[0] && value <= validations[1]) || (value >= validations[2] && value <= validations[3])) {
                return true;
            }
        }

        return false;
    }
}

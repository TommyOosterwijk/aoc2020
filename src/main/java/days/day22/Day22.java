package days.day22;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day22 {

    private Utils utils = new Utils();
    ArrayList<Integer> player1 = new ArrayList<>();
    ArrayList<Integer> player2 = new ArrayList<>();
    boolean player1Cards =  true;

    public Day22() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day22.txt");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains("Player 1")) {
                // do nothing
            } else if (line.contains("Player 2")) {
                player1Cards = false;
            } else if (line.equals("")) {
                // empty line do nothing
            } else {
                int card = Integer.parseInt(line);
                if (player1Cards) {
                    player1.add(card);
                } else {
                    player2.add(card);
                }
            }
        }
        playGame(new ArrayList<>(player1), new ArrayList<>(player2));


        if (playGameB(player1, player2)) {
            System.out.println("Day22 B= " + calculateScore(player1));
        } else {
            System.out.println("Day22 B=" + calculateScore(player2));
        }
    }

    private void playGame(ArrayList<Integer> p1, ArrayList<Integer> p2) {
        while(p1.size() > 0 && p2.size() > 0) {
            int card1 = p1.get(0);
            int card2 = p2.get(0);

            if(card1 > card2) {
                //player1 wins
                p1.add(card1);
                p1.add(card2);
            } else {
                //player2 wins
                p2.add(card2);
                p2.add(card1);
            }

            p1.remove(0);
            p2.remove(0);
        }

        if(p1.size() > 0) {
            System.out.println("Day22 a= " + calculateScore(p1));
        } else {
            System.out.println("Day22 a=" + calculateScore(p2));
        }
    }

    private boolean playGameB(ArrayList<Integer> p1, ArrayList<Integer> p2) {
        ArrayList<String> cardOrdersP1 = new ArrayList<>();
        ArrayList<String> cardOrdersP2 = new ArrayList<>();
        while(p1.size() > 0 && p2.size() > 0) {
            String p1String = getCardOrder(p1);
            String p2String = getCardOrder(p2);
            if(cardOrdersP1.contains(p1String) || cardOrdersP2.contains(cardOrdersP2)) {
                // Game ends, player 1 wins;
                return true;
            } else {
                cardOrdersP1.add(p1String);
                cardOrdersP2.add(p2String);
            }

            int card1 = p1.get(0);
            int card2 = p2.get(0);
            p1.remove(0);
            p2.remove(0);

            if( card1 <= p1.size() && card2 <= p2.size()) {
                ArrayList<Integer> newPlayer1 = new ArrayList<>();
                ArrayList<Integer> newPlayer2 = new ArrayList<>();
                for(int i = 0; i < card1; i++) {
                    newPlayer1.add(p1.get(i));
                }

                for(int i = 0; i < card2; i++) {
                    newPlayer2.add(p2.get(i));
                }
                if(playGameB(newPlayer1, newPlayer2)) {
                    p1.add(card1);
                    p1.add(card2);
                } else {
                    p2.add(card2);
                    p2.add(card1);
                }
            } else {
                if(card1 > card2) {
                    //player1 wins
                    p1.add(card1);
                    p1.add(card2);
                } else {
                    //player2 wins
                    p2.add(card2);
                    p2.add(card1);
                }
            }
        }

        return p1.size() > 0;
    }

    private String getCardOrder(ArrayList<Integer> playerCards) {
        String cardOrder = "";

        for(int i = 0; i < playerCards.size(); i++) {
            cardOrder+= playerCards.get(i) + "";
        }

        return cardOrder;
    }

    private long calculateScore(ArrayList<Integer> winner) {
        long value = 0;

        for(int i = 0; i < winner.size(); i++) {
            value += winner.get(i) * (winner.size() - i);
        }

        return value;
    }
}

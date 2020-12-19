package days.day19;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day19 {

    private Utils utils = new Utils();
    Map<Integer, ArrayList<ArrayList<Integer>>> actions = new HashMap<>();
    Map<Integer, ArrayList<String>> actionLookup = new HashMap<>();
    ArrayList<String> wordsToMatch = new ArrayList<>();
    ArrayList<String> badWords = new ArrayList<>();
    ArrayList<String> badWordsGoneGood = new ArrayList<>();
    int counter = 0;

    public Day19() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day19.txt");
        boolean action = true;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals("")) {
                action = false;
            } else {
                if (action) {
                    String[] lineSplit = line.split(":");
                    int index = Integer.parseInt(lineSplit[0]);
                    if (lineSplit[1].contains("a") || lineSplit[1].contains("b")) {
                        ArrayList<String> tempResult = new ArrayList<>();
                        String result = lineSplit[1].charAt(2) + "";
                        tempResult.add(result);
                        actionLookup.put(index, tempResult);
                    } else {
                        ArrayList<ArrayList<Integer>> tempAction = new ArrayList<>();
                        String[] splitActions = lineSplit[1].split("\\|");
                        for(int x = 0; x < splitActions.length; x++) {
                            ArrayList<Integer> tempSplitAction = new ArrayList<>();
                            String[] numbers = splitActions[x].split(" ");
                            for (int y = 1; y < numbers.length; y++) {
                                tempSplitAction.add(Integer.parseInt(numbers[y]));
                            }
                            tempAction.add(tempSplitAction);
                        }
                        actions.put(index, tempAction);
                    }
                } else {
                    wordsToMatch.add(line);
                }
            }
        }


        makeWords(0);
        countWords();
        countWordsB();

    }

    private void countWords() {
        for(int i = 0; i < wordsToMatch.size(); i++) {
            if(actionLookup.get(0).contains(wordsToMatch.get(i))) {
                counter++;
            }

            badWords.add(wordsToMatch.get(i));
        }
        System.out.println("Day19 = " + counter);
    }

    private void countWordsB() {
        for(String badword : badWords) {
            findGoodWordsInBadWords8(badword, badword, 0, 0);
        }

        System.out.println("Day19B = " + badWordsGoneGood.size());
    }

    private void findGoodWordsInBadWords8(String totalWord, String currentWord, int pos8Turns,  int pos11Turn) {
        ArrayList<String> action8 = actionLookup.get(42);
        for( int i  = 0; i < action8.size(); i++){
            if(currentWord.startsWith(action8.get(i))) {
                currentWord = currentWord.substring(action8.get(i).length());
                pos8Turns++;
                findGoodWordsInBadWords8(totalWord, currentWord, pos8Turns, pos11Turn);
                findGoodWordsInBadWords11(totalWord, currentWord, pos8Turns, pos11Turn);
            }
        }

    }

    private void findGoodWordsInBadWords11(String totalWord, String currentWord, int pos8Turns,  int pos11Turn) {
        ArrayList<String> action11 = actionLookup.get(31);
        for( int i  = 0; i < action11.size(); i++){
            if(currentWord.startsWith(action11.get(i))) {
                currentWord = currentWord.substring(action11.get(i).length());
                pos11Turn++;
                findGoodWordsInBadWords11(totalWord, currentWord, pos8Turns,pos11Turn);
            }
        }
        if(currentWord.equals("")) {
            if(pos8Turns > pos11Turn && pos11Turn >= 1) {

                if(!badWordsGoneGood.contains(totalWord)) {
                    badWordsGoneGood.add(totalWord);
                }
            }
        }
    }

    private ArrayList<String> makeWords(int actionStart){
        ArrayList<String> finalResult = new ArrayList<>();

        if(actionLookup.containsKey(actionStart)) {
            return actionLookup.get(actionStart);
        }

        ArrayList<ArrayList<Integer>> tempActions = actions.get(actionStart);
        for(int i = 0; i < tempActions.size(); i++) {
            ArrayList<Integer> tempAction = tempActions.get(i);
            ArrayList<String> tempResult = new ArrayList<>();
            for(int x = 0; x < tempAction.size(); x++) {
                if(actionLookup.containsKey(tempAction.get(x))) {
                    tempResult = mergeLists(tempResult, actionLookup.get(tempAction.get(x)));
                } else {
                    tempResult = mergeLists(tempResult, makeWords(tempAction.get(x)));
                }
            }
            finalResult.addAll(tempResult);
        }
        actionLookup.put(actionStart, finalResult);
        return finalResult;
    }

    private ArrayList<String> mergeLists (ArrayList<String> leftSide, ArrayList<String> rightSide) {
        ArrayList<String> tempResult = new ArrayList<>();
        if(leftSide.size() == 0) {
            return rightSide;
        }else if(rightSide.size() == 0) {
            return leftSide;
        }

        for( int i = 0; i < leftSide.size(); i++) {
            for(int x = 0; x < rightSide.size(); x++) {
                tempResult.add(leftSide.get(i) + ""+rightSide.get(x));
            }
        }
        return tempResult;
    }
}

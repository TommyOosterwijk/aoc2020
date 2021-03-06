package days.day18;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day18 {

    private Utils utils = new Utils();

    public Day18() throws FileNotFoundException, URISyntaxException {
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
        Scanner scanner = utils.getScannerFromFileName("Day18.txt");
        long totalCounterA = 0l;
        long totalCounterB = 0l;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replaceAll("\\(", "\\( ");
            line = line.replaceAll("\\)", " \\)");
            totalCounterA+= doMath(line);
            totalCounterB+= doMathB(line);
        }

        System.out.println("Day 18 = " + totalCounterA);
        System.out.println("Day 18B = " + totalCounterB);
    }

    enum MathActions {
        ADD,
        MULTIPLY
    };

    private long doMath(String numbersOrActions) {
        long value = 0l;
        MathActions mathAction = null;

        for(int i = 0; i < numbersOrActions.length(); i++){
            if(!(numbersOrActions.charAt(i) == ' ')) {
                if (numbersOrActions.charAt(i) == '(') {
                    long rightSideValue = doMath(numbersOrActions.substring(i + 2));
                    value = doMathAction(value, rightSideValue, mathAction);
                    int leftCurlCounter = 1;
                    for (int i2 = i+2; i2 < numbersOrActions.length(); i2++) {
                        if (numbersOrActions.charAt(i2) == '(') {
                            leftCurlCounter++;
                        }

                        if (numbersOrActions.charAt(i2) == ')') {
                            leftCurlCounter--;
                        }
                        if (leftCurlCounter == 0) {
                            i = i2;
                            break;
                        }
                    }
                } else if (numbersOrActions.charAt(i) == ')') {
                    return value;
                } else if (numbersOrActions.charAt(i) == '+') {
                    mathAction = MathActions.ADD;
                } else if (numbersOrActions.charAt(i) == '*') {
                    mathAction = MathActions.MULTIPLY;
                } else {
                    int number = Integer.parseInt(numbersOrActions.charAt(i)+"");
                    value = doMathAction(value, number, mathAction);

                }
            }
        }
        return value;
    }

    private long doMathB(String numbersOrActions) {
        ArrayList<Long> multiplyNumbers = new ArrayList<>();
        long value = 0l;
        MathActions mathAction = null;

        for(int i = 0; i < numbersOrActions.length(); i++){
            if(!(numbersOrActions.charAt(i) == ' ')) {
                if (numbersOrActions.charAt(i) == '(') {
                    long rightSideValue = doMathB(numbersOrActions.substring(i + 2));
                    value = doMathAction(value, rightSideValue, mathAction);
                    int leftCurlCounter = 1;
                    for (int i2 = i+2; i2 < numbersOrActions.length(); i2++) {
                        if (numbersOrActions.charAt(i2) == '(') {
                            leftCurlCounter++;
                        }

                        if (numbersOrActions.charAt(i2) == ')') {
                            leftCurlCounter--;
                        }
                        if (leftCurlCounter == 0) {
                            i = i2;
                            break;
                        }
                    }
                } else if (numbersOrActions.charAt(i) == ')') {
                    for(long number : multiplyNumbers) {
                        value *= number;
                    }
                    return value;
                } else if (numbersOrActions.charAt(i) == '+') {
                    mathAction = MathActions.ADD;
                } else if (numbersOrActions.charAt(i) == '*') {
                    multiplyNumbers.add(value);
                    value = 0l;
                    mathAction = null;
                } else {
                    int number = Integer.parseInt(numbersOrActions.charAt(i)+"");
                    value = doMathAction(value, number, mathAction);

                }
            }
        }

        for(long number : multiplyNumbers) {
            value *= number;
        }
        System.out.println("Line value = " + value);
        return value;
    }

    private long doMathAction(long leftSideValue, long rightSideValue, MathActions mathAction) {
        if(MathActions.ADD == mathAction) {
            return leftSideValue+= rightSideValue;
        } else if(MathActions.MULTIPLY == mathAction) {
            return leftSideValue*= rightSideValue;
        }

        return rightSideValue;
    }
}

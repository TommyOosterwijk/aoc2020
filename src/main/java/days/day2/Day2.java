package days.day2;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Day2 {

    private Utils utils = new Utils();
    int isValidCountera = 0;
    int isValidCounterb = 0;

    public Day2() throws FileNotFoundException, URISyntaxException {
        Scanner scanner = utils.getScannerFromFileName("Day2.txt");


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] findMin = line.split("-",2);
            findMin[1].replaceAll("-", "");
            String[] findMax = findMin[1].split(" ",2);
            findMax[1].replaceFirst(" ", "");
            String[] findCharacterAndPassword = findMax[1].split(":",2);
            String password = findCharacterAndPassword[1].replaceAll(" ", "");
            password.replaceFirst(":", "");

            Day2a(Integer.parseInt(findMin[0]), Integer.parseInt(findMax[0]),
                    password, findCharacterAndPassword[0].charAt(0));

            Day2b(Integer.parseInt(findMin[0]), Integer.parseInt(findMax[0]),
                    password, findCharacterAndPassword[0].charAt(0));
        }

        System.out.println("Day2a = " + isValidCountera);
        System.out.println("Day2b = " + isValidCounterb);
    }

    private void Day2a(int parseInt, int parseInt1, String s, char charAt) {
        PasswordPolicyA pp = new PasswordPolicyA(parseInt, parseInt1, s, charAt);
        if (pp.isValid()) {
            isValidCountera++;
        }
    }

    private void Day2b(int parseInt, int parseInt1, String s, char charAt) {
        PasswordPolicyB pp = new PasswordPolicyB(parseInt, parseInt1, s, charAt);

        if (pp.isValid()) {
            isValidCounterb++;
        }
    }
}

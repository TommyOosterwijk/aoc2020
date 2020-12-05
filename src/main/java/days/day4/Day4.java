package days.day4;

import utils.Utils;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {

    private Utils utils = new Utils();
    private boolean processingError = false;
    ArrayList<String> eyeColorOptions = new ArrayList<>(
            List.of("amb",
                    "blu",
                    "brn",
                    "gry",
                    "grn",
                    "hzl",
                    "oth"));

    public Day4() throws FileNotFoundException, URISyntaxException {
        int validPassportCounter = 0;
        int validPassportCounterB = 0;
        Scanner scanner = utils.getScannerFromFileName("Day4.txt");

        Passport passporta = new Passport();
        Passport passportb = new Passport();


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equals("")) {
                if(passporta.isValidPassport()) {
                    validPassportCounter++;
                }

                if(!processingError && passportb.isValidPassport()) {
                    validPassportCounterB++;
                }
                passporta = new Passport();
                passportb = new Passport();
                processingError = false;
            } else {
                String[] lineParts = line.split(" ");
                for( String string : lineParts) {
                    String[] keyValue = string.split(":");
                    setPassportPropertA(passporta, keyValue[0], keyValue[1]);
                    if(!processingError) {
                        setPassportPropertB(passportb, keyValue[0], keyValue[1]);
                    }
                }
            }
        }

        if(passporta.isValidPassport()) {
            validPassportCounter++;
        }

        if(!processingError && passportb.isValidPassport()) {
            validPassportCounterB++;
        }

        System.out.println("Day4a = " + validPassportCounter);
        System.out.println("Day4b = " + validPassportCounterB);
    }

    private void setPassportPropertA(Passport passport, String key, String value) {
        switch(key) {
            case "byr":
                passport.setBirthYear(value);
                break;
            case "iyr":
                passport.setIssueYear(value);
                break;
            case "eyr":
                passport.setExpirationYear(value);
                break;
            case "hgt":
                passport.setHeight(value);
                break;
            case "hcl":
                passport.setHairColor(value);
                break;
            case "ecl":
                passport.setEyeColor(value);
                break;
            case "pid":
                passport.setPassportID(value);
                break;
            case "cid":
                passport.setCountryID(value);
                break;
        }
    }

    private void setPassportPropertB(Passport passport, String key, String value) {
        switch(key) {
            case "byr":
                int year = Integer.parseInt(value);
                if(year >=1920 && year <= 2002) {
                    passport.setBirthYear(value);
                } else {
                    processingError = true;
                }
                break;
            case "iyr":
                int issueYear = Integer.parseInt(value);
                if(issueYear >=2010 && issueYear <= 2020) {
                    passport.setIssueYear(value);
                } else {
                    processingError = true;
                }
                break;
            case "eyr":
                int expirationYear = Integer.parseInt(value);
                if(expirationYear >= 2020 && expirationYear <= 2030) {
                    passport.setExpirationYear(value);
                } else {
                    processingError = true;
                }
                break;
            case "hgt":
                processingError = true;
                if(value.contains("cm")) {
                    if(value.length() == 5) {
                        int height = Integer.parseInt(value.substring(0,3));
                        if(height >= 150 && height <= 193 ) {
                            passport.setHeight(value);
                            processingError = false;
                        }
                    }
                } else if(value.contains("in")){
                    if(value.length() == 4) {
                        int height = Integer.parseInt(value.substring(0,2));
                        if(height >= 59 && height <= 76 ) {
                            passport.setHeight(value);
                            processingError = false;
                        }
                    }
                }
                break;
            case "hcl":
                if(value.length() == 7 && value.startsWith("#")) {
                    for( char ch : value.substring(1).toCharArray()) {
                        if(!(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'f')) {
                            processingError = true;
                        }
                    }
                    if(!processingError) {
                        passport.setHairColor(value);
                    }
                } else {
                    processingError = true;
                }
                break;
            case "ecl":
                if(eyeColorOptions.contains(value)) {
                    passport.setEyeColor(value);
                } else {
                    processingError = true;
                }
                break;
            case "pid":
                int pid = 0;
                try {
                    pid = Integer.parseInt(value);
                } catch(Exception e) {
                    // No integer giving. 100% wrong.
                }
                if(value.length() == 9 && pid != 0) {
                    passport.setPassportID(value);
                } else {
                    processingError = true;
                }
                break;
            case "cid":
                passport.setCountryID(value);
                break;
        }
    }
}

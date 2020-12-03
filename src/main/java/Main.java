import days.day1.Day1a;
import days.day1.Day1b;
import days.day2.Day2;
import days.day3.Day3;

public class Main {
    public static void main(String args[]) throws Exception {

        Day1a day1a = new Day1a();
        System.out.println("Day1a = " + day1a.expenseReport(2020));

        Day1b day1b = new Day1b();
        System.out.println("Day1b = " + day1b.expenseReport(2020));

        Day2 day2 = new Day2();

        Day3 day3 = new Day3();
    }
}

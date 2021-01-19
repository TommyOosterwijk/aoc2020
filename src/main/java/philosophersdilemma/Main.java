package philosophersdilemma;

public class Main {
    public static void main(String args[]) throws Exception {
        System.out.println("Start Philosopher!");

        Waiter waiter = new Waiter();
        waiter.start();

        Philosopher p1 = new Philosopher(waiter, 0);
        p1.start();
        Philosopher p2 = new Philosopher(waiter, 1);
        p2.start();
        Philosopher p3 = new Philosopher(waiter, 2);
        p3.start();

        Philosopher p4 = new Philosopher(waiter, 3);
        p4.start();
        Philosopher p5 = new Philosopher(waiter, 4);
        p5.start();

        while(p1.isAlive() || p2.isAlive() || p3.isAlive() || p4.isAlive() || p5.isAlive()) {
            //waiting
        }

        waiter.interrupt();
        System.out.println("All philosophers are done eating.");
    }
}

package philosophersdilemma;

public class Philosopher extends Thread {

    Waiter waiter;
    int id;

    public Philosopher(Waiter waiter, int id) {
        this.waiter = waiter;
        this.id = id;
    }

    public void run() {

        int meal = 1;
        while(meal < 4) {
            waiter.requestPermissionToEat(id);

            if(waiter.canPhilosopherEat(id)) {
                try {
                    System.out.println("Philosopher with ID " + id +
                            " and thread ID " + Thread.currentThread().getId() +
                            " eats meal " + meal);
                    Thread.sleep(1000);
                    meal++;
                    waiter.finishedMeal(id);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

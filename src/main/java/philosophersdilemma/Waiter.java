package philosophersdilemma;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Waiter extends Thread {

    private AtomicBoolean running = new AtomicBoolean(false);
    private AtomicBoolean editEatingStatus = new AtomicBoolean(false);

    ArrayList<Integer> requestToEat = new ArrayList<>();
    ArrayList<Integer> eating = new ArrayList<>();
    ArrayList<Integer> finishedEating = new ArrayList<>();


    public void interrupt() {
        running.set(false);
    }

    public boolean canPhilosopherEat(int id) {
       return eating.contains(id) && !finishedEating.contains(id);
    }

    public void finishedMeal(int id) {
        while(editEatingStatus.get() == true) {
            // waiting...
        }
        editEatingStatus.set(true);
        finishedEating.add(id);
        editEatingStatus.set(false);
    }

    public void requestPermissionToEat(int id) {
        while(editEatingStatus.get() == true) {
            // waiting...
        }
        editEatingStatus.set(true);
         if(!requestToEat.contains(id)) {
             requestToEat.add(id);
         }
        editEatingStatus.set(false);
    }

    public void run() {
        running.set(true);
        while(running.get()) {
            for(int i = 0; i < requestToEat.size(); i++) {
            if(eating.size() >= 2 ) {
                break;
            }

                Integer id = requestToEat.get(i);
                 if(id != null) {
                     int idleft = id - 1;
                     if (idleft < 0) {
                         idleft = 4;
                     }
                     int idright = id + 1;
                     if (idright > 4) {
                         idright = 0;
                     }
                     if (!(eating.contains(id) || eating.contains(idleft) || eating.contains(idright))) {
                         eating.add(id);
                     }
                 }
            }

            while(true) {
                if(finishedEating.size() > 0) {
                    if(editEatingStatus.get() == false) {
                        editEatingStatus.set(true);
                        eating.remove(eating.indexOf(finishedEating.get(0)));
                        requestToEat.remove(requestToEat.indexOf(finishedEating.get(0)));
                        finishedEating.remove(0);
                        editEatingStatus.set(false);
                    }
                } else {
                    break;
                }
            }
        }
    }
}

package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.concurrent.Semaphore;

public class SequentalRepairer implements Runnable {
    private BuildingSemaphore sem;
    private Floor floor;

    public SequentalRepairer(BuildingSemaphore sem, Floor floor) {
        this.sem = sem;
        this.floor = floor;
    }

    @Override
    public void run() {
        int spacesAmount = floor.getSpacesAmount();
        Space[] spaces = floor.getSpaceArray();
        synchronized (sem) {
            for (int i = 0; i < spacesAmount; i++) {
                if (!sem.isRepaired) {
                    sem.notify();
                    System.out.println("Repairing space number " + i + " with total area " + spaces[i].getArea() + " square meters");
                    sem.isRepaired = true;
                    try {
                        if(i==spacesAmount-1) return;
                        sem.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


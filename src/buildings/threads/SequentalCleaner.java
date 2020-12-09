package buildings.threads;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.concurrent.Semaphore;

public class SequentalCleaner implements Runnable{
    private BuildingSemaphore sem;
    private Floor floor;

    public SequentalCleaner(BuildingSemaphore sem, Floor floor) {
        this.sem = sem;
        this.floor = floor;
    }

    @Override
    public void run() {
        int spacesAmount = floor.getSpacesAmount();
        Space[] spaces = floor.getSpaceArray();
        synchronized (sem) {
            for (int i = 0; i < spacesAmount; i++) {
                if (sem.isRepaired) {
                    sem.notify();
                    System.out.println("Cleaning room number " + i + " with total area " + spaces[i].getArea() + " square meters");
                    sem.isRepaired = false;
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


package buildings.threads;

import buildings.interfaces.Floor;

public class Repairer extends Thread  {
    Floor floor;

    public Repairer(Floor floor) {
        this.floor = floor;
    }

    public void run() {
        System.out.println("Repairer thread starting...");
            for (int i = 0; i < floor.getSpacesAmount(); i++) {
                System.out.println("Repairing space number " + i + " with total area " + floor.getSpace(i).getArea() + " square meters");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted...");
                    return;
                }
                if(i==floor.getSpacesAmount()-1) {
                    Thread.currentThread().interrupt();
                    System.out.println("End of Repairer thread!");
                }
            }

    }
}

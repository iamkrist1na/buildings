package buildings.threads;

import buildings.interfaces.Floor;

public class Cleaner extends Thread  {
    Floor floor;

    public Cleaner(Floor floor){
        this.floor = floor;
    }
    public void run() {
        System.out.println("Cleaner thread starting...");
        for(int i=0;i<floor.getSpacesAmount();i++){
            System.out.println("Cleaning room number " + i + " with total area " +  floor.getSpace(i).getArea() + " square meters");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted...");
                return;
            }
            if(i==floor.getSpacesAmount()-1) {
                Thread.currentThread().interrupt();
                System.out.println("End of Cleaner thread!");
            }
        }

    }
}

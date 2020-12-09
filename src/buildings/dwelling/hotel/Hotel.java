package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

import java.util.Arrays;

public class Hotel extends Dwelling {

    public Hotel(int floorsAmount, int[] spacesAmount) {
        super(floorsAmount, spacesAmount);
    }
    public Hotel(Floor[] floors) {
        super(floors);
    }
    public int getStars() {
        int result = 0;
        for (Floor floor : floors) {
            if (floor instanceof HotelFloor) {
                if (result < ((HotelFloor) floor).getStars()) {
                    result = ((HotelFloor) floor).getStars();
                }
            }
        }
        return result;
    }
    @Override
    public Space getBestSpace() {
        double[] coeff = {0.25, 0.5, 1, 1.25, 1.5};
        Space bestSpace = null;
        double result = 0;
        for (Floor floor : floors) {
            if (floor instanceof HotelFloor) {
                for (Space flat : floor.getSpaceArray()) {
                    double score = coeff[((HotelFloor) floor).getStars()] * flat.getArea();
                    if (result < score) {
                        result = score;
                        bestSpace = flat;
                    }
                }
            }
        }
        return bestSpace;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Hotel (").append(getStars()).append(", ").append(getFloorsAmount()).append(", ");
        for (int i = 0; i < getFloorsAmount(); i++) {
            if (floors[i] instanceof HotelFloor) {
                if (i > 0) s.append(", ");
                s.append(floors[i].toString());
            }
        }
        s.append(")");
        return s.toString();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof Hotel))
            return false;
        Hotel other = (Hotel) obj;
        if (!Arrays.equals(floors, other.floors))
            return false;
        return true;
    }
}

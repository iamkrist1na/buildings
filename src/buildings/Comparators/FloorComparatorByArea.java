package buildings.Comparators;

import buildings.interfaces.Floor;

import java.util.Comparator;

public class FloorComparatorByArea implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return Double.compare(o1.getSpacesArea(), o2.getSpacesArea());
    }
}

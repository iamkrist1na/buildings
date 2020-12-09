package buildings.Comparators;

import buildings.interfaces.Floor;

import java.util.Comparator;

public class FloorComparatorBySpacesAmount implements Comparator<Floor> {
    @Override
    public int compare(Floor o1, Floor o2) {
        return Integer.compare(o1.getSpacesAmount(), o2.getSpacesAmount());
    }
}

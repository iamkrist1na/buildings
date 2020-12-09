package buildings.Comparators;

import buildings.interfaces.Space;

import java.util.Comparator;

public class SpaceComparatorByArea implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        return Double.compare(o1.getArea(), o2.getArea());
    }
}

package buildings.Comparators;

import buildings.interfaces.Space;

import java.util.Comparator;

public class SpaceComparatorByRoomsAmount implements Comparator<Space> {
    @Override
    public int compare(Space o1, Space o2) {
        return Integer.compare(o1.getRooms(), o2.getRooms());
    }
}

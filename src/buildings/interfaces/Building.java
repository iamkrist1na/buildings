package buildings.interfaces;

import java.io.Serializable;

public interface Building extends Cloneable, Serializable, Iterable<Floor> {

    int getFloorsAmount();

    int getSpacesAmount();

    double getSpacesArea();

    int getRoomsAmount();

    Floor[] getFloorsArray();

    Floor getFloor(int index);

    void setFloor(int index, Floor oneFloor);

    Space getSpace(int index);

    void setSpace(int index, Space oneSpace);

    void addSpace(int index, Space oneSpace);

    void removeSpace(int index);

    Space getBestSpace();

    Space[] getSpaceArraySorted();

    Object clone();
}

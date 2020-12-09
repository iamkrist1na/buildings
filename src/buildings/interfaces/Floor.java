package buildings.interfaces;

import java.io.Serializable;
import java.util.Iterator;

public interface Floor extends Cloneable, Serializable, Iterable<Space>, Comparable<Floor> {

     int getSpacesAmount();

     double getSpacesArea();

     int getRoomsAmount();

     Space[] getSpaceArray();

     Space getSpace(int index);

     void setSpace(int index, Space space);

     void addSpace(int index, Space space);

     void removeSpace(int index);

     Space getBestSpace();

     Object clone();


}

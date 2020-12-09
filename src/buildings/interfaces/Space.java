package buildings.interfaces;

import java.io.Serializable;

public interface Space extends Cloneable, Serializable, Comparable<Space>{

    int getRooms();

    double getArea();

    void setRooms(int rooms);

    void setArea(double area);

    Object clone();
}

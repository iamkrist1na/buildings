package buildings.office;
import buildings.interfaces.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;
import java.io.Serializable;

public class Office implements Space, Serializable, Cloneable {
    public static final int ConstRoom = 1;
    public static final double ConstArea = 250;
    private int rooms;
    private double area;


    public Office() {
        this(ConstRoom, ConstArea);
    }

    public Office(double area) {
        this(ConstRoom, area);
    }

    public Office(int roomsAmount, double area) {
        if (area <= 0) {
            throw new InvalidSpaceAreaException();
        }
        if (roomsAmount <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.rooms = roomsAmount;
        this.area = area;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        if (rooms <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.rooms = rooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        if (area <= 0) {
            throw new InvalidSpaceAreaException();
        }
        this.area = area;
    }

    @Override
    public String toString() {
        return "Office (" + rooms + ", " + area + ")";
    }

     @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(area);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + rooms;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Office))
            return false;
        Office other = (Office) obj;
        if (Double.doubleToLongBits(area) != Double.doubleToLongBits(other.area))
            return false;
        return rooms == other.rooms;
    }

    @Override
    public Object clone() {
        Object result = null;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int compareTo(Space o) {
        return Double.compare(this.getArea(), o.getArea());
    }
}

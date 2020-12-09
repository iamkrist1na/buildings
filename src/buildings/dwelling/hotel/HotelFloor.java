package buildings.dwelling.hotel;

import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Space;

public class HotelFloor extends DwellingFloor {
    public static final int ConstStars = 1;
    private int stars;


    public HotelFloor(int spacesAmount) {
        super(spacesAmount);
        this.stars = ConstStars;
    }
    public HotelFloor(Space[] spaces) {
        super(spaces);
        this.stars = ConstStars;
    }
    public int getStars(){
        return stars;
    }
    public void setStars(int stars){
        this.stars = stars;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("HotelFloor (").append(getStars()).append(", ").append(getSpacesAmount()).append(", ");
        for (int i = 0; i < flats.length; i++) {
            if (i > 0) s.append(", ");
            s.append(flats[i].toString());
        }
        s.append(")");
        return s.toString();
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + stars;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof HotelFloor))
            return false;
        HotelFloor other = (HotelFloor) obj;
        return stars == other.stars;
    }

}

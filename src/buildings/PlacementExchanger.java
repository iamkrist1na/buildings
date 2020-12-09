package buildings;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.InexchangeableFloorsException;
import exceptions.InexchangeableSpacesException;

public class PlacementExchanger {
    public static boolean canExchangeSpace(Space s1,Space s2){
        return (s1.getArea() == s2.getArea()) && (s1.getRooms() == s2.getRooms());
    }

    public static boolean canExchangeFloor(Floor f1, Floor f2){
        return (f2.getSpacesArea() == f2.getSpacesArea()) && (f1.getSpacesAmount() == f2.getSpacesAmount());
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {

        if(canExchangeSpace(floor1.getSpace(index1), floor2.getSpace(index2))) {
            Space temp = floor1.getSpace(index1);
            floor1.setSpace(index1, floor2.getSpace(index2));
            floor2.setSpace(index2, temp);
        }

        else if(floor1.getSpacesAmount()<index1 || floor2.getSpacesAmount()<index2 || index1<0 || index2<0)
            throw new FloorIndexOutOfBoundsException();

        else throw new InexchangeableSpacesException();
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if (canExchangeFloor(building1.getFloor(index1), building2.getFloor(index2))) {
            Floor temp = building1.getFloor(index1);
            building1.setFloor(index1, building2.getFloor(index2));
            building2.setFloor(index2, temp);
        } else if ((index1 >= building1.getFloorsAmount() || index1 < 0) || (index2 >= building2.getFloorsAmount() || index2 < 0)) {
            throw new FloorIndexOutOfBoundsException();
        } else {
            throw new InexchangeableFloorsException();
        }
    }
}

package buildings;

import java.io.*;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.*;

import buildings.Comparators.FloorComparatorByArea;
import buildings.Comparators.FloorComparatorBySpacesAmount;
import buildings.Comparators.SpaceComparatorByArea;
import buildings.Comparators.SpaceComparatorByRoomsAmount;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.factory.DwellingFactory;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class Buildings implements Serializable, Comparator {


    public static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    public Space createSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    public Space createSpace(int roomsCount, double area) {
        return buildingFactory.createSpace(roomsCount, area);
    }

    public Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }


    public static void outputBuilding(Building building, OutputStream out) throws IOException { //1
        DataOutputStream dos = new DataOutputStream(out);
        Floor floor;
        Space space;
        dos.writeInt(building.getFloorsAmount());
        for (int i = 0, floorsAmount = building.getFloorsAmount(); i < floorsAmount; i++) {
            floor = building.getFloor(i);
            dos.writeInt(floor.getSpacesAmount());
            for (int j = 0, spacesAmount = floor.getSpacesAmount(); j < spacesAmount; j++) {
                space = floor.getSpace(j);
                dos.writeInt(space.getRooms());
                dos.writeDouble(space.getArea());
            }
        }
    }

    public static Building inputBuilding(InputStream in) throws IOException {  //2
        DataInputStream dis = new DataInputStream(in);
        Floor[] floors = new Floor[dis.readInt()];
        for (int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            Space[] flats = new Space[dis.readInt()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                flats[j] = buildingFactory.createSpace(dis.readInt(), dis.readDouble());
            }
            floors[i] = buildingFactory.createFloor(flats);
        }
        return buildingFactory.createBuilding(floors);
    }

    public static void writeBuilding(Building building, Writer out) { //3
        PrintWriter pwo = new PrintWriter(out);
        Floor floor;
        Space space;
        pwo.print(building.getFloorsAmount() + " ");
        for (int i = 0, floorsAmount = building.getFloorsAmount(); i < floorsAmount; i++) {
            floor = building.getFloor(i);
            pwo.print(floor.getSpacesAmount() + " ");
            for (int j = 0, spacesAmount = floor.getSpacesAmount(); j < spacesAmount; j++) {
                space = floor.getSpace(j);
                pwo.print(space.getRooms() + " ");
                pwo.print(space.getArea() + " ");
            }
        }
    }


    public static Building readBuilding(Reader in) throws IOException { //4
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        Floor[] floors = new Floor[(int) st.nval];
        for (int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            st.nextToken();
            Space[] flats = new Space[(int) st.nval];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                st.nextToken();
                flats[j] = buildingFactory.createSpace((int)st.nval, st.nval);
            }
            floors[i] = buildingFactory.createFloor(flats);
        }
        return buildingFactory.createBuilding(floors);

    }

    public static Building readBuilding(Scanner in) { //4
        Floor[] floors = new Floor[in.nextInt()];
        for (int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            Space[] flats = new Space[in.nextInt()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                flats[j] = buildingFactory.createSpace(in.nextInt(), in.nextDouble());
            }
            floors[i] = buildingFactory.createFloor(flats);
        }
        return buildingFactory.createBuilding(floors);
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        ObjectOutputStream out1 = new ObjectOutputStream(out);
        out1.writeObject(building);
    }

    public static Building deserializeBuilding(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream in1 = new ObjectInputStream(in);
        return (Building) in1.readObject();
    }

    //сортировка массива помещений этажа по возрастанию площадей помещений
    public static void sortAscend(Floor floor) {
        SpaceComparatorByArea comparator = new SpaceComparatorByArea();
        for (int i = 0; i < floor.getSpacesAmount(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < floor.getSpacesAmount(); j++) {
                if (comparator.compare(floor.getSpace(j),floor.getSpace(minIndex)) < 0)
                    minIndex = j;
            }
            Space swapBuf = floor.getSpace(i);
            floor.setSpace(i, floor.getSpace(minIndex));
            floor.setSpace(minIndex, swapBuf);
        }

    }


    //сортировка массива помещений на этаже по убыванию количества комнат
    public static void sortDescend(Floor floor) {
        SpaceComparatorByRoomsAmount comparator = new SpaceComparatorByRoomsAmount();
        for (int i = 0; i < floor.getSpacesAmount(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < floor.getSpacesAmount(); j++) {
                if (comparator.compare(floor.getSpace(j), floor.getSpace(minIndex)) > 0)
                    minIndex = j;
            }
            Space swapBuf = floor.getSpace(i);
            floor.setSpace(i, floor.getSpace(minIndex));
            floor.setSpace(minIndex, swapBuf);
        }
    }

    //сортировка массива этажей здания по возрастанию количества помещений на этаже
    public static void sortAscend(Building building) {
        FloorComparatorBySpacesAmount comparator = new FloorComparatorBySpacesAmount();
        for (int i = 0; i < building.getFloorsAmount(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < building.getFloorsAmount(); j++) {
                if (comparator.compare(building.getFloor(j), building.getFloor(minIndex)) < 0)
                    minIndex = j;
            }
            Floor swapBuf = building.getFloor(i);
            building.setFloor(i, building.getFloor(minIndex));
            building.setFloor(minIndex, swapBuf);
        }
    }

    //сортировка массива этажей в здании по убыванию общей площади помещений этажа
    public static void sortDescend(Building building) {
        FloorComparatorByArea comparator = new FloorComparatorByArea();
        for (int i = 0; i < building.getFloorsAmount(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < building.getFloorsAmount(); j++) {
                if (comparator.compare(building.getFloor(j), building.getFloor(minIndex)) > 0)
                    minIndex = j;
            }
            Floor swapBuf = building.getFloor(i);
            building.setFloor(i, building.getFloor(minIndex));
            building.setFloor(minIndex, swapBuf);
        }
    }
    //параметризованный метод сортировки массива по возрастанию
    public static <T extends Comparable<T>> void sortInAscend(T[] t) {
        sortAscend(t);

    }

    //параметризованный метод сортировки массива по убыванию
    public static <T> void sortInDescend(T[] t, Comparator<T> comparator) {
        sortDescend(t);
    }


    //Добавьте в класс Buildings со статическими методами обработки реализацию метода synchronizedFloor,
    // возвращающего ссылку на оболочку указанного объекта этажа, безопасную с точки зрения многопоточности.
    public static Floor synchronizedFloor (Floor floor){
        return new SynchronizedFloor(floor);
    }
}


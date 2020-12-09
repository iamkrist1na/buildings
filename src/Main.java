import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;
import buildings.threads.*;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        DwellingFloor dwellingFloor1 = new DwellingFloor(new Flat[] {new Flat(11,11), new Flat(21,21), new Flat(31,31),new Flat(4,4), new Flat(5,5), new Flat(6,6)});
        DwellingFloor dwellingFloor2 = new DwellingFloor((new Flat[] {new Flat(3,3), new Flat(4,4)}));
        Dwelling dwelling = new Dwelling(new DwellingFloor[]{dwellingFloor1, dwellingFloor2});
        OfficeFloor officeFloor1 = new OfficeFloor(new Office[] {new Office(11,11),new Office(112,12), new Office(13,13)});
        OfficeFloor officeFloor2 = new OfficeFloor(new Office[] {new Office(23,23),new Office(24,24), new Office(213,213), new Office(2113,2113)});
        OfficeBuilding officeBuilding = new OfficeBuilding((new OfficeFloor[] {officeFloor1, officeFloor2}));
        System.out.println(officeBuilding);
        Buildings.sortDescend(officeBuilding);
        System.out.println(officeBuilding);

        //Thread r = new Repairer(dwellingFloor1);
        //Thread c = new Cleaner(dwellingFloor1);
        //r.start();
        //r.interrupt();
        //c.start();
/*
        BuildingSemaphore sem = new BuildingSemaphore();
        Thread repairer = new Thread(new SequentalRepairer(sem, officeFloor1));
        Thread cleaner = new Thread(new SequentalCleaner(sem, officeFloor1));
        repairer.start();
        cleaner.start();
 */


        /*
        Flat testFlat = new Flat();
        Office testOffice = new Office();
        DwellingFloor testDwellingFloor = new DwellingFloor(9);
        OfficeFloor testOfficeFloor = new OfficeFloor(3);
        HotelFloor testHotelFloor = new HotelFloor(3);
        int[] spacesAmount = {1, 2, 3};
        Building testDwelling = new Dwelling(spacesAmount.length, spacesAmount);
        OfficeBuilding testOfficeBuilding = new OfficeBuilding(spacesAmount.length, spacesAmount);


        //сериализация в файл
    	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.txt"));
		out.writeObject(testDwelling);
		out.close();

        //десериализация из файла и просмотр результатов
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.txt"));
		Building dsbDwelling = (Dwelling)in.readObject();
		in.close();
        System.out.println(dsbDwelling.getFloorsAmount() + " " + dsbDwelling.getSpacesAmount());
         */



        /*
        //тест toSting'ов
        System.out.println(testFlat);
        System.out.println(testOffice);
        System.out.println(testDwellingFloor);
        System.out.println(testHotelFloor);
        System.out.println(testOfficeFloor);
        System.out.println(testDwelling);
        System.out.println(testOfficeBuilding.getFloorsAmount());
        System.out.println(testOfficeBuilding);

        Scanner sc = new Scanner(System.in);
        HotelFactory df = new HotelFactory();
        Buildings.setBuildingFactory(df);
        Buildings b = new Buildings();
        b.createBuilding(5, new int[]{3, 3, 2, 2, 1});
        //Buildings.readBuilding(sc);
        sc.close();
        BuildingFactory bf = new HotelFactory();
        Space[] space =  {new Flat(), new Flat()};
        bf.createFloor(space);
         */
    }
}

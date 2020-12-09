package buildings.net.client;

import buildings.Buildings;
import buildings.dwelling.Dwelling;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class BinaryClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int[] spacesAmount = {1, 2, 3};
        Building testDwelling = new Dwelling(spacesAmount.length, spacesAmount);
        try {
            Files.createFile(Path.of("C:\\Users\\User\\Desktop\\Task2\\type.txt"));
            Files.createFile(Path.of("C:\\Users\\User\\Desktop\\Task2\\info.txt"));
            Files.createFile(Path.of("C:\\Users\\User\\Desktop\\Task2\\cost.txt"));
            System.out.println("Файлы type.txt, info.txt, cost.txt созданы!");
        }catch (Exception ignored) {
            System.out.println("Файлы type.txt, info.txt, cost.txt созданы!");
        }

        File t = new File("type.txt");
        File i = new File("info.txt");
        File c = new File("cost.txt");
        Scanner type = new Scanner(t);
        Reader in = new FileReader("info.txt");
        FileOutputStream fos = new FileOutputStream(c);
        PrintStream writeCostInFile = new PrintStream(fos);
        Socket socket = null;

        try {
            socket = new Socket(InetAddress.getByName("localhost"), 8889);
        }catch (Exception e) {
            System.out.println("Ошибка при подключении к сокету");
        }
        assert socket != null;
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("Подключение клиента к сокету выполнено!");

        while (type.hasNext() && !socket.isOutputShutdown()) {
            System.out.println("Клиент считывает данные о здании...");
            String tmp = type.next();
            dos.writeBytes(tmp);

            switch (tmp) {
                case "Hotel":
                    Buildings.setBuildingFactory(new HotelFactory());
                case "OfficeBuilding":
                    Buildings.setBuildingFactory(new OfficeFactory());
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
            }
            Building building = Buildings.readBuilding(in);
            Buildings.outputBuilding(building, dos);
            dos.flush();
            System.out.println("Клиент отправил сообщение на сервер.");
            writeCostInFile.println((dis.read()));
            System.out.println("Клиент прочитал сообщение от сервера и записал его в файл.");
        }

        writeCostInFile.close();
        dis.close();
        dos.close();
        in.close();
        System.out.println("Завершение соединения на клиенте успешно завершено!");
    }
}


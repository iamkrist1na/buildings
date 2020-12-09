package buildings.office;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс buildings.office.OfficeBuilding офисного здания.
 * Работа класса должна быть основана на двусвязном циклическом списке этажей с выделенной головой.
 * Номер офиса явно не хранится.
 * Нумерация офисов в здании сквозная и начинается с нуля.
 */
public class OfficeBuilding implements Building, Serializable {

    private static class Node {
        Node next;
        Node previous;
        Floor officeFloor;
    }

    private Node head;

    private OfficeBuilding() {
        head = new Node();
        head.next = head;
        head.previous = head;
    }

    /**
     * Конструктор может принимать количество этажей и массив количества офисов по этажам.
     */

    public OfficeBuilding(int size, int[] officesAmountOnFloor) {
        this();
        Node current = head;
        for (int i = 0; i < size; i++) {
            Node x = new Node();
            x.officeFloor = new OfficeFloor(officesAmountOnFloor[i]);
            current.next = x;
            current.next.previous = current;
            current = current.next;
        }
        current.next = head.next;
        head.next.previous = current;
    }

    /**
     * Конструктор может принимать массив этажей офисного здания.
     */
    public OfficeBuilding(Floor[] officeFloors) {
        this();
        Node current = head;
        for (Floor officeFloor : officeFloors) {
            Node x = new Node();
            current.next = x;
            x.previous = current;
            x.officeFloor = officeFloor;
            current = x;
        }
        current.next = head.next;
        head.next.previous = current;
    }

    /**
     * Создайте приватный метод получения узла по его номеру.
     */

    private Node getNode(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Создайте приватный метод добавления узла в список по номеру.
     */
    private void addNode(Node node, int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) temp = temp.next;
        temp.next.previous = node;
        node.next = temp.next;
        temp.next = node;
        node.previous = temp;
    }

    /**
     * Создайте приватный метод удаления узла из списка по его номеру.
     */
    public void removeNode(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        temp.next.previous = temp;
    }

    /**
     * Создайте метод получения общего количества этажей здания.
     */
    public int getFloorsAmount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result++;
        } while (current.next != head.next);
        return result;
    }

    /**
     * Создайте метод получения общего количества офисов здания.
     */
    public int getSpacesAmount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.officeFloor.getSpacesAmount();
        } while (current.next != head.next);
        return result;
    }

    /**
     * Создайте метод получения общей площади помещений здания.
     */
    public double getSpacesArea() {
        double result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.officeFloor.getSpacesArea();
        } while (current.next != head.next);
        return result;
    }

    /**
     * Создайте метод получения общего количества комнат здания.
     */
    public int getRoomsAmount() {
        int result = 0;
        Node current = head;
        do {
            current = current.next;
            result += current.officeFloor.getRoomsAmount();
        } while (current.next != head.next);
        return result;
    }

    /**
     * Создайте метод получения массива этажей офисного здания.
     */
    public Floor[] getFloorsArray() {
        Floor[] officeFloors = new Floor[getFloorsAmount()];
        Node current = head;
        for (int i = 0; i < getFloorsAmount(); i++) {
            current = current.next;
            officeFloors[i] = current.officeFloor;
        }
        return officeFloors;
    }

    /**
     * Создайте метод получения объекта этажа по его номеру в здании.
     */
    public Floor getFloor(int index) {
        if ((index > this.getFloorsAmount()-1) || (index < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        return getNode(index+1).officeFloor;
    }

    /**
     * Создайте метод изменения этажа по его номеру в здании и ссылке на обновленный этаж.
     */
    public void setFloor(int index, Floor newFloor) {
        if ((index > getFloorsAmount()-1) || (index < 0)) {
            throw new FloorIndexOutOfBoundsException();
        }
        getNode(index+1).officeFloor = newFloor;
    }

    /**
     * Создайте метод получения объекта офиса по его номеру в офисном здании.
     */
    public Space getSpace(int index) {
        if ((index >= getSpacesAmount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsAmount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesAmount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesAmount() - (sum - index);
                return current.officeFloor.getSpace(indexOnFloor);
            }
        }
        return null;
    }

    /**
     * Создайте метод изменения объекта офиса по его номеру в доме и ссылке типа офиса.
     */
    public void setSpace(int index, Space newOffice) {
        if ((index >= getSpacesAmount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsAmount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesAmount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesAmount() - (sum - index);
                current.officeFloor.setSpace(indexOnFloor, newOffice);
            }
        }
    }

    /**
     * Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на офис.
     */
    public void addSpace(int index, Space newOffice) {
        if ((index > getSpacesAmount()) || (index < -1)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsAmount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesAmount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesAmount() - (sum - index);
                current.officeFloor.addSpace(indexOnFloor, newOffice);
            }
        }
    }

    /**
     * Создайте метод удаления офиса по его номеру в здании.
     */
    public void removeSpace(int index) {
        if ((index >= getSpacesAmount()) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Node current = head;
        int sum = 0;
        for (int i = 0; i < getFloorsAmount(); i++) {
            current = current.next;
            sum += current.officeFloor.getSpacesAmount();
            if (sum >= index) {
                int indexOnFloor = current.officeFloor.getSpacesAmount() - (sum - index);
                current.officeFloor.removeSpace(indexOnFloor);
            }
        }
    }

    /**
     * Создайте метод getBestSpace() получения самого большого по площади офиса здания.
     */
    public Space getBestSpace() {
        if (head.next == head.previous) {
            return null;
        }
        Node current = head.next;
        Space bestOffice = current.officeFloor.getBestSpace();
        for (int i = 1; i < getFloorsAmount(); i++) {
            current = current.next;
            if (bestOffice.getArea() < current.officeFloor.getBestSpace().getArea()) {
                bestOffice = current.officeFloor.getBestSpace();
            }
        }
        return bestOffice;
    }

    /**
     * Создайте метод получения отсортированного по убыванию площадей массива офисов.
     */
    public Space[] getSpaceArraySorted() {
        Space[] nonSortedOffices = new Space[getSpacesAmount()];
        Node current = head;
        int sum = 0;
        for (int i = 1; i < getFloorsAmount(); i++) {
            current = current.next;
            System.arraycopy(current.officeFloor.getSpaceArray(), 0, nonSortedOffices, sum, current.officeFloor.getSpacesAmount());
            sum += current.officeFloor.getSpacesAmount();
        }
        Arrays.sort(nonSortedOffices, Comparator.comparingDouble(Space::getArea));
        return nonSortedOffices;
    }

    /**
     * Добавьте в классы зданий Dwelling, buildings.office.OfficeBuilding реализации метода String toString().
     * Методы выводят текущее количество этажей и соответствующую информацию о каждом помещении каждого этажа, используя toString() уровня этажа и помещения.
     * Например, Dwelling (2, DwellingFloor (3, Flat (...), ...), DwellingFloor (3, Flat (...), ...)
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Floor[] floors = getFloorsArray();
        s.append("OfficeBuilding (").append(this.getFloorsAmount()).append(", ");
        for (int i = 0; i < floors.length; i++) {
            if (i > 0) s.append(", ");
            s.append(floors[i].toString());
        }
        s.append(")");
        return s.toString();
    }

    /**
     * Добавьте в классы зданий реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является зданием соответствующего типа, количество этажей совпадает и сами этажи эквивалентны помещениям текущего объекта.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((head == null) ? 0 : head.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof OfficeBuilding))
            return false;
        OfficeBuilding other = (OfficeBuilding) obj;
        if (head == null) {
            return other.head == null;
        } else return head.equals(other.head);
    }

    /**
     * Добавьте в интерфейс и классы зданий метод Object clone().
     * Клонирование должно быть глубоким.
     */
    @Override
    public Object clone() {
        OfficeBuilding result;
        try {
            result = (OfficeBuilding) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        result = new OfficeBuilding(this.getFloorsArray());
        for (int i = 0; i < this.getFloorsAmount()-2; i++) {
                result.setFloor(i, (Floor) this.getFloor(i).clone());
                for (int j = 0; j < this.getFloor(i).getSpacesAmount()-2; i++) {
                    result.getFloor(i).setSpace(j, (Space) this.getSpace(j).clone());
                }
            }
        return result;
    }

    @Override
    public Iterator<Floor> iterator() {
        return new Iterator<>() {
            int index = 0;
            final int length = getFloorsAmount();
            @Override
            public boolean hasNext() {
                return index++ <= length;
            }

            @Override
            public Floor next() {
                if (index++>length) throw new NoSuchElementException();
                return (Floor) getNode(index++);
            }
        };
    }
}
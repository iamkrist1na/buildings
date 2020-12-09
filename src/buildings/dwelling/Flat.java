package buildings.dwelling;

import java.io.Serializable;
import buildings.interfaces.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

public class Flat implements Space, Serializable, Cloneable {
    public static final float ConstArea = 50;
    public static final int ConstRoom = 2;

    private double area;
    private int rooms;

    /**
     * Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м. (эти числа должны быть константами в классе)
     */
    public Flat() {
        this(ConstRoom, ConstArea);
    }

    /**
     * Конструктор может принимать площадь квартиры (создается квартира с 2 комнатами).
     */
    public Flat(double area) {
        this(ConstRoom, area);
    }

    /**
     * Конструктор может принимать площадь квартиры и количество комнат.
     */
    public Flat(int roomsAmount, double area) {
        if (area <= 0) {
            throw new InvalidSpaceAreaException();
        }
        if (roomsAmount <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.area = area;
        this.rooms = roomsAmount;
    }

    /**
     * Создайте метод получения количества комнат в квартире.
     */
    public int getRooms() {
        return rooms;
    }

    /**
     * Создайте метод получения площади квартиры.
     */
    public double getArea() {
        return area;
    }

    /**
     * Создайте метод изменения площади квартиры.
     */
    public void setArea(double area) {
        if (area <= 0) {
            throw new InvalidSpaceAreaException();
        }
        this.area = area;
    }

    /**
     * Создайте метод изменения количества комнат в квартире.
     */
    public void setRooms(int roomsAmount) {
        if (roomsAmount <= 0) {
            throw new InvalidRoomsCountException();
        }
        this.rooms = roomsAmount;
    }

    /**
     * Добавьте в классы помещений Flat и Office реализации метода String toString(),
     * выводящего тип помещения, текущее количество комнат помещения и его площадь через запятую в скобках.
     * Например, Flat (3, 55.0)
     */
    @Override
    public String toString() {
        return "Flat (" + rooms + ", " + area + ")";
    }

    /**
     * Добавьте в классы помещений реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является помещением соответствующего типа и все соответствующие параметры помещений равны.
     * Добавьте в классы помещений реализации методов int hashCode(). Значение хеш-функции помещения можно вычислить как значение последовательного побитового исключающего ИЛИ битового представления количества комнат помещения, и, например, первых 4 байтов и вторых 4-х байтов (для типа double) битовых представлений площадей помещений этажа (следует воспользоваться вспомогательными методами классов-оберток).
     */
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
        if (getClass() != obj.getClass())
            return false;
        Flat other = (Flat) obj;
        if (Double.doubleToLongBits(area) != Double.doubleToLongBits(other.area))
            return false;
        return rooms == other.rooms;
    }

    /**
     * Добавьте в интерфейс и классы помещений метод Object clone().
     * Клонирование должно быть глубоким.
     */
    public Object clone() {
        Object result;
        try {
            result = super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }

    @Override
    public int compareTo(Space o) {
        return Double.compare(this.getArea(), o.getArea());
    }
}

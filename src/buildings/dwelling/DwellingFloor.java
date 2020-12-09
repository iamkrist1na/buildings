package buildings.dwelling;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.SpaceIndexOutOfBoundsException;

public class DwellingFloor implements Floor {
    protected Space[] flats;



    /**
     * Конструктор может принимать количество квартир на этаже.
     */
    public DwellingFloor(int flatsAmount) {
        this.flats = new Space[flatsAmount];
        for (int i = 0; i < flatsAmount; i++) {
            flats[i] = new Flat();
        }
    }

    /**
     * Конструктор может принимать массив квартир этажа.
     */
    public DwellingFloor(Space[] flats) {
        this.flats = flats;
    }

    /**
     * Создайте метод получения количества квартир на этаже.
     */
    public int getSpacesAmount() {
        return flats.length;
    }

    /**
     * Создайте метод получения общей площади квартир этажа.
     */
    public double getSpacesArea() {
        double result = 0;
        for (Space flat : flats) {
            result += flat.getArea();
        }
        return result;
    }

    /**
     * Создайте метод получения общего количества комнат этажа.
     */
    public int getRoomsAmount() {
        int result = 0;
        for (Space flat : flats) {
            result += flat.getRooms();
        }
        return result;
    }

    /**
     * Создайте метод получения массива квартир этажа.
     */
    public Space[] getSpaceArray() {
        return flats;
    }

    /**
     * Создайте метод получения объекта квартиры, по ее номеру на этаже.
     */
    public Space getSpace(int index) {
        if ((index >= flats.length) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        return flats[index];
    }

    /**
     * Создайте метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
     */
    public void setSpace(int index, Space oneFlat) {
        if ((index >= flats.length) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        this.flats[index] = oneFlat;
    }

    /**
     * Создайте метод добавления новой квартиры на этаже по будущему номеру квартиры.
     */
    public void addSpace(int index, Space oneFlat) {
        if ((index >= flats.length) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newFlats = new Space[flats.length + 1];
        for (int i = 0; i < flats.length; i++) {
            newFlats[i] = flats[i];
        }
        for (int i = newFlats.length; i >= index; i--) {
            newFlats[i] = newFlats[i - 1];
        }
        newFlats[index] = oneFlat;
        flats = newFlats;
    }

    /**
     * Создайте метод удаления квартиры по ее номеру на этаже.
     */
    public void removeSpace(int index) {
        if ((index >= flats.length) || (index < 0)) {
            throw new SpaceIndexOutOfBoundsException();
        }
        Space[] newFlats = new Space[flats.length - 1];
        for (int i = 0; i < index; i++) {
            newFlats[i] = flats[i];
        }
        for (int i = index + 1; i < flats.length; i++) {
            newFlats[i - 1] = flats[i];
        }
        flats = newFlats;
    }

    /**
     * Создайте метод getBestSpace() получения самой большой по площади квартиры этажа.
     */
    public Space getBestSpace() {
        double bestSpace = 0;
        Space flatBestSpace = null;
        for (Space flat : flats) {
            if (flat.getArea() > bestSpace) {
                bestSpace = flat.getArea();
                flatBestSpace = flat;
            }
        }

        return flatBestSpace;
    }

    /**
     * Добавьте в классы этажей DwellingFloor, OfficeFloor реализации метода String toString().
     * Методы выводят тип этажа, текущее количество помещений этажа и соответствующую информацию по каждому помещению, используя метод toString() помещения.
     * Например,
     * DwellingFloor (3, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0))
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("DwellingFloor (").append(getSpacesAmount()).append(", ");
        for (int i = 0; i < flats.length; i++) {
            if (i > 0) s.append(", ");
            s.append(flats[i].toString());
        }
        s.append(")");
        return s.toString();
    }

    /**
     * Добавьте в классы этажей реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является этажом соответствующего типа, количество помещений совпадает и сами помещения эквивалентны помещениям текущего объекта. Для экземпляров класса HotelFloor также должно проверяться совпадение количества звёзд.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(flats);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof DwellingFloor))
            return false;
        DwellingFloor other = (DwellingFloor) obj;
        return Arrays.equals(flats, other.flats);
    }

    /**
     * Добавьте в интерфейс и классы этажей метод Object clone().
     * Клонирование должно быть глубоким.
     */
    @Override
    public Object clone() {
        DwellingFloor result = null;
        try {
            result = (DwellingFloor) super.clone();
            result.flats = flats.clone();
            for (int i = 0; i < result.getSpacesAmount(); i++) {
                result.setSpace(i, (Space) this.getSpace(i).clone());
            }
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        return result;
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<>() {


            final int length = flats.length;
            int index = -1;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public Space next() {
                if (index >= length) throw new NoSuchElementException();
                else return flats[index++];
            }
        };
    }

    @Override
    public int compareTo(Floor o) {
        return Integer.compare(this.getSpacesAmount(), o.getSpacesAmount());
    }
}

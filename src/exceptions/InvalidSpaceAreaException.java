package exceptions;

public class InvalidSpaceAreaException extends IllegalArgumentException{
    public static void main(String[] args) {
        System.out.println("Площадь не может быть отрицательной!");
    }
}

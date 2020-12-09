package exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException{
    public static void main(String[] args) {
        System.out.println("Комнат не может быть меньше одной!");
    }
}

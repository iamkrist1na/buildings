package exceptions;

public class BuildingUnderArrestException extends Throwable{
    public BuildingUnderArrestException() {
        System.err.println("Здание под арестом!");
    }
}

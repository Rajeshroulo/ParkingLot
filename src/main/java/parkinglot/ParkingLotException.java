package parkinglot;

public class ParkingLotException extends RuntimeException {

    enum ExceptionType {
        ENTERED_NULL,ENTERED_EMPTY,PARKINGLOT_IS_FULL,
        NUMBER_EXISTING,NUMBER_IS_NOT_PRESENT,ALL_PARKING_LOTS_ARE_FULL
        ,NOT_IN_THE_SLOTS
    }

    ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}

package contract;

import java.util.List;

public interface HotelRoomContact {
    void assignGuest(String guestName, String guestSurname);

    void checkOut();

    List<String> getHistory();

    String getCurrentGuestName();

    String getCurrentGuestSurname();

    boolean isOccupied();
}

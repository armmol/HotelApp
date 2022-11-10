package contract;

import java.util.List;

/**
 * Hotel Room Interface Contract
 */
public interface HotelRoomContact {
    /**
     * Function to assign guest to a room.
     *
     * @param guestName    First Name of guest.
     * @param guestSurname Surname of guest.
     */
    void assignGuest(String guestName, String guestSurname);

    /**
     * Function to check guest out of room.
     */
    void checkOut();

    /**
     * Function to get the History of Array of room that contains names and surnames of guests who have occupied/are occupying the room.
     *
     * @return history of guests who have occupied/are occupying room.
     */
    List<String> getHistory();


    /**
     * Function to return name and surname of guest currently occupying the room.
     *
     * @return Name and surname of guest currently occupying the room.
     */
    String getCurrentGuestName();

    /**
     * Function to return a boolean value to check if a room is available or occupied.
     *
     * @return room status about being occupied or available.
     */
    boolean isOccupied();
}

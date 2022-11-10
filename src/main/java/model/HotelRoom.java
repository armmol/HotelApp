package model;

import contract.HotelRoomContact;

import java.util.ArrayList;
import java.util.List;

public class HotelRoom implements HotelRoomContact {
    private final List<String> history;
    private boolean occupied;

    /**
     * Constructor for HotelRoom class.
     */
    public HotelRoom() {
        history = new ArrayList<>();
        occupied = false;
    }

    /**
     * Function to assign guest to a room.
     *
     * @param guestName    First Name of guest.
     * @param guestSurname Surname of guest.
     */
    @Override
    public void assignGuest(String guestName, String guestSurname) {
        occupied = true;
        history.add(String.format("%s %s", guestName, guestSurname));
    }

    /**
     * Function to check guest out of room.
     */
    @Override
    public void checkOut() {
        occupied = false;
    }

    /**
     * Function to get the History of Array of room that contains names and surnames of guests who have occupied/are occupying the room.
     *
     * @return history of guests who have occupied/are occupying room.
     */
    @Override
    public List<String> getHistory() {
        return history;
    }

    /**
     * Function to return name and surname of guest currently occupying the room.
     *
     * @return Name of guest currently occupying the room.
     */
    @Override
    public String getCurrentGuestName() {
        return history.get(history.size() - 1);
    }


    /**
     * Function to return a boolean value to check if a room is available or occupied.
     *
     * @return room status about being occupied or available.
     */
    @Override
    public boolean isOccupied() {
        return occupied;
    }
}

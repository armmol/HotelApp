package model;

import contract.HotelRoomContact;

import java.util.ArrayList;
import java.util.List;

public class HotelRoom implements HotelRoomContact {
    private List<String> history;
    private String currentGuestName;
    private String currentGuestSurname;
    private boolean occupied;

    public HotelRoom() {
        history = new ArrayList<>();
        currentGuestName = "";
        currentGuestSurname = "";
        occupied = false;
    }

    @Override
    public void assignGuest(String guestName, String guestSurname) {
        currentGuestName = guestName;
        currentGuestSurname = guestSurname;
        occupied = true;
        history.add(String.format("%s %s", guestName, guestSurname));
    }

    @Override
    public void checkOut() {
        currentGuestName = "";
        currentGuestSurname = "";
        occupied = false;
    }

    @Override
    public List<String> getHistory() {
        return history;
    }

    @Override
    public String getCurrentGuestName() {
        return currentGuestName;
    }

    @Override
    public String getCurrentGuestSurname() {
        return currentGuestSurname;
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }
}

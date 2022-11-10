package controller;

import form.HotelForm;
import model.HotelRoom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static utility.ConstantStings.*;

class ServiceControllerTest {


    private ServiceController controller;
    private ArrayList<HotelRoom> hotelRooms;

    @BeforeEach
    public void setUp() {
        hotelRooms = new ArrayList<>(Arrays.asList(new HotelRoom(), new HotelRoom()));
        controller = new ServiceController(new HotelForm(), hotelRooms);
    }

    @AfterEach
    public void tearDown() {
        for (HotelRoom room : hotelRooms) {
            room.checkOut();
        }
        controller = new ServiceController(new HotelForm(), new ArrayList<>());
    }

    @Test
    void addRoom() {
        controller.addRoom();
        assertEquals(3, hotelRooms.size());
    }

    @Test
    void buildGuestList() {
        hotelRooms.get(0).assignGuest("Dessault", "Systems");
        StringBuilder builder = new StringBuilder();
        for (String guest : hotelRooms.get(0).getHistory()) {
            builder.append(guest).append("\n");
        }
        assertEquals(builder.toString(), controller.buildGuestList(hotelRooms.get(0)));
    }

    @Test
    void buildRoomReview() {
        assertEquals(AVAILABLE, controller.buildRoomReview(hotelRooms.get(0)));
        hotelRooms.get(0).assignGuest("Dessault", "Systems");
        assertEquals(OCCUPIED + GUEST + hotelRooms.get(0).getCurrentGuestName(), controller.buildRoomReview(hotelRooms.get(0)));
    }

    @Test
    void buildRoomSummary() {
        assertEquals(NO_ACTIVITY, controller.buildRoomSummary(hotelRooms.get(0)));
        hotelRooms.get(0).assignGuest("Dessault", "Systems");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < hotelRooms.get(0).getHistory().size(); i++) {
            if (i == hotelRooms.get(0).getHistory().size() - 1 && hotelRooms.get(0).isOccupied()) {
                builder.append(hotelRooms.get(0).getHistory().get(i)).append(CHECKED_IN).append(hotelRooms.get(0).getHistory().get(i)).append(IS_OCCUPYING);
            } else {
                builder.append(hotelRooms.get(0).getHistory().get(i)).append(CHECKED_IN).append(hotelRooms.get(0).getHistory().get(i)).append(CHECKED_OUT);
            }
        }
        assertEquals(builder.toString(), controller.buildRoomSummary(hotelRooms.get(0)));
    }
}
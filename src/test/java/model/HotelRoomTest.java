package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Class for HotelRoom Class functions.
 */
class HotelRoomTest {

    private HotelRoom room;

    @BeforeEach
    public void setUp() {
        room = new HotelRoom();
    }

    @AfterEach
    public void tearDown() {
        room.checkOut();
        room = new HotelRoom();
    }

    @Test
    void assignGuest() {
        room.assignGuest("Dessault", "Systems");
        assertEquals("Dessault Systems", room.getHistory().get(0));
        assertTrue(room.isOccupied());
    }

    @Test
    void assignGuestEdge() {
        room.assignGuest("Dessault", "Systems");
        //Invalid assignment does not fall through as room is already occupied by first guest.
        room.assignGuest("Invali","Assignment");
        assertEquals("Dessault Systems", room.getHistory().get(0));
        assertTrue(room.isOccupied());
    }

    @Test
    void checkOut() {
        room.assignGuest("Dessault", "Systems");
        room.checkOut();
        assertFalse(room.isOccupied());
    }

    @Test
    void getHistory() {
        room.assignGuest("Dessault", "Systems");
        room.checkOut();
        assertEquals("Dessault Systems", room.getHistory().get(0));
    }

    @Test
    void getCurrentGuestName() {
        room.assignGuest("Dessault", "            Systems");
        assertEquals("Dessault Systems", room.getHistory().get(0));
    }

    @Test
    void isOccupied() {
        assertFalse(room.isOccupied());
    }
}
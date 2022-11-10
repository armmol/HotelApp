package contract;

import model.HotelRoom;

import javax.swing.*;

public interface ServiceControllerContract {
    void initForm();

    void initServiceController();

    void checkOutAction(JButton button, HotelRoom room);

    void checkGuestHistoryAction(JButton button, HotelRoom room);

    void checkHotelSummaryAction(JButton button);

    void checkRoomSummaryAction(JButton button, HotelRoom room);

    void addGuestAction(JTextField guestName, JTextField guestSurname, JButton button);

    void reviewRoomAction(JButton button, HotelRoom room);

    void reviewAllRoomsAction(JButton button);

    String buildRoomReview(HotelRoom room);

    String buildGuestList(HotelRoom room);

    String buildRoomSummary(HotelRoom room);

}

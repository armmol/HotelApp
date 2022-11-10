package contract;

import model.HotelRoom;

import javax.swing.*;

/**
 * ServiceController Interface Contract
 */
public interface ServiceControllerContract {
    /**
     * Initializes the view or swing GUI form of the application.
     */
    void initForm();

    /**
     * Initializes the ServiceController object of the application and assigns functions to the button click listeners.
     */
    void initServiceController();

    /**
     * Function to check guest out of room when Check Out button is clicked.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    void checkOutAction(int roomNumber);

    /**
     * Function to check history of guests who have previously occupied the room.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    void checkGuestHistoryAction(int roomNumber);

    /**
     * Function to print Summary of entire hotel which includes guest lists and activities of each room in the Scrollable text area.
     */
    void checkHotelSummaryAction();

    /**
     * Function to print summary of guests and activity of the room in the scrollable text area.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    void checkRoomSummaryAction(int roomNumber);

    /**
     * Function to assign guest to a room.
     *
     * @param guestName    First Name of guest.
     * @param guestSurname Surname of guest.
     */
    void addGuestAction(JTextField guestName, JTextField guestSurname);

    /**
     * Function to review status and current guest occupant if any of the room.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    void reviewRoomAction(int roomNumber);

    /**
     * Function to print the review of Room Occupancy for entire hotel in the scrollable text area.
     */
    void reviewAllRoomsAction();

    /**
     * Function to add a room to the hotel.
     */
    void addRoom();

    /**
     * Function to generate review of a room i.e. status and current guest occupant if any of the room.
     *
     * @param room Room of the hotel for which the review is being generated.
     * @return String value of the review generated.
     */
    String buildRoomReview(HotelRoom room);

    /**
     * Function to generate guest list of a room.
     *
     * @param room Room of the hotel for which the review is being generated.
     * @return String value of the guest list generated.
     */
    String buildGuestList(HotelRoom room);

    /**
     * Function to generate summary of a room i.e. status and current guest occupant if any and activity of the room.
     *
     * @param room Room of the hotel for which the review is being generated.
     * @return String value of the room summary generated.
     */
    String buildRoomSummary(HotelRoom room);

}

package controller;

import contract.ServiceControllerContract;
import form.HotelForm;
import model.HotelRoom;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

import static utility.ConstantStings.*;


/**
 * Service Controller class that manipulates the model and updates the view.
 */
public class ServiceController implements ServiceControllerContract {

    private final HotelForm form;
    private final List<HotelRoom> rooms;

    public ServiceController(HotelForm form, List<HotelRoom> rooms) {
        this.form = form;
        this.rooms = rooms;
    }

    /**
     * Initializes the view or swing GUI form of the application.
     */
    @Override
    public void initForm() {
        form.setContentPane(form.getJFormHotel());
        form.setBounds(100, 200, 600, 450);
        form.setVisible(true);
        form.getSpinnerRoomNumber().setModel(new SpinnerNumberModel(1, 1, rooms.size(), 1));
        ((JSpinner.DefaultEditor) form.getSpinnerRoomNumber().getEditor()).getTextField().setEditable(false);
        form.getLabelNumberOfRooms().setText("" + rooms.size());
    }

    /**
     * Initializes the ServiceController object of the application and assigns functions to the button click listeners.
     */
    @Override
    public void initServiceController() {
        form.getBtnRegisterGuest().addActionListener(e -> addGuestAction(form.getTxtGuestName(), form.getTxtGuestSurname()));
        form.getBtnSummary().addActionListener(e -> checkHotelSummaryAction());
        form.getBtnRoomOccupancyReview().addActionListener(e -> reviewAllRoomsAction());
        form.getBtnAddRoom().addActionListener(e -> addRoom());
        form.getBtnRoomCheckOut().addActionListener(e -> checkOutAction((Integer) form.getSpinnerRoomNumber().getValue() - 1));
        form.getBtnRoomGuestHistory().addActionListener(e -> checkGuestHistoryAction((Integer) form.getSpinnerRoomNumber().getValue() - 1));
        form.getBtnRoomHistory().addActionListener(e -> checkRoomSummaryAction((Integer) form.getSpinnerRoomNumber().getValue() - 1));
        form.getBtnRoomReview().addActionListener(e -> reviewRoomAction((Integer) form.getSpinnerRoomNumber().getValue() - 1));
    }

    /**
     * Function to check guest out of room when Check Out button is clicked.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    @Override
    public void checkOutAction(int roomNumber) {
        if (rooms.get(roomNumber).isOccupied()) {
            form.getTxtAreaPrinting().setText(ROOM + (roomNumber + 1) + ":" + rooms.get(roomNumber).getCurrentGuestName() + CHECKED_OUT);
            rooms.get(roomNumber).checkOut();
        } else {
            form.getTxtAreaPrinting().setText(ROOM + (roomNumber + 1) + NOT_CHECKED_OUT);
        }
    }

    /**
     * Function to check history of guests who have previously occupied the room.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    @Override
    public void checkGuestHistoryAction(int roomNumber) {
        if (rooms.get(roomNumber).getHistory().isEmpty()) {
            form.getTxtAreaPrinting().setText(NO_GUESTS_TO_SHOW + ROOM + (roomNumber + 1) + NOT_OCCUPIED);
        } else {
            form.getTxtAreaPrinting().setText(GUEST_LIST + buildGuestList(rooms.get(roomNumber)));
        }
    }

    /**
     * Function to print Summary of entire hotel which includes guest lists and activities of each room in the Scrollable text area.
     */
    @Override
    public void checkHotelSummaryAction() {
        StringBuilder builder = new StringBuilder();
        builder.append(HOTEL_SUMMARY);
        int i = 0;
        for (HotelRoom room : rooms) {
            if (room.getHistory().isEmpty()) {
                builder.append(ROOM).append(++i).append(ROOM_SUMMARY).append(buildRoomSummary(room))
                        .append(GUEST_LIST + NO_GUESTS_TO_SHOW);
            } else {
                builder.append(ROOM).append(++i).append(ROOM_SUMMARY).append(buildRoomSummary(room))
                        .append(GUEST_LIST).append(buildGuestList(room));
            }
        }
        form.getTxtAreaPrinting().setText(builder.toString());
    }

    /**
     * Function to print summary of guests and activity of the room in the scrollable text area.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    @Override
    public void checkRoomSummaryAction(int roomNumber) {
        form.getTxtAreaPrinting().setText(ROOM + (roomNumber + 1) + ROOM_SUMMARY + buildRoomSummary(rooms.get(roomNumber)));
    }

    /**
     * Function to assign guest to a room.
     *
     * @param guestName    First Name of guest.
     * @param guestSurname Surname of guest.
     */
    @Override
    public void addGuestAction(JTextField guestName, JTextField guestSurname) {
        int counter = 0;
        if (guestName.getText().trim().equals("") || guestSurname.getText().trim().equals("")) {
            form.getTxtAreaPrinting().setText(EMPTY_NAME_SURNAME);
        } else {
            for (HotelRoom room : rooms) {
                if (!room.isOccupied()) {
                    room.assignGuest(guestName.getText().trim(), guestSurname.getText().trim());
                    form.getTxtAreaPrinting().setText(guestName.getText().trim() + " " + guestSurname.getText().trim()
                            + WAS_ASSIGNED_TO + ++counter);
                    guestName.setText("");
                    guestSurname.setText("");
                    break;
                } else {
                    if (++counter == 5) {
                        form.getTxtAreaPrinting().setText(ALL_ROOMS_OCCUPIED);
                    }
                }
            }
        }
    }

    /**
     * Function to review status and current guest occupant if any of the room.
     *
     * @param roomNumber Room number of the room action is being performed for.
     */
    @Override
    public void reviewRoomAction(int roomNumber) {
        form.getTxtAreaPrinting().setText(ROOM + (roomNumber + 1) + buildRoomReview(rooms.get(roomNumber)));
    }

    /**
     * Function to print the review of Room Occupancy for entire hotel in the scrollable text area.
     */
    @Override
    public void reviewAllRoomsAction() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (HotelRoom room : rooms) {
            builder.append(ROOM).append(++i).append(buildRoomReview(room));
        }
        form.getTxtAreaPrinting().setText(builder.toString());
    }

    /**
     * Function to add a room to the hotel.
     */
    @Override
    public void addRoom() {
        rooms.add(new HotelRoom());
        form.getSpinnerRoomNumber().setModel(new SpinnerNumberModel(1, 1, rooms.size(), 1));
        form.getLabelNumberOfRooms().setText("" + rooms.size());
    }

    /**
     * Function to generate review of a room i.e. status and current guest occupant if any of the room.
     *
     * @param room Room of the hotel for which the review is being generated.
     * @return String value of the review generated.
     */
    @Override
    public String buildGuestList(@NotNull HotelRoom room) {
        StringBuilder builder = new StringBuilder();
        for (String guest : room.getHistory()) {
            builder.append(guest).append("\n");
        }
        return builder.toString();
    }

    /**
     * Function to generate guest list of a room.
     *
     * @param room Room of the hotel for which the review is being generated.
     * @return String value of the guest list generated.
     */
    @Override
    public String buildRoomReview(@NotNull HotelRoom room) {
        if (room.isOccupied()) {
            return OCCUPIED + GUEST + room.getCurrentGuestName();
        } else {
            return AVAILABLE;
        }
    }

    /**
     * Function to generate summary of a room i.e. status and current guest occupant if any and activity of the room.
     *
     * @param room Room of the hotel for which the review is being generated.
     * @return String value of the room summary generated.
     */
    @Override
    public String buildRoomSummary(@NotNull HotelRoom room) {
        if (room.getHistory().isEmpty()) {
            return NO_ACTIVITY;
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < room.getHistory().size(); i++) {
                if (i == room.getHistory().size() - 1 && room.isOccupied()) {
                    builder.append(room.getHistory().get(i)).append(CHECKED_IN).append(room.getHistory().get(i)).append(IS_OCCUPYING);
                } else {
                    builder.append(room.getHistory().get(i)).append(CHECKED_IN).append(room.getHistory().get(i)).append(CHECKED_OUT);
                }
            }
            return builder.toString();
        }
    }
}

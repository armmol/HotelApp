package controller;

import contract.ServiceControllerContract;
import form.HotelForm;
import model.HotelRoom;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

public class ServiceController implements ServiceControllerContract {

    private static final String ROOM_SUMMARY = "Room Summary :\n";
    private static final String ROOM = "Room ";
    private static final String GUEST_LIST = "Guest List :\n";
    private final HotelForm form;
    private final List<HotelRoom> rooms;

    public ServiceController(HotelForm form, List<HotelRoom> rooms) {
        this.form = form;
        this.rooms = rooms;
    }

    public void initForm() {
        form.setContentPane(form.getJFormHotel());
        form.setBounds(100, 200, 830, 400);
        form.setVisible(true);
//        form.setResizable(false);
    }

    public void initServiceController() {
        form.getBtnRegisterGuest().addActionListener(e -> addGuestAction(form.getTxtGuestName(), form.getTxtGuestSurname(), form.getBtnRegisterGuest()));
        form.getBtnRoom1CheckOut().addActionListener(e -> checkOutAction(form.getBtnRoom1CheckOut(), rooms.get(0)));
        form.getBtnRoom2CheckOut().addActionListener(e -> checkOutAction(form.getBtnRoom2CheckOut(), rooms.get(1)));
        form.getBtnRoom3CheckOut().addActionListener(e -> checkOutAction(form.getBtnRoom3CheckOut(), rooms.get(2)));
        form.getBtnRoom4CheckOut().addActionListener(e -> checkOutAction(form.getBtnRoom4CheckOut(), rooms.get(3)));
        form.getBtnRoom5CheckOut().addActionListener(e -> checkOutAction(form.getBtnRoom5CheckOut(), rooms.get(4)));
        form.getBtnRoom1GuestHistory().addActionListener(e -> checkGuestHistoryAction(form.getBtnRoom1GuestHistory(), rooms.get(0)));
        form.getBtnRoom2GuestHistory().addActionListener(e -> checkGuestHistoryAction(form.getBtnRoom2GuestHistory(), rooms.get(1)));
        form.getBtnRoom3GuestHistory().addActionListener(e -> checkGuestHistoryAction(form.getBtnRoom3GuestHistory(), rooms.get(2)));
        form.getBtnRoom4GuestHistory().addActionListener(e -> checkGuestHistoryAction(form.getBtnRoom4GuestHistory(), rooms.get(3)));
        form.getBtnRoom5GuestHistory().addActionListener(e -> checkGuestHistoryAction(form.getBtnRoom5GuestHistory(), rooms.get(4)));
        form.getBtnRoom1History().addActionListener(e -> checkRoomSummaryAction(form.getBtnRoom1History(), rooms.get(0)));
        form.getBtnRoom2History().addActionListener(e -> checkRoomSummaryAction(form.getBtnRoom2History(), rooms.get(1)));
        form.getBtnRoom3History().addActionListener(e -> checkRoomSummaryAction(form.getBtnRoom3History(), rooms.get(2)));
        form.getBtnRoom4History().addActionListener(e -> checkRoomSummaryAction(form.getBtnRoom4History(), rooms.get(3)));
        form.getBtnRoom5History().addActionListener(e -> checkRoomSummaryAction(form.getBtnRoom5History(), rooms.get(4)));
        form.getBtnRoom1Review().addActionListener(e -> reviewRoomAction(form.getBtnRoom1Review(), rooms.get(0)));
        form.getBtnRoom2Review().addActionListener(e -> reviewRoomAction(form.getBtnRoom2Review(), rooms.get(1)));
        form.getBtnRoom3Review().addActionListener(e -> reviewRoomAction(form.getBtnRoom3Review(), rooms.get(2)));
        form.getBtnRoom4Review().addActionListener(e -> reviewRoomAction(form.getBtnRoom4Review(), rooms.get(3)));
        form.getBtnRoom5Review().addActionListener(e -> reviewRoomAction(form.getBtnRoom5Review(), rooms.get(4)));
        form.getBtnSummary().addActionListener(e -> checkHotelSummaryAction(form.getBtnSummary()));
        form.getBtnRoomOccupancyReview().addActionListener(e -> reviewAllRoomsAction(form.getBtnRoomOccupancyReview()));
    }

    @Override
    public void checkOutAction(JButton button, @NotNull HotelRoom room) {
        if (room.isOccupied()) {
            JOptionPane.showMessageDialog(button, room.getCurrentGuestName() + " " + room.getCurrentGuestSurname() + " was checked out of room.");
            room.checkOut();
        } else {
            JOptionPane.showMessageDialog(button, "Room is already empty. No guest to check out.");
        }
    }

    @Override
    public void checkGuestHistoryAction(JButton button, @NotNull HotelRoom room) {
        if (room.getHistory().isEmpty()) {
            JOptionPane.showMessageDialog(button, "No guests to show. Room has not been occupied.");
        } else {
            JOptionPane.showMessageDialog(button, GUEST_LIST + buildGuestList(room));
        }
    }

    @Override
    public void checkHotelSummaryAction(JButton button) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (HotelRoom room : rooms) {
            if (room.getHistory().isEmpty()) {
                builder.append(ROOM).append(++i).append("\n").append(ROOM_SUMMARY).append(buildRoomSummary(room))
                        .append(GUEST_LIST + " No Guests to show.");
            } else {
                builder.append(ROOM).append(++i).append("\n").append(ROOM_SUMMARY).append(buildRoomSummary(room))
                        .append(GUEST_LIST).append(buildGuestList(room));
            }
        }
        JOptionPane.showMessageDialog(button, builder);
    }

    @Override
    public void checkRoomSummaryAction(JButton button, @NotNull HotelRoom room) {
        JOptionPane.showMessageDialog(button, ROOM_SUMMARY + buildRoomSummary(room));
    }

    @Override
    public void addGuestAction(JTextField guestName, JTextField guestSurname, JButton button) {
        int counter = 0;
        if (guestName.getText().trim().equals("") || guestSurname.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(button, "Name or Surname of guest cannot be empty");
        } else {
            for (HotelRoom room : rooms) {
                if (!room.isOccupied()) {
                    room.assignGuest(guestName.getText().trim(), guestSurname.getText().trim());
                    JOptionPane.showMessageDialog(button, guestName.getText().trim() + " " + guestSurname.getText().trim()
                            + " was assigned to room " + ++counter);
                    guestName.setText("");
                    guestSurname.setText("");
                    break;
                } else {
                    if (++counter == 5) {
                        JOptionPane.showMessageDialog(button, "Unfortunately all rooms are occupied at this moment. Please try again later.");
                    }
                }
            }
        }
    }

    @Override
    public void reviewRoomAction(JButton button, @NotNull HotelRoom room) {
        JOptionPane.showMessageDialog(button, buildRoomReview(room));
    }

    @Override
    public void reviewAllRoomsAction(JButton button) {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (HotelRoom room : rooms) {
            builder.append(ROOM).append(++i).append("\n").append(buildRoomReview(room));
        }
        JOptionPane.showMessageDialog(button, builder);
    }

    @Override
    public String buildGuestList(@NotNull HotelRoom room) {
        StringBuilder builder = new StringBuilder();
        for (String guest : room.getHistory()) {
            builder.append(guest).append("\n");
        }
        return builder.toString();
    }

    @Override
    public String buildRoomReview(@NotNull HotelRoom room) {
        if (room.isOccupied()) {
            return "Room is occupied.\n" +
                    "Guest :" + room.getCurrentGuestName() + " " + room.getCurrentGuestSurname() + "\n";
        } else {
            return "Room is available.\n";
        }
    }

    @Override
    public String buildRoomSummary(@NotNull HotelRoom room) {
        if (room.getHistory().isEmpty()) {
            return "No activity to show. Room has not been occupied yet.\n";
        } else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < room.getHistory().size(); i++) {
                if (i == room.getHistory().size() - 1 && room.isOccupied()) {
                    builder.append(room.getHistory().get(i)).append(" is occupying the room\n");
                } else {
                    builder.append(room.getHistory().get(i)).append(" checked in\n");
                    builder.append(room.getHistory().get(i)).append(" checked out\n");
                }
            }
            return builder.toString();
        }
    }
}

package form;

import javax.swing.*;

/**
 * Java Swing form for the application.
 */
public class HotelForm extends JFrame {
    private JButton btnRoomCheckOut;
    private JButton btnRegisterGuest;
    private JButton btnRoomGuestHistory;
    private JButton btnSummary;
    private JPanel jFormHotel;
    private JTextField txtGuestName;
    private JTextField txtGuestSurname;
    private JButton btnRoomHistory;
    private JButton btnRoomOccupancyReview;
    private JSpinner spinnerRoomNumber;
    private JButton btnRoomReview;
    private JTextArea txtAreaPrinting;
    private JLabel labelNumberOfRooms;
    private JButton btnAddRoom;

    //Default Constructor
    public HotelForm() {
        //Do nothing because the actual HotelForm.form to define the layout and set constraints
    }

    public JButton getBtnRoomCheckOut() {
        return btnRoomCheckOut;
    }

    public JButton getBtnRegisterGuest() {
        return btnRegisterGuest;
    }

    public JButton getBtnRoomGuestHistory() {
        return btnRoomGuestHistory;
    }

    public JButton getBtnSummary() {
        return btnSummary;
    }

    public JPanel getJFormHotel() {
        return jFormHotel;
    }

    public JTextField getTxtGuestName() {
        return txtGuestName;
    }

    public JTextField getTxtGuestSurname() {
        return txtGuestSurname;
    }

    public JButton getBtnRoomHistory() {
        return btnRoomHistory;
    }

    public JButton getBtnRoomOccupancyReview() {
        return btnRoomOccupancyReview;
    }

    public JButton getBtnRoomReview() {
        return btnRoomReview;
    }

    public JSpinner getSpinnerRoomNumber() {
        return spinnerRoomNumber;
    }

    public JTextArea getTxtAreaPrinting() {
        return txtAreaPrinting;
    }

    public JLabel getLabelNumberOfRooms() {
        return labelNumberOfRooms;
    }

    public JButton getBtnAddRoom() {
        return btnAddRoom;
    }
}

package driver;

import controller.ServiceController;
import form.HotelForm;
import model.HotelRoom;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Driver class to run the program.
 */
public class HotelApp {

    /**
     * Main method to run code. Defines a Controller object with a hotel essentially
     * having 5 rooms based on the requirement of the task.
     *
     * @param args java command line arguments
     */
    public static void main(String[] args) {
        ServiceController controller = new ServiceController(new HotelForm(),
                new ArrayList<>(Arrays.asList(new HotelRoom(), new HotelRoom(), new HotelRoom(), new HotelRoom(), new HotelRoom())));
        controller.initForm();
        controller.initServiceController();
    }
}

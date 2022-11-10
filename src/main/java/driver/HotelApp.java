package driver;

import controller.ServiceController;
import form.HotelForm;
import model.HotelRoom;

import java.util.ArrayList;
import java.util.Arrays;

public class HotelApp {

    public static void main(String[] args) {
        ServiceController controller = new ServiceController(new HotelForm(),
                new ArrayList<>(Arrays.asList(new HotelRoom(), new HotelRoom(), new HotelRoom(), new HotelRoom(), new HotelRoom())));
        controller.initForm();
        controller.initServiceController();
    }
}

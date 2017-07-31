package demo.spring;

import demo.dto.Car;
import demo.dto.RequestWrapper;
import demo.dto.Truck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
public class VehicleController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Car> get() {
        Car car = new Car();
        car.setColor("Blue");
        car.setMiles(100);
        car.setVin("1234");

        writeToFile(car);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Car> update(@RequestBody Car car) {
        car.setMiles(car.getMiles() + 100);
        writeToFile(car);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @RequestMapping(value = "/cars", method = RequestMethod.POST)
    public ResponseEntity<List<Car>> update(@RequestBody List<Car> cars) {
        cars.forEach(c -> c.setMiles(c.getMiles() + 100));
        writeToFile(cars);

        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @RequestMapping(value = "/truck", method = RequestMethod.POST)
    public ResponseEntity<Truck> update(@RequestBody Truck truck) {
        truck.setMiles(truck.getMiles() + 222);
        truck.setColor("Gray but Red");

        writeToFile(truck);

        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    @RequestMapping(value = "/carsandtrucks", method = RequestMethod.POST)
    public ResponseEntity<RequestWrapper> updateWithMultipleObjects(@RequestBody RequestWrapper wrapper) {
        wrapper.getCars().forEach(c -> c.setMiles(c.getMiles() + 100));
        wrapper.getTruck().setMiles(455);
        writeToFile(wrapper);

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    private <T> void writeToFile(T object) {
        try (OutputStream file = new FileOutputStream("test.txt");
             OutputStream buffer = new BufferedOutputStream(file);
             ObjectOutput output = new ObjectOutputStream(buffer);) {
            output.writeObject(object.toString());
            output.writeChars("\n");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

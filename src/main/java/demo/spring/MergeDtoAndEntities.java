package demo.spring;

import demo.dto.*;
import demo.db.repos.CarRepository;
import demo.db.repos.TruckRepository;
import demo.dto.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MergeDtoAndEntities {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TruckRepository truckRepository;


    public ResponseEntity<Car> update(Car car) {
        car.setMiles(car.getMiles() + 100);
        demo.db.entities.Car car1 = dtoToEntity(car);
        carRepository.save(car1);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    public ResponseEntity<RequestWrapper> updateWithMultipleObjects(RequestWrapper wrapper) {
        wrapper.getCars().forEach(c -> c.setMiles(c.getMiles() + 100));
        wrapper.getTruck().setMiles(455);

        demo.db.entities.Truck truck = dtoToEntityTruck(wrapper.getTruck());

        wrapper.getCars().stream().map(this::dtoToEntity).forEach(e -> carRepository.save(e));
        truckRepository.save(truck);

        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    public ResponseEntity<Truck> update(Truck truck) {
        truck.setMiles(truck.getMiles() + 222);
        truck.setColor("Gray but Red");

        dtoToEntityTruck(truck);

        return new ResponseEntity<>(truck, HttpStatus.OK);
    }

    private demo.db.entities.Car dtoToEntity(Car car) {
        return new demo.db.entities.Car(car.getVin(), car.getColor(), car.getMiles());
    }

    private demo.db.entities.Truck dtoToEntityTruck(Truck truck) {
        return new demo.db.entities.Truck(truck.getVin(), truck.getColor(), truck.getMiles());
    }
}

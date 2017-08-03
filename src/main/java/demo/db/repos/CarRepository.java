package demo.db.repos;



import java.util.List;

import demo.db.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
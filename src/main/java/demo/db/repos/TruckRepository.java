package demo.db.repos;

import org.springframework.data.repository.CrudRepository;

import demo.db.entities.Truck;

public interface TruckRepository extends CrudRepository<Truck, Long>{}

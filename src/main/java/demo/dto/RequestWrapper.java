package demo.dto;

import java.io.Serializable;
import java.util.List;

public class RequestWrapper implements Serializable {

    private List<Car> cars;
    private Truck truck;

    public RequestWrapper() {
    }

    public RequestWrapper(List<Car> cars, Truck truck) {
        this.cars = cars;
        this.truck = truck;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestWrapper that = (RequestWrapper) o;

        if (cars != null ? !cars.equals(that.cars) : that.cars != null) return false;
        return truck != null ? truck.equals(that.truck) : that.truck == null;
    }

    @Override
    public int hashCode() {
        int result = cars != null ? cars.hashCode() : 0;
        result = 31 * result + (truck != null ? truck.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestWrapper [cars=" + cars + ", truck=" + truck + "]";
    }
}
package demo.dto;

import java.io.Serializable;

public class Car implements Serializable {

    private String vin;
    private String color;
    private Integer miles;

    public Car() {
    }

    public Car(String vin, String color, Integer miles) {
        this.vin = vin;
        this.color = color;
        this.miles = miles;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getMiles() {
        return miles;
    }

    public void setMiles(Integer miles) {
        this.miles = miles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((miles == null) ? 0 : miles.hashCode());
        result = prime * result + ((vin == null) ? 0 : vin.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Car other = (Car) obj;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (miles == null) {
            if (other.miles != null)
                return false;
        } else if (!miles.equals(other.miles))
            return false;
        if (vin == null) {
            if (other.vin != null)
                return false;
        } else if (!vin.equals(other.vin))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Car [vin=" + vin + ", color=" + color + ", miles=" + miles + "]";
    }
}

package demo.db.entities;

import javax.persistence.*;

@Entity
@Table(name = "Truck")
public class Truck {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vin")
    private String vin;

    @Column(name = "color")
    private String color;

    @Column(name = "miles")
    private Integer miles;

    public Truck(String vin, String color, Integer miles) {
        this.vin = vin;
        this.color = color;
        this.miles = miles;
    }

    public Truck() {
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", color='" + color + '\'' +
                ", miles=" + miles +
                '}';
    }
}

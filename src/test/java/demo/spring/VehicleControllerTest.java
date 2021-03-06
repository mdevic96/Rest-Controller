package demo.spring;

import demo.dto.*;
import demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTest {

	@LocalServerPort
	private int port;

	private final TestRestTemplate restTemplate = new TestRestTemplate();

	private final HttpHeaders headers = new HttpHeaders();

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

	@Test
	public void testGetForCar() {
		HttpEntity<String> entity = new HttpEntity<>(null, headers);

		ResponseEntity<Car> response = restTemplate.exchange(createURLWithPort("/"), HttpMethod.GET, entity, Car.class);

		assertThat(response.getBody().getVin()).as("check if Car's vin is").isEqualTo("555");
		assertThat(response.getBody().getColor()).as("check if Car's color is").isEqualTo("Red");
		assertThat(response.getBody().getMiles()).as("check if Car's miles is").isEqualTo(110);
	}

	@Test
	public void testPostForMultipleCars() {
		Car[] cars = { new Car("122", "Red", 111), new Car("123", "Orange", 222), new Car("124", "Blue", 333) };

		HttpEntity<Car[]> entity = new HttpEntity<>(cars, headers);
		ResponseEntity<Car[]> response = restTemplate.exchange(createURLWithPort("/cars"), HttpMethod.POST, entity,
				Car[].class);

		for (Car car : cars) {
			car.setMiles(car.getMiles() + 100);
		}

		Car[] carsFromResponse = response.getBody();
		assertThat(cars).hasSize(3);
		assertThat(cars).isEqualTo(carsFromResponse);
	}

	@Test
	public void testPostForNoCars() {
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<List<Car>> entity = new HttpEntity<>(null, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(createURLWithPort("/cars"), HttpMethod.POST,
				entity, String.class);

		assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testPostForOneTruck() {
		Truck truck = new Truck("111", "Blue", 244);

		HttpEntity<Truck> entity = new HttpEntity<>(truck, headers);
		ResponseEntity<Truck> response = restTemplate.exchange(createURLWithPort("/truck"), HttpMethod.POST, entity,
				Truck.class);

		truck.setMiles(truck.getMiles() + 222);
		truck.setColor("Gray but Red");

		assertThat(response.getBody()).isEqualTo(truck);
	}

	@Test
	public void testPostForNoTruck() {

		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Truck> entity = new HttpEntity<>(null, headers);
		ResponseEntity<Truck> response = restTemplate.exchange(createURLWithPort("/truck"), HttpMethod.POST, entity,
				Truck.class);

		assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getStatusCode()).as("check if responses has errors")
				.isEqualByComparingTo(HttpStatus.BAD_REQUEST);
	}

	@Test
	public void testPostForCarsAndTruck() {
		Truck truck = new Truck("111", "Black", 200);
		List<Car> cars = new ArrayList<>();

		cars.add(new Car("122", "Red", 111));
		cars.add(new Car("123", "Orange", 222));
		cars.add(new Car("124", "Blue", 333));

		RequestWrapper requestWrapper = new RequestWrapper(cars, truck);

		HttpEntity<RequestWrapper> entity = new HttpEntity<>(requestWrapper, headers);
		ResponseEntity<RequestWrapper> response = restTemplate.exchange(createURLWithPort("/carsandtrucks"),
				HttpMethod.POST, entity, RequestWrapper.class);

		requestWrapper.getCars().forEach(c -> c.setMiles(c.getMiles() + 100));
		requestWrapper.getTruck().setMiles(455);
	}

	@Test
	public void testPostForNoCarsAndTruck() {
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<RequestWrapper> entity = new HttpEntity<>(null, headers);
		ResponseEntity<RequestWrapper> responseEntity = restTemplate.exchange(createURLWithPort("/cars"),
				HttpMethod.POST, entity, RequestWrapper.class);

		assertThat(responseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
		assertThat(responseEntity.getStatusCode()).as("check if responses has errors")
				.isEqualByComparingTo(HttpStatus.BAD_REQUEST);
	}
}
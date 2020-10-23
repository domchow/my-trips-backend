import com.my.trips.model.Trip;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static helpers.TripTestHelper.getTestTrip;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
public class EntityTest {

    private final String localHost = "http://localhost:8080";
    private final String tripPath = "/trips";
    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void shouldReturnNonNullTrips() {
        ResponseEntity<Trip[]> response = getTrips();
        List<Trip> body = List.of(requireNonNull(response.getBody()));

        assertThat(body.size(), greaterThan(0));
        assertThat(body.get(0).getCity(), notNullValue());
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test()
    public void shouldCreateNewTrip() {
        Trip trip = getTestTrip(1);
        ResponseEntity<Trip> response = createTrip(trip);
        Trip body = response.getBody();
        assertThat("City Name returned for POST is not correct",
                body.getCity(),
                equalTo(trip.getCity()));

        assertThat("Response code is not correct",
                response.getStatusCode(),
                equalTo(OK));

    }

    @Test()
    public void shouldCreateUpdateDeleteTrip() {
        Trip trip = getTestTrip(1);
        ResponseEntity<Trip> createdEntity = createTrip(trip);
        long id = requireNonNull(createdEntity.getBody()).getId();
        ResponseEntity<Trip> returnedTrip = getTrips(id);
        deleteTrip(id);
        ResponseEntity<Trip[]> allTrips = getTrips();

        assertThat(createdEntity.getStatusCode(), equalTo(OK));
        assertThat(returnedTrip.getStatusCode(), equalTo(OK));
        assertThat(allTrips.getStatusCode(), equalTo(OK));

        assertThat("Trip returned for POST is not correct",
                requireNonNull(returnedTrip.getBody()).getCity(),
                equalTo(trip.getCity()));
        assertThat("Trip is returned despite was deleted",
                List.of(requireNonNull(allTrips.getBody())), not(hasItem(createdEntity)));
    }

    private ResponseEntity<Trip> createTrip(Trip trip) {
        return restTemplate.postForEntity(localHost.concat(tripPath), trip, Trip.class);
    }

    private ResponseEntity<Trip> getTrips(long id) {
        return restTemplate.getForEntity(localHost.concat(tripPath).concat(format("/%s", id)), Trip.class);
    }

    private ResponseEntity<Trip[]> getTrips() {
        return requireNonNull(restTemplate.getForEntity(localHost.concat(tripPath), Trip[].class));
    }

    private void deleteTrip(long tripId) {
        restTemplate.delete(localHost.concat(tripPath).concat(format("/%s", tripId)));
    }

}

package helpers;

import com.my.trips.model.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class TripTestHelper {

    public static List<Trip> getTrips() {
        return IntStream
                .rangeClosed(1, 5)
                .mapToObj(TripTestHelper::getTestTrip)
                .collect(toList());
    }

    public static Trip getTestTrip(int index) {
        return Trip.of(null, format("TestCity_%s", index), LocalDate.now().plusDays(index));
    }
}

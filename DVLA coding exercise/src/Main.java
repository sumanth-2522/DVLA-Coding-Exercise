import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

    public class Main {

        static class VehicleRegistration {

            private final String registrationNumber;
            private final int year;
            private final String vehicleId;

            public VehicleRegistration(
                    String registrationNumber,
                    int year,
                    String vehicleId) {
                this.registrationNumber = registrationNumber;
                this.year = year;
                this.vehicleId = vehicleId;
            }

            public String getRegistrationNumber() {
                return registrationNumber;
            }

            public int getYear() {
                return year;
            }

            public Optional<String> getVehicleId() {
                return Optional.ofNullable(vehicleId);
            }
        }

        static class RegistrationRegistry {

            private final List<VehicleRegistration> registrations =
                    new ArrayList<>();

            public RegistrationRegistry() {
                registrations.add(
                        new VehicleRegistration("AB01 CDE", 2001, "12345"));

                registrations.add(
                        new VehicleRegistration("FG02 HJK", 2002, "67890"));

                registrations.add(
                        new VehicleRegistration("L33T H4X0R", 2022, null));
            }

            public int getTotalRegistrations() {
                return registrations.size();
            }

            public Optional<String> getVehicleId(String registrationNumber) {

                String searchNumber = registrationNumber
                        .trim()
                        .toUpperCase(Locale.ROOT);

                for (VehicleRegistration registration : registrations) {

                    if (registration.getRegistrationNumber()
                            .toUpperCase(Locale.ROOT)
                            .equals(searchNumber)) {
                        return registration.getVehicleId();
                    }
                }

                return Optional.empty();
            }
        }

        public static void main(String[] args) {

            RegistrationRegistry registry = new RegistrationRegistry();

            System.out.println(
                    "Total registrations: " + registry.getTotalRegistrations());

            printVehicleId(registry, "AB01 CDE");
            printVehicleId(registry, "FG02 HJK");
            printVehicleId(registry, "L33T H4X0R");
            printVehicleId(registry, "UNKNOWN");
        }

        private static void printVehicleId(
                RegistrationRegistry registry,
                String registrationNumber) {

            Optional<String> vehicleId =
                    registry.getVehicleId(registrationNumber);

            if (vehicleId.isPresent()) {
                System.out.println(
                        registrationNumber + ": Vehicle ID = "
                                + vehicleId.get());
            } else {
                System.out.println(
                        registrationNumber + ": No vehicle ID found");
            }
        }
    }

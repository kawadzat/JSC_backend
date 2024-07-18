package io.getarrays.securecapita.officebearer;

import io.getarrays.securecapita.officelocations.OfficeLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeBearerRepo extends JpaRepository<OfficeBearer, Long> {
}

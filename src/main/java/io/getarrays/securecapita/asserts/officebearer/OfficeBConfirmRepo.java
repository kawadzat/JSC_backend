package io.getarrays.securecapita.asserts.officebearer;

import io.getarrays.securecapita.asserts.Confirm.ConfirmAssert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeBConfirmRepo extends JpaRepository<OfficeBConfirm, Long> {
}

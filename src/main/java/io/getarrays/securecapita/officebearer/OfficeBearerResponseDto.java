package io.getarrays.securecapita.officebearer;

import java.sql.Timestamp;

public record OfficeBearerResponseDto(Long id, String asset, String serial, String checkedByName, String checkedByEmail, Timestamp updatedDate) {
}

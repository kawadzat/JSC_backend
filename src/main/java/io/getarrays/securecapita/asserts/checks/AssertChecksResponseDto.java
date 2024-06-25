package io.getarrays.securecapita.asserts.checks;

import lombok.Data;

import java.sql.Timestamp;


public record AssertChecksResponseDto(Long id, String station, String checkedByName, String checkedByEmail, Timestamp updatedDate) {
}
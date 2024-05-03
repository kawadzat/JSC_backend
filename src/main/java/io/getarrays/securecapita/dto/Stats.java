package io.getarrays.securecapita.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * @author Junior RT
 * @version 1.0
 * @license Get Arrays, LLC (https://getarrays.io)
 * @since 5/15/2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stats {
    private long totalAsserts;
    private long totalFixedAsserts;
    private long totalCurrentAsserts;
    private ArrayList<AssetItemStat> assetsStats;
}

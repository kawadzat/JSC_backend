package io.getarrays.securecapita.dto;

import io.getarrays.securecapita.domain.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Junior RT
 * @version 1.0
 * @license Get Arrays, LLC (https://getarrays.io)
 * @since 8/28/2022
 */

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String title;
    private String bio;
    private String imageUrl;
    private boolean enabled;
    private boolean isNotLocked;
    private boolean isUsingMfa;
    private LocalDateTime createdAt;
    private String roleName;
    private String permissions;
    private String station;
    private boolean isAssigned;

    public static UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .title(user.getTitle())
                .bio(user.getBio())
                .imageUrl(user.getImageUrl())
                .enabled(user.isEnabled())
                .isNotLocked(user.isNotLocked())
                .isUsingMfa(user.isUsingMfa())
                .createdAt(user.getCreatedAt())
                .roleName(user.getRoles().stream().findAny().get().getRole().getName())
                .permissions(user.getRoles().stream().findAny().get().getRole().getPermission())
//                .station(user.getStation() == null ? null : user.getStation().getStationName())
//                .isAssigned(user.getStation()!=null)
                .build();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + email;
    }
}

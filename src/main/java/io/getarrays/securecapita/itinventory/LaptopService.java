package io.getarrays.securecapita.itinventory;

import io.getarrays.securecapita.domain.User;
import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.repository.UserRepository;
import io.getarrays.securecapita.repository.implementation.UserRepository1;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LaptopService {
    private final UserRepository<User> userRepository;

    private final UserRepository1 userRepository1;

    private final LaptopRepository laptopRepository;


    public LaptopDto createLaptop(UserDTO currentUser, LaptopDto laptopDto) {
        Laptop laptopEntity = dtoToEntity(currentUser, null, laptopDto); // Map DTO to entity
        Laptop savedLaptop = laptopRepository.save(laptopEntity);        // Save entity
        return entityToDto(savedLaptop);                                 // Map back to DTO
    }

    private Laptop dtoToEntity(UserDTO currentUser, Laptop existing, LaptopDto dto) {
        // Example mapping — implement according to your structure
        Laptop laptop = existing != null ? existing : new Laptop();
        laptop.setTitle(dto.getTitle());
        laptop.setDescription(dto.getDescription());
        laptop.setIssueDate(dto.getIssueDate());
        laptop.setReplacementDate(dto.getReplacementDate());
        laptop.setSerialNumber(dto.getSerialNumber());
        // You may fetch User entity from userRepository based on currentUser if needed
        return laptop;
    }

    private LaptopDto entityToDto(Laptop entity) {
        return LaptopDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .issueDate(entity.getIssueDate())
                .replacementDate(entity.getReplacementDate())
                .serialNumber(entity.getSerialNumber())
                .build();
    }

}

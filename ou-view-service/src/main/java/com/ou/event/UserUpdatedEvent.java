package com.ou.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserUpdatedEvent {
    private UUID userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private int serialNumber;
    private String phone;
    private String phone2;
    private String gender;
    private String maritalStatus;
    private String status;
    private LocalDate birthDate;
    private LocalDate joiningDate;
    private String picture;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

package com.ou.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserNameUpdatedEvent {

    private UUID userId;

    private String firstName;
    private String middleName;
    private String lastName;

    private LocalDateTime updatedAt;
}

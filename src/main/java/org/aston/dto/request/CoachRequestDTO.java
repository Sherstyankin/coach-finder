package org.aston.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachRequestDTO {
    private String name;
    private String sportType;
    private Long gymId;
}

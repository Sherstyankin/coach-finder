package org.aston.dto.update;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoachUpdateDTO {
    private Long id;
    private String name;
    private String sportType;
    private Long gymId;
}

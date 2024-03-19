package org.aston.dto.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymRequestDTO {
    private String name;
    private String city;
}

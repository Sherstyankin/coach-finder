package org.aston.dto.update;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GymUpdateDTO {
    private Long id;
    private String name;
    private String city;
}

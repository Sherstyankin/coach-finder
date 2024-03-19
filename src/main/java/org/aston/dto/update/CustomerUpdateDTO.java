package org.aston.dto.update;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateDTO {
    private Long id;
    private String name;
}

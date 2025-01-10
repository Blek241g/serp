package org.scalke.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class PermissionDTO extends RepresentationModel<PermissionDTO> {
    private Long id;
    private String name;
}

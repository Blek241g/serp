package org.scalke.userservice.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter @Builder
@Entity
@Table(name = "roles")
public class Role extends RepresentationModel<Role> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            unique = true
    )
    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "permission_roles",
        joinColumns = @JoinColumn(
                name = "role_id", referencedColumnName = "id"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "permission_id", referencedColumnName = "id"
        )
    )
    private Collection<Permission> permissions = new ArrayList<Permission>();

}

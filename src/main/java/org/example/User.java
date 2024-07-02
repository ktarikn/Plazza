package org.example;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "plazaUser")
@Getter @Setter
public class User {

    @Id
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}

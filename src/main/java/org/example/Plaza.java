package org.example;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Plaza {

    @Id
    private Long id;

    @OneToMany
    private List<Ofis> ofisler;
}

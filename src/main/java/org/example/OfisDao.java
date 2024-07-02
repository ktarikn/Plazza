package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class OfisDao {

    @Autowired
    private EntityManager em;

    @Transactional
    public void persist(Ofis ofis) {
        em.persist(ofis);
    }

    @Transactional
    public Ofis[] getAll() {
        return em.createQuery("from Ofis", Ofis.class).getResultList().toArray(new Ofis[0]);
    }

}

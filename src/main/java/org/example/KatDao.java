package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class KatDao {
    @Autowired
    private EntityManager em;

    @Transactional
    public void persist(Kat kat) {
        em.persist(kat);
    }
    @Transactional
    public void merge(Kat kat) {
        em.merge(kat);
    }

    @Transactional
    public Kat[] getAll() {
        return em.createQuery("from Kat", Kat.class).getResultList().toArray(new Kat[0]);
    }
}

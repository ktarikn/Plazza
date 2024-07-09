package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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

    @Transactional
    public Ofis findById(Long id) {
        return em.find(Ofis.class, id);
    }

    @Transactional
    public Ofis deleteById(Long Id) {
        Ofis ofis = em.find(Ofis.class, Id);
        em.remove(em.getReference(Ofis.class, Id));
        return ofis;
    }

    @Transactional
    public void delete(Ofis ofis) {
        em.remove(ofis);
    }

    @Transactional
    public Ofis update(Ofis ofis) {
        return em.merge(ofis);
    }

}

package com.leonardozm.app;

import com.leonardozm.app.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DeleteCascade {
    public static void main(String... string) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Clientes-PU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//		EntityGraph<Cliente> entityGraph = entityManager.createEntityGraph(Cliente.class);
//		Subgraph<Object> carros = entityGraph.addSubgraph("carros");
//		carros.addSubgraph("multas");
        Query query = entityManager.createQuery("SELECT c FROM Cliente c join fetch c.carros ca join fetch ca.multas m where c.id = :id");
        query.setParameter("id",7);
        Cliente singleResult = (Cliente) query.getSingleResult();
//		Cliente cliente = entityManager.find(Cliente.class, 6, Collections.singletonMap("javax.persistence.loadgraph", entityGraph));
        entityManager.remove(singleResult);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

    }


}

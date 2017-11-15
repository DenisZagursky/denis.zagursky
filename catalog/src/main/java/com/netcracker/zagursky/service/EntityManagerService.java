package com.netcracker.zagursky.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Dzenyaa on 08.11.2017.
 */
public class EntityManagerService {
    private EntityManagerService() {
    }

    public static EntityManager getInstance() {
        return Holder.entityManager;
    }

    private static class Holder {

        private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceCatalog");
        private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    }


}

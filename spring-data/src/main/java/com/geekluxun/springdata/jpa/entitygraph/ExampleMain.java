package com.geekluxun.springdata.jpa.entitygraph;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: luxun
 * @Create: 2018-12-09 13:07
 * @Description:
 * @Other:
 */
public class ExampleMain {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("example-unit");

    public static void main(String[] args) {
        try {
            persistEntity();
            findEntity();
        } finally {
            entityManagerFactory.close();
        }
    }

    private static void findEntity() {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityGraph graph = em.getEntityGraph("user-phones-entity-graph");
        Map<String, Object> properties = new HashMap<>();
        // 使用查询提示方式增加一个fetch 图
        properties.put("javax.persistence.fetchgraph", graph);
        User2 user = em.find(User2.class, 1, properties);
        em.close();
        printInitializeStatus(user);
    }

    private static void printInitializeStatus(User2 user) {
        PersistenceUtil pu = entityManagerFactory.getPersistenceUnitUtil();
        System.out.println("          User loaded: " + pu.isLoaded(user));
        System.out.println("     User.name loaded: " + pu.isLoaded(user, "name"));
        System.out.println("User.addresses loaded: " + pu.isLoaded(user, "addresses"));
        System.out.println("   User.phones loaded: " + pu.isLoaded(user, "phones"));
    }

    public static void persistEntity() {
        User2 user = new User2();
        user.setName("Jimi");
        user.addPhone("111-111-1111", "cell");
        user.addPhone("222-222-2222", "work");
        user.addAddress("111 Star Ave", "Sky Town", "Sunland");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }
}

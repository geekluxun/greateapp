package com.geekluxun.springdata.jpa.projection;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import java.util.Date;
import java.util.List;

/**
 * @Author: luxun
 * @Create: 2018-12-09 15:00
 * @Description:  三元组示例实现实体类到DTO（查询部分字段）
 * 可以考虑使用Projection（投影）技术
 * @Other:
 */
public class ProjectionExample {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("example-unit");

    public static void main(String[] argc) {
        demo1();
    }

    private static void demo1() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Post post = new Post();
        post.setCreatedBy("luxun");
        post.setTitle("great");
        post.setCreatedOn(new Date());
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();

        List<Tuple> postDTOs = entityManager
                .createQuery(
                        "select " +
                                "       p.id as id, " +
                                "       p.title as title " +
                                "from Post p " +
                                "where p.title = :title", Tuple.class)
                .setParameter("title", "great")
                .getResultList();

        Assert.isTrue(!postDTOs.isEmpty());
        Tuple postDTO = postDTOs.get(0);
        Assert.isTrue(postDTO.get("id").equals(1L));
    }
}

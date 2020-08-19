package com.cinema.quiz.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;


@Repository
public class UnavailableFootballPlayerRepoTruncate {



        private final EntityManager entityManager;

        @Autowired
        public UnavailableFootballPlayerRepoTruncate(EntityManager entityManager) {
            this.entityManager = entityManager;
        }

        @Transactional
        public  void delete(){
            String sql = "TRUNCATE unavailable_football_player;";
            Query nativeQuery = entityManager.createNativeQuery(sql);
            nativeQuery.executeUpdate();
        }
}

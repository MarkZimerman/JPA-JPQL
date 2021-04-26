package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserRepositoryJpql {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<String> updateUsers(List<String> usersId) {
		var notFoundIds = new ArrayList<String>();
		var newValue = "JPQL-changed";
		var query = em.createQuery("UPDATE UserEntity u SET u.lastName = :value WHERE u.id = :id");
		
		for (String id : usersId) {
			query.setParameter("value", newValue);
			query.setParameter("id", id);
			
			var rowsAffected = query.executeUpdate();

			if (rowsAffected == 0) 
				notFoundIds.add(id);
		}
		
		return notFoundIds;
	}
}

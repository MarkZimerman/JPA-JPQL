package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	UserRepository repository;
	
	@Override
	public List<String> updateUsers(List<String> usersId) {
		
		var updatedEntities = repository.findAllByIdIn(usersId)
				.map(user -> {
					user.setFirstName("JPA-changed");
					return user;
				})
				.collect(Collectors.toList());
		
		// If @Transactional method you can skip saving, it will save on method return.
		repository.saveAll(updatedEntities);
		
		var updatedIds = updatedEntities.stream().map(entity -> entity.getId()).collect(Collectors.toList());
		var notFound = usersId
				.stream()
				.filter(id -> !updatedIds.contains(id))
				.collect(Collectors.toList());
		
		return notFound;
	}

}

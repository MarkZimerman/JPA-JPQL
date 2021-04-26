package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.repositories.UserRepositoryJpql;
import com.example.demo.services.IUserService;

import java.sql.SQLException;
import java.util.ArrayList;

@SpringBootApplication
public class DariaQuestionApplication {

	public static void main(String[] args) throws SQLException {
		var context = SpringApplication.run(DariaQuestionApplication.class, args);

		var repository = context.getBean(UserRepositoryJpql.class);
		var service = context.getBean(IUserService.class);
		
		var list = new ArrayList<String>();
		list.add("280893f2-e0ca-4fdf-807d-9821ddb8102b");
		list.add("af0bcb35-a859-4a25-878b-dfa19e25143f");
		list.add("389bfd4d-9b4e-42b1-af68-fa779c09a9ef");
		list.add("4701c542-aaac-4e33-8d1a-eae078473127");
		list.add("ea49d6f5-7764-40c0-a2d1-22382c6c05d8");
		list.add("123");
		list.add("1234");
		list.add("12345");
		
		System.out.println("Using JPA:");	
		var notFoundUsers1 = service.updateUsers(list);
		System.out.println("Not found ids: " + notFoundUsers1);
		
		System.out.println("Using JPQL:");
		var notFoundUsers2 = repository.updateUsers(list);
		System.out.println("Not found ids: " + notFoundUsers2);
	}

}

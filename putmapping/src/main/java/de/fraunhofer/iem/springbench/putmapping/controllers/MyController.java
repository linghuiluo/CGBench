package de.fraunhofer.iem.springbench.putmapping.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.fraunhofer.iem.springbench.putmapping.User;
import de.fraunhofer.iem.springbench.putmapping.UsersRepository;

@RestController
public class MyController {

	private UsersRepository usersRepository = new UsersRepository();
	
	@PutMapping(value = "/user/{id}")
    public ResponseEntity<?> saveUser(@PathVariable("id") String id) {
        if (usersRepository.exists(id))
        	return ResponseEntity.ok("user exists");
        else 
        	return ResponseEntity.ok("user does not exist");
    }
}

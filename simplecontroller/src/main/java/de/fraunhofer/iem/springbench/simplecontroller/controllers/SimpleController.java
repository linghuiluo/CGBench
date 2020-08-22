package de.fraunhofer.iem.springbench.simplecontroller.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {

	@GetMapping("/")
	public @ResponseBody ResponseEntity<String> sendMessageBack(HttpServletRequest request) {
		return new ResponseEntity<String>("Response from Simple Controller. Received message: " + request.getParameter("message"), HttpStatus.OK);
	}
}

package de.fraunhofer.iem.springbench.simplerestcontroller.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {

	@RequestMapping("/")
		public String sendMessageBack(HttpServletRequest request) {
			String message = request.getParameter("message");
			return "Response from Rest Simple Controller. Received message: " + message;
		}
}

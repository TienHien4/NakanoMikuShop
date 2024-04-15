package com.practice1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.practice1.conf.Customer.CustomerDetails;
import com.practice1.dto.AdminDto;
import com.practice1.dto.LoginResponse;
import com.practice1.dto.UserDto;
import com.practice1.dto.UserResponse;
import com.practice1.dto.loginRequest;
import com.practice1.entities.Admin;
import com.practice1.entities.Role;
import com.practice1.entities.Customer;
import com.practice1.service.AdminService;
import com.practice1.service.UserService;
import com.practice1.service.iplm.JwtTokenProvider;

@RestController
@Validated
public class HomeController {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtService;
	

	@Autowired
	private UserService userService;
	
	
	@GetMapping("/signin")
	public String Login()
	{
		return "home/login";
	}
	
	@GetMapping("/register")
	public String Register(Model model)
	{

		UserDto user = new UserDto();
		model.addAttribute("userDto", user);
		return "home/register";
	}
	
	@PostMapping("/createUser")
	public String createrUser(UserDto userDto) {
		userService.createUser(userDto);
		return "home/login";
	}
	
	@PostMapping(value = "/rest/signin")
	public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Customer user) {
		String result = "";
		HttpStatus httpStatus = null;
		try {
			if (userService.checkLogin(user)) {
				result = jwtService.generateToken(user.getUsername());
				httpStatus = HttpStatus.OK;
			} else {
				result = "Wrong userId and password";
				httpStatus = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception ex) {
			result = "Server Error";
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<String>(result, httpStatus);
	}
	
	
//	@PostMapping("/createUser")
//	public String createuser(@ModelAttribute Customer user, HttpSession session) {
//
//		// System.out.println(user);
//
//		boolean f = userService.checkEmail(user.getEmail());
//
//		if (f) {
//			session.setAttribute("msg", "Email Id alreday exists");
//		}
//
//		else {
//			Customer userDtls = userService.createUser(user);
//			if (userDtls != null) {
//				session.setAttribute("msg", "Register Sucessfully");
//			} else {
//				session.setAttribute("msg", "Something wrong on server");
//			}
//		}
//
//		return "redirect:/home/register";
//	}

}


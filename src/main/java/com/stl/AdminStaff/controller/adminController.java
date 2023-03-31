package com.stl.AdminStaff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stl.AdminStaff.jwt.JwtUtils;
import com.stl.AdminStaff.model.adminModel;
import com.stl.AdminStaff.service.adminService;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("Admin")
public class adminController {
	
	@Autowired
	adminService service;
	

	@Autowired
    AuthenticationManager authmanage;
    
    @Autowired
    JwtUtils jwtUtils;

	@PostMapping("/add")
	public ResponseEntity<String> Addadmin(@RequestBody adminModel admin){
		String pass = admin.getPassword();
		admin.setPassword(new BCryptPasswordEncoder().encode(pass));
		return new ResponseEntity<String>(service.Addadmin(admin),HttpStatus.CREATED);
	}
	
	 @GetMapping("/gettoken/{token}")
     public String extractUsernamesbytoken(@PathVariable String token) {
    	 return jwtUtils.extractUsername(token);
     }
	
	//login
    @PostMapping("/authenticatetoken")
    public String authDoctor(@RequestBody adminModel admin){
        try {
            @SuppressWarnings("unused")
            
            Authentication authtoken=  authmanage.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword()));
            String token = jwtUtils.generateToken(admin.getEmail().toString());
            return token;
            
        } catch (Exception e) {
            return "Login Failed";
        }
    }
}

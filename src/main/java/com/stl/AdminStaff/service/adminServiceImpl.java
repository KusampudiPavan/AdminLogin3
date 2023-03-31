package com.stl.AdminStaff.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stl.AdminStaff.model.adminModel;
import com.stl.AdminStaff.repository.adminRepo;

@Service
public class adminServiceImpl implements adminService {

	@Autowired
	adminRepo repo;
	
	@Override
	public String Addadmin(adminModel admin) {
		
		if(!repo.existsById(admin.getEmail())) {
			repo.save(admin);
			return admin.getFirstname() + " Admin Registration is successfull!";
		}
		else {
			System.out.println("Email id already exists!!");
			return "Email id already exists!!";
		}
	}
	

	

}

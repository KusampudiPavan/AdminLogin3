package com.stl.AdminStaff.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.stl.AdminStaff.model.adminModel;
import com.stl.AdminStaff.repository.adminRepo;

@Service
public class AdminlogService implements UserDetailsService{
    @Autowired
    adminRepo adminrepo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        adminModel admin = adminrepo.findById(username).get();
        if(admin == null) {
            return null;
        }
        
        return new Adminlog(admin);
    }
    
}

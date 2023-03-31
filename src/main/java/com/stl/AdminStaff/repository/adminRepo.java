package com.stl.AdminStaff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stl.AdminStaff.model.adminModel;

public interface adminRepo extends JpaRepository<adminModel, String> {

}

package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myresume.web.app.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, String> {

}

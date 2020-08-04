package com.myresume.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myresume.web.app.models.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {

}

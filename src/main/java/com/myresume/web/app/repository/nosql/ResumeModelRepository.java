package com.myresume.web.app.repository.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myresume.web.app.models.ResumeModel;

public interface ResumeModelRepository extends MongoRepository<ResumeModel, String>{

}

package com.crio.xmeme.repository;

import com.crio.xmeme.data.MemeEntity;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemesRepository extends MongoRepository<MemeEntity, String> {
}
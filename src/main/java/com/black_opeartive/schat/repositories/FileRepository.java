package com.black_opeartive.schat.repositories;

import com.black_opeartive.schat.models.FileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<FileDocument, String> {}

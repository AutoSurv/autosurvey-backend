package com.marcosimon.autosurvey.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDbRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}

package com.neewrobert.superuser.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.neewrobert.superuser.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

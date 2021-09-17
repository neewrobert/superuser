package com.neewrobert.superuser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neewrobert.superuser.model.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	@Query("SELECT u.id FROM User u WHERE u.email=:email")
	public Optional<Long> findUserIdByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.email=:email")
	public Optional<User> findUserByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u")
	public Optional<List<User>> getAll();

	@Query(" SELECT u FROM User u JOIN u.profile p WHERE p.profileType = :profileType")
	public Optional<List<User>> findAllUsersByProfile(@Param("profileType") String profileType);
	
}

package com.neewrobert.superuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.neewrobert.superuser.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	
	@Query("SELECT p.id FROM Profile p WHERE p.profileType=:profileType")
	public Optional<Long> findProfileIdByType(@Param("profileType") String profileType);
	
	@Query("SELECT p FROM Profile p WHERE p.profileType=:profileType")
	public Optional<Profile> findProfileByType(@Param("profileType") String profileType);

	@Modifying
	@Query("UPDATE Profile p set p.profileType = :#{#profile.profileType} WHERE p.id = :#{#profile.id}")
	public void update(@Param("profile") Profile profile);
	
	

}

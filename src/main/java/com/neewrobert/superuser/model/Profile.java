package com.neewrobert.superuser.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "profileType"))
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4455266067032586151L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable=true)
	private String profileType;

	@OneToMany(cascade = CascadeType.ALL)
	private List<User> users;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the profileType
	 */
	public String getProfileType() {
		return profileType;
	}

	/**
	 * @param profileType the profileType to set
	 */
	public void setProfileType(String profileType) {
		this.profileType = profileType.toLowerCase();
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((profileType == null) ? 0 : profileType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Profile))
			return false;
		Profile other = (Profile) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (profileType == null) {
			if (other.profileType != null)
				return false;
		} else if (!profileType.equals(other.profileType))
			return false;
		return true;
	}

}
package org.sphic.tps.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "sphic")
public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String title;
	private Integer departmentId;
	private Boolean isEnabled;
	private String telephone;
	private String email;
	private Set<Structure> structuresForLockedBy = new HashSet<Structure>(0);
	private Set<Structure> structuresForLastModified = new HashSet<Structure>(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer userId, String username, String password,
			Integer departmentId, Boolean isEnabled) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.departmentId = departmentId;
		this.isEnabled = isEnabled;
	}

	/** full constructor */
	public User(Integer userId, String username, String password,
			String firstName, String lastName, String title,
			Integer departmentId, Boolean isEnabled, String telephone,
			String email, Set<Structure> structuresForLockedBy,
			Set<Structure> structuresForLastModified) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.departmentId = departmentId;
		this.isEnabled = isEnabled;
		this.telephone = telephone;
		this.email = email;
		this.structuresForLockedBy = structuresForLockedBy;
		this.structuresForLastModified = structuresForLastModified;
	}

	// Property accessors
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "username", nullable = false, length = 40)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "first_name", length = 40)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 40)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "title", length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "department_id", nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "is_enabled", nullable = false)
	public Boolean getIsEnabled() {
		return this.isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Column(name = "telephone", length = 40)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByLockedBy")
	public Set<Structure> getStructuresForLockedBy() {
		return this.structuresForLockedBy;
	}

	public void setStructuresForLockedBy(Set<Structure> structuresForLockedBy) {
		this.structuresForLockedBy = structuresForLockedBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userByLastModified")
	public Set<Structure> getStructuresForLastModified() {
		return this.structuresForLastModified;
	}

	public void setStructuresForLastModified(
			Set<Structure> structuresForLastModified) {
		this.structuresForLastModified = structuresForLastModified;
	}

}
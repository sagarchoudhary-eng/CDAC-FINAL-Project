package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_tbl")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "user_name", length = 50, nullable = false)
	private String name;
	@Column(name = "user_email", length = 50, nullable = false)
	private String emailId;
	@Column(length = 50, nullable = false)
	private String password;
	@Column(length = 50, nullable = false)
	private String confirmPassword;
	@Column(name = "user_phone", length = 10, nullable = false)
	private String phoneNo;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user")
	private List<Address> address = new ArrayList<>();

//	@OneToOne(mappedBy = "cartOwner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonIgnoreProperties("cartOwner")
//	private AddToCart shoppingCart;

	public User() {
		System.out.println("in User default constructor");
	}

	public User(String name, String emailId, String password, String confirmPassword, String phoneNo, Role role) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneNo = phoneNo;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + ", phoneNo=" + phoneNo + ", role=" + role + "]";
	}
	

	/*
	 * @Override public String toString() { return "User [userId=" + userId +
	 * ", name=" + name + ", emailId=" + emailId + ", password=" + password +
	 * ", confirmPassword=" + confirmPassword + ", phoneNo=" + phoneNo +
	 * ", address=" + address + ", role=" + role + "]"; }
	 */

}

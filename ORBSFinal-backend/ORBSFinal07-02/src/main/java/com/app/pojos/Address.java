package com.app.pojos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "address_tbl")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(name = "address1", length = 50, nullable = false)
	private String addressLine1;
	@Column(name = "address2", length = 50, nullable = false)
	private String addressLine2;
	@Column(length = 50, nullable = false)
	private String city;
	@Column(length = 50, nullable = false)
	private String state;
	@Column(length = 50, nullable = false)
	private String country;
	@Column(length = 6, nullable = false)
	private String postalCode;
	@Enumerated(EnumType.STRING)
	private AddressType type;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties("address")
	@JoinColumn(name = "user_id")
	private User user;

	public Address() {
		System.out.println("in Address default constrctor");
	}

	public Address(String addressLine1, String addressLine2, String city, String state, String country,
			String postalCode, AddressType type) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.type = type;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [Id=" + Id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", city="
				+ city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode + ", type=" + type
				+ ", user=" + user + "]";
	}

}

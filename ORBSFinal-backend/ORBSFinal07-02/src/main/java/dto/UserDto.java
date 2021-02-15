package dto;

import com.app.pojos.Role;

public class UserDto {
	private Integer userId;
	private String name;
	private String emailId;
	private String phoneNo;
	private Role role;

	public UserDto() {
		System.out.println("in user dto");
	}

	public UserDto(Integer userId, String name, String emailId, String phoneNo, Role role) {
		super();
		this.userId = userId;
		this.name = name;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.role = role;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", phoneNo=" + phoneNo
				+ ", role=" + role + "]";
	}

}

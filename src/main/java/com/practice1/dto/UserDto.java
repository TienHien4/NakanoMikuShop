package com.practice1.dto;


	public class UserDto {
		private String email;
		private  String username;
		private  String password;
		private String fullName;
		public UserDto() {
			super();
		}
		public UserDto(String email, String username, String password, String fullName) {
			super();
			this.email = email;
			this.username = username;
			this.password = password;
			this.fullName = fullName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		
		
	
	}


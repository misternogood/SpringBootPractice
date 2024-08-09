package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(message = "姓を入力してください")
    private String lastName;
	@NotBlank(message = "名を入力してください")
    private String firstName;
	@NotBlank(message = "メールアドレス入力してください")
	 @Email
    private String email;
	@NotBlank(message = "パスワードを入力してください")
    private String password;

    public SignupForm() {}

    public SignupForm(String lastName, String firstName, String email, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

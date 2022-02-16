package com.estherNmorga.demo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberModel {
	private int id;
	@NotEmpty(message = "帳號不可為空")
	private String name;
	@NotEmpty(message = "密碼不可為空")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\w]{6,16}$", 
	 	message = "密碼必須為長度6~16位碼大小寫英文加數字")
	private String password;
	@NotEmpty(message = "電話不可為空")
	@Pattern(regexp = "^[0-9]{10,12}$",
		message = "電話必須為長度10位數字，並符合電話格式")
	private String phone;
	@NotEmpty(message = "地址不可為空")
	private String address;
	private String createDate;
	private String crypto;
}

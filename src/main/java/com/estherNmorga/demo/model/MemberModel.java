package com.estherNmorga.demo.model;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberModel {
	private int id;
	private String name;
	private String password;
	private String phone;
	private String address;
	private String createDate;
	private String crypto;
}

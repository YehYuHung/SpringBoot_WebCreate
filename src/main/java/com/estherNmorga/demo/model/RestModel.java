package com.estherNmorga.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestModel extends Base{
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String company;
	private String gender;
}

package com.koushik.usermanager.domain;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;

@Entity(name = "user")
@NoArgsConstructor
@ToString
@Getter
@Setter
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String phoneNumber;
	@Column
	private String email;
	@Column
	private Integer age;
	@Column
	@Enumerated(EnumType.STRING)
	private Roles roles;
	@Column(nullable = false,updatable = false)
	private String userCode;
	@Transient
	private List<CustomField> customFields;
	@Column
	private String password;

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
}


	                             






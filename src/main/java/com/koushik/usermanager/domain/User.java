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
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	                    )
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
	        generator = "user_sequence"
	            )
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

	public User(String firstName, String lastName, String phoneNumber, String email, Integer age, Roles roles, String userCode, List<CustomField> customFields) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.age = age;
		this.roles = roles;
		this.userCode = userCode;
		this.customFields = customFields;
	}
}


	                             






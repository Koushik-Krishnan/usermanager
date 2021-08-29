package com.koushik.usermanager.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity(name = "user")
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class User implements Serializable, UserDetails {
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

	@Column
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@Column(nullable = false,updatable = false)
	private String userCode;
	@Transient
	private List<CustomField> customFields;
	@Column
	private String password;

	private String username;

	private Boolean locked;
	private Boolean enabled;

	public User(String firstName, String lastName, String email, String password,UserRole userRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority =
				new SimpleGrantedAuthority(userRole.name());
		return Collections.singleton(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}


	                             






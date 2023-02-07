package com.example.tomato.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.tomato.constant.Role;
import com.example.tomato.dto.SignUpDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "member")
@ToString
public class Member extends BaseEntity {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;
	
	private String nickname;

	private String tel;

	private String password;

	private String address;

	@Enumerated(EnumType.STRING)
	private Role role;

	public static Member createMember(SignUpDto signUpDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(signUpDto.getName());
		member.setEmail(signUpDto.getEmail());
		member.setAddress(signUpDto.getAddress());
		member.setNickname(signUpDto.getNickname());
		member.setTel(signUpDto.getTel());

		String password = passwordEncoder.encode(signUpDto.getPassword()); // 비밀번호 암호화
		member.setPassword(password);

		member.setRole(Role.USER);

		return member;
	}
}

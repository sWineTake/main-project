package com.twocow.song.mvc.vo.user;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	private String userId;
	private String password;
	private String email;
	private String role;
	private String insertDt;
	private String deleteDt;
	private String useYn;
}

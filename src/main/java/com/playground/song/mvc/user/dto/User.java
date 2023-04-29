package com.playground.song.mvc.user.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class User {
	private String userId;
	private String password;
	private String email;
	private String role;
	private String insertDt;
	private String deleteDt;
	private LocalDateTime loginDate;
	private int wrongCnt;
	private String useYn;
}

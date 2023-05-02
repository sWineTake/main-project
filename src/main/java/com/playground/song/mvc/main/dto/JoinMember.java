package com.playground.song.mvc.main.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinMember {
	private Long memberId;
	private String snsId;
	private LocalDateTime createDate;
	private LocalDateTime lastModifiedDate;
	private String city;
	private String name;
	private String street;
	private String zipcode;

}

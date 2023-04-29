package com.playground.song.mvc.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertMember {
	private Long memberId;
	private String snsId;
	private LocalDateTime createDate;
	private LocalDateTime lastModifiedDate;
	private String city;
	private String name;
	private String street;
	private String zipcode;

}

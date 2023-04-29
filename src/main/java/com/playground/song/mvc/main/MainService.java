package com.playground.song.mvc.main;


import com.playground.song.mvc.main.dto.InsertMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainService {

	private final MainRepository mainRepository;

	public boolean insertMember(InsertMember insertMember) {
		return mainRepository.insertMember(insertMember) >= 1;
	}
}

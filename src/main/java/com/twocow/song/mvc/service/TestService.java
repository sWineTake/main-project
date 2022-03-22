package com.twocow.song.mvc.service;

import com.twocow.song.mvc.repository.TestRepository;
import com.twocow.song.mvc.vo.TestParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
	private final TestRepository testRepository;

	public List<TestParameter> getTestConnectionList() {
		return testRepository.getTestConnectionList();
	}
}

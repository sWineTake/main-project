package com.twocow.song.test;

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

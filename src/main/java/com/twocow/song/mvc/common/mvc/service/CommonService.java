package com.twocow.song.mvc.common.mvc.service;

import com.twocow.song.mvc.common.mvc.repository.CommonRepository;
import com.twocow.song.mvc.common.vo.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommonService {
	private final CommonRepository commonRepository;

	public ArrayList<Code> getCode(String masterCode) {
		return commonRepository.getCode(masterCode);
	}

}

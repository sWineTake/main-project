package com.twocow.song.mvc.admin.service.code;

import com.twocow.song.mvc.admin.repository.code.CodeRepository;
import com.twocow.song.mvc.admin.vo.code.CommCode;
import com.twocow.song.mvc.admin.vo.code.CommCodePageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CodeService {

	private final CodeRepository codeRepository;

	public ArrayList<CommCode> getCommCode(CommCodePageInfo commCodePageInfo) {
		return codeRepository.getCommCode(commCodePageInfo);
	}
}

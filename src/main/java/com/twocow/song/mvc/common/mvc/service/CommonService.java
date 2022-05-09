package com.twocow.song.mvc.common.mvc.service;

import com.twocow.song.mvc.admin.vo.code.CommCode;
import com.twocow.song.mvc.common.mvc.repository.CommonRepository;
import com.twocow.song.mvc.common.vo.Code;
import com.twocow.song.mvc.common.vo.ServiceResponseData;
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

	public ServiceResponseData insertCommCode(CommCode commCode) {
		return commonRepository.insertCommCode(commCode) >= 1
				? new ServiceResponseData("공통 코드 입력이 정상적으로 완료되었습니다.")
				: new ServiceResponseData(true, "공통 코드 입력 시 에러가발생하였습니다.\n다시 시도해주세요.");
	}

}

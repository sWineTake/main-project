package com.twocow.song.mvc.common.mvc.service;

import com.twocow.song.mvc.admin.repository.code.CodeRepository;
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
	private final CodeRepository codeRepository;

	public ArrayList<Code> getCode(String masterCode) {
		return commonRepository.getCode(masterCode);
	}

	public ServiceResponseData insertCommCode(CommCode commCode) {
		// 코드 저장 전 공통코드 중복여부 확인하기
		int codeCheck = codeRepository.getCodeCheck(commCode);
		if (codeCheck >= 1) {
			return new ServiceResponseData(true,"공통코드의 마스터코드와 코드가 이미 존재합니다.");
		}

		return codeRepository.insertCommCode(commCode) >= 1
			? new ServiceResponseData("공통 코드 입력이 정상적으로 완료되었습니다.")
			: new ServiceResponseData(true, "공통 코드 입력 시 에러가발생하였습니다.\n다시 시도해주세요.");
	}

	public ServiceResponseData updateCommCode(CommCode commCode) {
		return codeRepository.updateCommCode(commCode) >= 1
			? new ServiceResponseData("공통 코드 수정이 정상적으로 완료되었습니다.")
			: new ServiceResponseData(true, "공통 코드 입력 시 에러가발생하였습니다.\n다시 시도해주세요.");
	}

}

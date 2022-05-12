package com.twocow.song.mvc.admin.vo.code;

import lombok.Getter;
import lombok.Setter;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class CommCode {
	private String masterCode;
	private String code;
	private String title;
	private SimpleDateFormat insertDt;
	private SimpleDateFormat deleteDt;
	private String ordNo;
	private String useYn;
	private String originMasterCode;
	private String originCode;
}

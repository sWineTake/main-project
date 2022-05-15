package com.twocow.song.mvc.common.vo.user;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
// 레디스에 객체를 저장하기위해 Serializable를 사용합니다.
// Serializable는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트 형태로 데이터 변환하는 기술과(직렬화)
// 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)을 말합니다.
// java.io.Serializable를 상속받은 객체는 직렬화 할 수 있는 기본 조건을 가집니다.

// JVM의 메모리에서만 상주되어있는 객체 데이터를 그대로 영속화가 필요할 때 직렬화를 사용합니다.
// 시스템이 종료되더라도 없어지지 않는 장점을 가지며 영속화된 데이터이기때문에 네트워크로 전송이 가능합니다.
// 언제든지 필요할때 직렬화된 데이트를 가져와 역직렬화를 하여 객체를 바로 사용할수있습니다.
@Builder
public class User implements Serializable {
	private String userId;
	private String password;
	private String email;
	private String role;
	private String insertDt;
	private String deleteDt;
	private Timestamp loginDate;
	private int wrongCnt;
	private String useYn;
}

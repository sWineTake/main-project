package com.twocow.song.utils.regular.password;

import com.twocow.song.mvc.vo.ServiceResponseData;
import com.twocow.song.utils.messages.MessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PasswordUtils {

	@Autowired
	MessageConfig messageConfig;

	public ServiceResponseData checkPassword(String pwd, String id){
		// 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
		Pattern passPattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
		if(!passPattern.matcher(pwd).find()){
			return new ServiceResponseData(true, messageConfig.getMessage("password.missmatch.pattern"));
		}

		// 반복된 문자 확인
		Pattern samePattern = Pattern.compile("(\\w)\\1\\1\\1");
		if(samePattern.matcher(pwd).find()){
			return new ServiceResponseData(true, messageConfig.getMessage("passwrod.same.pattern"));
		}

		// 아이디 포함 확인
		if(pwd.contains(id)){
			return new ServiceResponseData(true, messageConfig.getMessage("passwrod.containsId.pattern"));
		}

		// 특수문자 확인
		Pattern getSpecial = Pattern.compile("\\W");
		Pattern passPattern4 = Pattern.compile("[!@#$%^*+=-]");
		for(int i = 0; i < pwd.length(); i++){
			String getChar = String.valueOf(pwd.charAt(i));
			if(getSpecial.matcher(getChar).find() && passPattern4.matcher(getChar).find()){
				return new ServiceResponseData(true, messageConfig.getMessage("passwrod.special.pattern"));
			}
		}

		return new ServiceResponseData();
	}

}

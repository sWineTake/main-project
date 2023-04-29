package com.playground.song.utils.regular.email;

import com.playground.song.mvc.common.dto.ServiceResponseData;
import com.playground.song.utils.messages.MessageConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailUtils {

	@Autowired
	MessageConfig messageConfig;

	public ServiceResponseData checkEmail(String email){
		String regx = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regx);
		if (!pattern.matcher(email).find()) {
			return new ServiceResponseData(true, messageConfig.getMessage("email.missmatch.pattern"));
		}
		return new ServiceResponseData();
	}

}

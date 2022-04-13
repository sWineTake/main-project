package com.twocow.song.utils.session;

import com.twocow.song.mvc.vo.user.User;
import com.twocow.song.utils.servlet.ServletUtils;
import org.springframework.stereotype.Component;

@Component
public class SessionUtils {
	private final String SESSION_NAME_MEMBER = "SESSION_MEMBER";

	public void setUser(User user) {
		ServletUtils.getSession().setAttribute(SESSION_NAME_MEMBER, user);
	}

	public User getUser() {
		return (User) ServletUtils.getSession().getAttribute(SESSION_NAME_MEMBER);
	}

	public void delUser() {
		ServletUtils.getSession().removeAttribute(SESSION_NAME_MEMBER);
	}

	public boolean isLogin() {
		User attribute = (User) ServletUtils.getSession().getAttribute(SESSION_NAME_MEMBER);
		if (attribute == null) {
			return false;
		}
		return true;
	}

}

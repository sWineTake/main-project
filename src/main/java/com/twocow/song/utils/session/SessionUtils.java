package com.twocow.song.utils.session;

import com.twocow.song.enums.Role;
import com.twocow.song.mvc.common.vo.user.User;
import com.twocow.song.utils.servlet.ServletUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

@Component
public class SessionUtils {
	private final static String SESSION_NAME_MEMBER = "SESSION_MEMBER";

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

	public static String getUserId() {
		User user = (User) ServletUtils.getSession().getAttribute(SESSION_NAME_MEMBER);
		if (ObjectUtils.isEmpty(user) || StringUtils.isBlank(user.getUserId())) {
			return "";
		}
		return user.getUserId();
	}

	public static int getUserRoleLevel() {
		User userInfo = (User) ServletUtils.getSession().getAttribute(SESSION_NAME_MEMBER);
		if (ObjectUtils.isEmpty(userInfo)) {
			return 0;
		}
		Optional<Role> userRole = Stream.of(Role.values())
				.filter(role -> role.getRole().equals(userInfo.getRole()))
				.findFirst();
		// 권한을 못찾음 -> 에러 (모든 계정은 권한이 있어야함)
		if (userRole.isEmpty()) {
			return userRole.get().getLevel();
		}
		return 0;
	}

}

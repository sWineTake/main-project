package com.playground.song.configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Properties;

/**
  * 로컬, 개발, 운영에 따른 값을 프로젝트 시작 후 바로 셋팅하여 설정함
  * 서버단에서 바로 사용가능
  * **/
@Slf4j
@Getter
@Configurable
public class GlobalConfig {

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ApplicationContext context;

	private String activeEnv = "local";
	private Properties properties;

	private boolean local;
	private boolean dev;
	private boolean prod;

	/**
	 * 클래스가 생성시 메서드가 호출된다.
	 * 설정관련 기능 수행
	 */
	@PostConstruct
	public void init() {
		boolean successEnv = setEnv();
		String resourcePath = String.format("classpath:global/globals-%s.properties", activeEnv);
		try {
			Resource resource = resourceLoader.getResource(resourcePath);
			properties = PropertiesLoaderUtils.loadProperties(resource);

			local = activeEnv.equals("local");
			dev = activeEnv.equals("dev");
			prod = activeEnv.equals("prod");

			// 예제
			// String value = getValue("properties key");
		}
		catch (Exception e) {
			log.info("resourcePath : {} 파일을 불러오는 중 오류가 발생했습니다.", resourcePath);
			log.error("error", e);
		}
	}

	/**
	 * properties 세팅
	 * @param key
	 * @return
	 */
	String getValue(String key) {
		String value = properties.getProperty(key);
		if (StringUtils.isNotEmpty(value)) {
			return value.trim();
		}
		return null;
	}

	/**
	 * Env환경 변수 세팅
	 * @return
	 */
	boolean setEnv() {
		String[] activeProfiles = context.getEnvironment().getActiveProfiles();
		if (ObjectUtils.isNotEmpty(activeProfiles)) {
			activeEnv = activeProfiles[0];
			log.debug("environment activeProfile : {}", activeEnv);
		}
		return true;
	}
}

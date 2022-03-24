package com.twocow.song.configuration.database;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * DataBase 트랜잭션 관리
 */
@Configuration
// 매퍼를 하나씩 등록하는게 아닌 페키지 경로를 지정하여 이하 위치에있는 인터페이스들은 전부 맵퍼로 사용할수있다.
@MapperScan(value = { "com.twocow.song.*" })
@RequiredArgsConstructor
@Slf4j
public class DatabaseConfiguration {

	private final ApplicationContext context;

	@Bean
	public DataSource dataSource() {
		// PooledDataSource 단순하고 동기적이며 스레드로부터 안전한 데이터베이스 연결 풀입니다.
		// 데이터베이스와 연결된 Connection을 미리 만들어서 pool 속에 저장해 두고 있다가 필요할 때
		// Connection을 Pool에서 쓰고 다시 Pool에 반환하는 기법을 말합니다.
		// 미리 생성해두기 때문에 데이터베이스에 부하를 줄이고 유동적으로 연결을 관리 할 수 있습니다.
		PooledDataSource dataSource = new PooledDataSource();
		Environment env = context.getEnvironment();
		dataSource.setDriver(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDefaultAutoCommit(false);
		dataSource.setPoolPingEnabled(true);
		dataSource.setPoolPingQuery("SELECT 1");

		log.info("data Source : {}", dataSource);
		return dataSource;
	}

	// Mysql 서버와 MyBatis를 연결해주는건 SqlSessionFactory라는 객체입니다.
	@Bean
	public SqlSessionFactory sqlSessionFactory(final DataSource dataSource)
		throws Exception {

		// SqlSessionFactory 객체를 생성 해주는 SqlSessionFactoryBean를 사용해서 객체를 생성시킵니다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.twocow.song.*");


		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();

		return null;
	}

}

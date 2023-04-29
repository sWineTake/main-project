package com.playground.song.configuration.database;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DataBase 트랜잭션 관리
 */
@Configuration
// 매퍼를 하나씩 등록하는게 아닌 페키지 경로를 지정하여 이하 위치에있는 인터페이스들은 전부 맵퍼로 사용할수있습니다.
@MapperScan(value = { "com.twocow.song.*" })
@RequiredArgsConstructor
@Slf4j
public class DatabaseConfiguration {

	private final Environment env;

	@Bean
	public DataSource dataSource() {
		// PooledDataSource 단순하고 동기적이며 스레드로부터 안전한 데이터베이스 연결 풀입니다.
		// 데이터베이스와 연결된 Connection을 미리 만들어서 pool 속에 저장해 두고 있다가 필요할 때
		// Connection을 Pool에서 쓰고 다시 Pool에 반환하는 기법을 말합니다.
		// 미리 생성해두기 때문에 데이터베이스에 부하를 줄이고 유동적으로 연결을 관리 할 수 있습니다.
		PooledDataSource dataSource = new PooledDataSource();
		dataSource.setDriver(env.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setDefaultAutoCommit(false);

		// MyBatis에서 일정 시간을 기준으로 지속적으로 유효한 커넥션 확인을 하기위해서 설정하는 값입니다.
		// dataSource.setPoolPingEnabled(false);
		// dataSource.setPoolPingQuery("SELECT 1");

		log.info("DataBase : {}", dataSource);
		return dataSource;
	}

	// Mysql 서버와 MyBatis를 연결해주는건 SqlSessionFactory 객체입니다.
	@Bean
	public SqlSessionFactory sqlSessionFactory(final DataSource dataSource)
		throws Exception {
		// SqlSessionFactory 객체를 생성 해주는 SqlSessionFactoryBean를 사용해서 객체를 생성시킵니다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(
			new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/**.xml"));
		sqlSessionFactoryBean.setTypeAliasesPackage("com.twocow.song.*");

		// DB세션 구성
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		// MyBatis 카멜 표기법 설정
		configuration.setMapUnderscoreToCamelCase(true);
		sqlSessionFactoryBean.setConfiguration(configuration);
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * DB 트랜잭션 공통 처리
	 * */
	@Bean
	public TransactionInterceptor txAdvice(final DataSourceTransactionManager transactionManager) {
		// 선언적 트랜잭션
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		Properties properties = new Properties();

		// 롤백 규칙 추가
		List<RollbackRuleAttribute> rollbackRules = new ArrayList<RollbackRuleAttribute>();
		rollbackRules.add(new RollbackRuleAttribute(Exception.class)); // 에러 발생시 롤백

		// Select
		DefaultTransactionAttribute readAttribute = new DefaultTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED);
		readAttribute.setReadOnly(true);
		readAttribute.setTimeout(10); // Select 트랜잭션 시간 5초
		String readOnly = readAttribute.toString();
		properties.setProperty("get*", readOnly);
		properties.setProperty("check*", readOnly);

		// 트랜잭션
		RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(
				TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
		writeAttribute.setTimeout(10);
		String write = writeAttribute.toString();
		properties.setProperty("update*", write);
		properties.setProperty("delete*", write);
		properties.setProperty("insert*", write);
		properties.setProperty("work*", write); //여러번의 insert,update,delete가 일어나는 작업

		// 설정 바인딩
		txAdvice.setTransactionAttributes(properties);
		txAdvice.setTransactionManager(transactionManager);
		return txAdvice;
	}

	@Bean
	public Advisor txAdviceAdvisor(final TransactionInterceptor txAdvice) {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.twocow.song..service.*Service.*(..))");
		return new DefaultPointcutAdvisor(pointcut, txAdvice);
	}

	@Bean
	public SqlSessionTemplate sqlSession(final SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	public DataSourceTransactionManager transactionManager(final DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}

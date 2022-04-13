# main-project

### 프로젝트 규칙
**--** interface default는 @implSpec 설명 적어두기

### project-info
1. DB : mysql(MyBatis)
2. template : Bootstrap
3. AOP 공통 처리, Interceptor

### 프로젝트 정보
> DataBase
> > DB 연결 테스트 서비스 제공 (MySqlConnection.java)
> 
> > DB 트랜잭션 관리 Service단에서 메소드 호출시 이름으로 트랜잭션 관리됨

> log
> > AOP에서 xml실행전 클래스명.id 기록
> 
> > Dev, Prod 환경에서는 log파일 생성 및 기록

> 스프링 시큐리티
> > SecurityConfig 에서 상세 설정
>
> > 시큐리티 로그인기능 사용하지않음. 레디스를 사용하기때문에 /api/user/login으로 로그인을 진행

> 공통
> > 에러 처리 BaseMappingExceptionResolver 에서 공통으로 처리함
>
> > Config 어노테이션처리로 preHandler에서 분기 가능 (스프링 시큐리티 사용으로 사용자가 알맞게 사용 분리할수있음)
> 
> > Validation 객체를 만들어서 파라미터에 맞는 값 체크 가능 - 기준 맞지않을시 커스텀에러 발생

> 프로젝트 디자인 템플릿
> > Copyright © Start Bootstrap LLC 2021
> > https://startbootstrap.com/templates

## DB info
- MySqlConnection.java 파일로 DB접근 테스트 가능
- /document/db 폴더에 DB 스키마 파일
- 로컬 mysql 셋팅 정보 (https://ghostweb.tistory.com/100)
- mysql-connector-java 라이브러리 사용



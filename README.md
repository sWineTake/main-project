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

> 프로젝트 디자인 템플릿
> > Copyright © Start Bootstrap LLC 2021
> > https://startbootstrap.com/templates
 

## DB info
- MySqlConnection.java 파일로 DB접근 테스트 가능
- /document/db 폴더에 DB 스키마 파일
- 로컬 mysql 셋팅 정보 (https://ghostweb.tistory.com/100)
- mysql-connector-java 라이브러리 사용



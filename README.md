# main-project

### 프로젝트 정보
> 서버 실행 방법
> > 로컬 DB -> h2 database 사용, 
> 
> > id : sa , pw : 1234, database name : sa(변경 시 application-local.properties 파일에서 DB정보 직접 변경 필요)
> 
> > Active Profile : local 


> DataBase
> > DB 트랜잭션 관리 Service단에서 메소드 호출시 이름으로 트랜잭션 관리됨
> 
> > DatabaseConfiguration 파일 참고

> Swagger API 명세서
> > /swagger-ui/swagger-ui/index.html 

> log
> > AOP에서 xml실행전 클래스명.id 기록
> 
> > Dev, Prod 환경에서는 log파일 생성 및 기록

> 스프링 시큐리티
> > SecurityConfig 에서 상세 설정

> 공통
> > 에러 처리 BaseMappingExceptionResolver 에서 공통으로 처리함
>
> > Config 어노테이션처리로 preHandler에서 분기 가능
> 
> > Validation 객체를 만들어서 파라미터에 맞는 값 체크 가능 - 기준 맞지않을시 커스텀에러 발생

## DB info
- /document/db 폴더에 DB 스키마 파일



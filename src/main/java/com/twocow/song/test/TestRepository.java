package com.twocow.song.test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface TestRepository {
	List<TestParameter> getTestConnectionList();
}

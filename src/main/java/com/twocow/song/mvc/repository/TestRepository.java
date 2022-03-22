package com.twocow.song.mvc.repository;
import com.twocow.song.mvc.vo.TestParameter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface TestRepository {
	List<TestParameter> getTestConnectionList();
}

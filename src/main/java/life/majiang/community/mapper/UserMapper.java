package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	@Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountid},#{token},#{gmtcreate},#{gmtModified})")
	//执行Insert语句时，调用model的get方法
	void insert(User user);

	@Select("select * from user where token = #{token}")
	User findByToken(@Param("token") String token);
}

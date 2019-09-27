package com.easyApp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.easyApp.entity.App;


@Mapper
public interface CreateAppDao {
	
	@Select(" select * from em_applist where user_id = #{id} ")
	public List<App> queryCustById(Integer id) ;

	@Insert(" insert into em_applist (user_id ,app_url,app_dec) values (#{id},#{url},#{dec})")
	public void insetrtApp(Integer id, String url,String dec) ;
	
	
	
}

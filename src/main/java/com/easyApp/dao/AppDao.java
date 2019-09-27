package com.easyApp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.easyApp.entity.App;


@Mapper
public interface AppDao {
	
	public List<App> queryAppListByUserId(Map<String,Object> map);
	
//	public int 

}

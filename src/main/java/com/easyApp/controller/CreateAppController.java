package com.easyApp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.easyApp.dao.AppDao;
import com.easyApp.dao.CreateAppDao;
import com.easyApp.entity.App;
import com.easyApp.entity.Result;
@RestController
public class CreateAppController {
	
	@Autowired
	CreateAppDao createAppDao;
	
	@Autowired
	AppDao appDao;
	
//	@RequestMapping("/queryAppById")
	@RequestMapping(value = "/queryAppById", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Result queryAppById(@RequestBody JSONObject jsonParam){
		int userId = jsonParam.getInteger("userId");
	    List<App> arr = createAppDao.queryCustById(userId);
	    return Result.ok(arr);
	}
	
	
	@RequestMapping("/appd")
	public Result queryAppListByUserId(@RequestParam("userId")Integer userId) {
		
		Map<String,Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("limit", 3);
		param.put("offset",0);
		
	    List<App> arr = appDao.queryAppListByUserId(param);
	    return Result.ok(arr);
	}
	
//	@RequestMapping("/createApp")
//	@ResponseBody
//	@RequestMapping("/createApp")
	@RequestMapping(value = "/createApp", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//	public Result creatApp(@RequestParam("url")String url,@RequestParam("userId")Integer userId) throws IOException{
	public Result getByJSON(@RequestBody JSONObject jsonParam) throws IOException{
//		 System.out.println(jsonParam.toJSONString());
		String url = jsonParam.getString("url");
		int userId = jsonParam.getInteger("userId");
		
	//		List<String> params = new ArrayList<String>();
//		params.add("ps");
//        params.add(" -ef ");
//        params.add(" | ");
//        params.add(" grep ");
//        params.add("flutter");
		// 创建系统进程
//        ProcessBuilder processBuilder = new ProcessBuilder(params);
//		ProcessBuilder processBuilder = new ProcessBuilder("" + "/Users/fodelf/git/python/abc.sh", "",
//                "", "");
		ProcessBuilder processBuilder = new ProcessBuilder("" + "/usr/flutter/abc.sh", "",
                "", "");
        processBuilder.redirectErrorStream(true);
//      processBuilder.directory(new File("/Users/fodelf/HBuilderProjects/cy"));
      try {
          Process process = processBuilder.start();
          
          BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
          String line;
          while ((line = br.readLine()) != null) {
              System.out.println(line);
              if(line.indexOf("flutter build apk")>0){
            	  return Result.err("系统中有正在执行的任务请稍后重试，或者等我买个好点的服务器");
              }
          }
          int exitCode = process.waitFor();
//          System.out.println("exitCode = "+exitCode);
      } catch (IOException e) {
          e.printStackTrace();
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
//		File file =  new File("/Users/fodelf/git/flutter_learn/easy_app/lib/main1.dart");
		File file =  new File("/usr/flutter/easy_app/lib/main1.dart");
		try {
		    file.createNewFile();
		    //读取文件(字符流)
		    BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("/usr/flutter/easy_app/lib/main.dart"),"utf-8"));
		    //写入相应的文件
		    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/usr/flutter/easy_app/lib/main1.dart"),"utf-8"));
		    //读取数据
		    //循环取出数据
		    String str = null;
		    System.out.println("开始了");
		    while ((str = in.readLine()) != null) {
//		        System.out.println(str);
		        if(str.indexOf("Flutter Demo Home Page")>0){
		        	String urlstr =  "url:"+"'"+url+"'"+")";
		        	System.out.println(urlstr);
		        	str = str.replaceAll("url.*\\)",urlstr);
		        }
		        //写入相关文件
		        out.write(str);
		        out.newLine();
		    }
		    System.out.println("替换url结束了");
		    //清楚缓存
		    out.flush();
		    //关闭流
		    in.close();
		    out.close();
//		    File oldName = new File("/Users/fodelf/git/flutter_learn/easy_app/lib/main1.dart");
		    File oldName = new File("/usr/flutter/easy_app/lib/main1.dart");
//		    File newName = new File("/Users/fodelf/git/flutter_learn/easy_app/lib/main.dart");
		    File newName = new File("/usr/flutter/easy_app/lib/main.dart");
		    if(oldName.renameTo(newName)) {
		        System.out.println("已重命名");
		    } else {
		        System.out.println("Error");
		    }
		    System.out.println("创建文件成功了");
//		    CreatApp.ok();
		    
		    TaskThread task = new TaskThread(userId,createAppDao);
		    Thread t1 = new Thread(task);
		    t1.start();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("创建文件失败了");

		}
	    return Result.ok(null);
		
	}
}

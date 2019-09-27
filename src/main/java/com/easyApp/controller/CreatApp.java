package com.easyApp.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import com.easyApp.dao.CreateAppDao;


public class CreatApp {
	
	
	CreateAppDao createAppDao;

	
	public CreateAppDao getCreateAppDao() {
		return createAppDao;
	}

	public void setCreateAppDao(CreateAppDao createAppDao) {
		this.createAppDao = createAppDao;
	}
	


	public CreatApp(CreateAppDao createAppDao) {
		super();
		this.createAppDao = createAppDao;
	}

	@Async
	public void ok(Integer userId) {
		try {
			System.out.println("开始构建APP");
			ProcessBuilder pb = new ProcessBuilder("/usr/flutter/flutter-1.10.2/flutter/bin/flutter","build","apk");
			pb.directory(new File("/usr/flutter/easy_app"));
			Process ps = pb.start();
			InputStream is = ps.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			

			String line;
			while ((line = br.readLine()) != null) {
			System.err.println(line);
			}


			InputStream is1 = ps.getInputStream();
			BufferedReader br1 = new BufferedReader(new InputStreamReader(is1));


			String line1;
			while ((line1 = br1.readLine()) != null) {
			System.out.println(line1);
			}
			
//		    String filePath = "/Users/fodelf/Desktop/appDownloads/" + userId +"/" +new Date().getTime();
			 String filePath = "/usr/local/nginx2/appDownloads/" + userId +"/" +new Date().getTime();			
			File file =  new File(filePath);
			if (!file.exists() && !file.isDirectory()) {
				try {
		          file.mkdirs(); 
		          String url = filePath +"/app-release.apk";
		          File toFile =  new File(url);
					try {
						toFile.createNewFile();
						//File fromFile =  new File("/Users/fodelf/git/flutter_learn/easy_app/build/app/outputs/apk/release/app-release.apk");
						File fromFile =  new File("/usr/flutter/easy_app/build/app/outputs/apk/release/app-release.apk");
						try (FileInputStream in = new FileInputStream(fromFile);
								FileOutputStream ou = new FileOutputStream(toFile);
								FileChannel ci = in.getChannel();
								FileChannel co = ou.getChannel()) {
							long size = ci.transferTo(0, ci.size(), co);
							if (size == fromFile.length()) {
								ou.close();
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						int exitCode = ps.waitFor();
//						System.out.println(exitCode);
						System.out.println("应用,,,,,userId::"+userId +"  url:: " + url);
						String appUrl = "http://easymarket.chehe88.com/appDownloads/" + userId +"/" +new Date().getTime();
						createAppDao.insetrtApp(userId,appUrl,"xx");
						System.out.println("应用已经生成在桌面");
//						根据时间戳创建app
						
					} catch (IOException e2) {
					    e2.printStackTrace();
					    System.out.println("创建文件失败了");

					}
				}catch (Exception e) {
					e.printStackTrace();
				}
		    }
			} catch (Exception e) {
			e.printStackTrace();
			}
		
	}
	
	
	
}

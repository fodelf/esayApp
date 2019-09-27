package com.easyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Testtest2 {
	
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        String batPath = "sh " +"/Users/fodelf/Documents/workspace-sts-3.9.9.RELEASE/easyApp/src/main/resources/static/abc.sh"; 
		
//		String batPath = "H:/ipconfig1.sh"; 
		
        File batFile = new File(batPath);
        boolean batFileExist = batFile.exists();
        System.out.println("batFileExist:" + batFileExist);
        
            callCmd(batPath);
        
    }
    
    private static void  callCmd(String locationCmd){
        StringBuilder sb = new StringBuilder();
        try {
            Process child = Runtime.getRuntime().exec(locationCmd);
            InputStream in = child.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line + "\n");
            }
               in.close();
            try {
                child.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("sb:" + sb.toString());
            System.out.println("callCmd execute finished");           
        } catch (IOException e) {
            System.out.println(e);
        }
     }
}

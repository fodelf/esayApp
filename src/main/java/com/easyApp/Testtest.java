package com.easyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Testtest {
	
	public static void main(String[] args) throws IOException {
		List<String> params = new ArrayList<String>();
        params.add("java");
        params.add("-version");
        ProcessBuilder processBuilder = new ProcessBuilder(params);
//        System.out.println(processBuilder.directory());
//        System.out.println(processBuilder.environment());
        processBuilder.redirectErrorStream(true);
//        processBuilder.directory(new File("/Users/fodelf/HBuilderProjects/cy"));
        try {
            Process process = processBuilder.start();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            System.out.println("exitCode = "+exitCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}		
}

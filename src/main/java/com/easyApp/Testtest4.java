package com.easyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Testtest4 {
	
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("" + "/Users/fodelf/git/python/abc.sh", "",
		                "", "");
		pb.directory(new File("/Users/fodelf/git/python"));
		int runningStatus = 0;
		String s = null;
		try {
		Process p = pb.start();
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
		try {
		runningStatus = p.waitFor();
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		} catch (IOException e) {
			System.out.println(e);
		}
		if (runningStatus != 0) {
		}
		return;
	}		
}

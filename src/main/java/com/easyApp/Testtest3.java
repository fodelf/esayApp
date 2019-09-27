package com.easyApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Testtest3 {
	
	public static void main(String[] args) throws IOException {
		try {
			ProcessBuilder pb = new ProcessBuilder("/Users/fodelf/flutter/bin/flutter","build","apk");
			pb.directory(new File("/Users/fodelf/git/flutter_learn/easy_app"));
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


			int exitCode = ps.waitFor();
			System.out.println(exitCode);
			} catch (Exception e) {
			e.printStackTrace();
			}	
	}
}

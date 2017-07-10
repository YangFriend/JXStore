package org.yang.util;

import java.io.File;
import org.junit.Test;

public class TestFile {

	public static void main(String[] args) {
	
	}
	
	@Test
	public void testSpring() {
		String directory = "C:\\userData\\framework\\struts2.3";

		File path = new File(directory);

		if (!path.isDirectory()) {
			System.out.println("非目录");
			return;
		}
		System.out.println(directory + "\\ 该目录下的所有文件名有:");
		File[] list = path.listFiles();
		for (File f : list) {
			if (!f.isDirectory())
				System.out.println(f.getName());
		}

	}

}

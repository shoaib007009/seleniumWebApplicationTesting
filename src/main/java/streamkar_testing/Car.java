package streamkar_testing;

import java.io.File;

public class Car {

	public static void main(String[] args)  {

		File file = new File("chromedriver.exe");

		String filePath = file.getAbsolutePath();
		
		System.out.println(filePath);
	}

}
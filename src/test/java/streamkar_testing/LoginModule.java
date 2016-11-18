package streamkar_testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class LoginModule {

	@Test
	public void facebookLoginTest() throws Exception{  // This test will check all login test cases with respect to facebook.
		
		// File path to excel sheet which contains username and passwords of sites.
		String excelFilePath = "C:\\Users\\noman\\Documents\\workspace-sts-3.8.2.RELEASE\\streamkar_testing\\src\\main\\resources\\login_successful.xls";
	
		
		// Input Stream for excel file.
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		// Creating Workbook.
		Workbook workbook = new HSSFWorkbook(inputStream);
		
		// Reading Sheet From Workbook.
		Sheet facebook = workbook.getSheet("facebook");
		
		//Chrome Web Driver Setup
		ChromeOptions options = new ChromeOptions();
					  options.addArguments("--start-maximized");
		
		// File path to excel sheet which contains username and passwords of sites.	
		File fileChrome = new File(getClass().getClassLoader().getResource("chromedriver.exe").getFile());
		String chromeDriverFilePath = fileChrome.getAbsolutePath();
		
		//System Property for driver path.
		System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
		
		// File Paths
		System.out.println("Excel File Path: " + excelFilePath);
		System.out.println("Chrome Driver File Path: " + chromeDriverFilePath);
		
		for (Row row : facebook) { // Start of for each loop.
			
				if (row.getRowNum() >= 1) { // Start of first if condition.
					
						System.out.println("Current Row Number: " + row.getRowNum());
						
					Cell currentUsernameE  =  row.getCell(0); // First cell in the row.
					Cell currentPasswordE  =  row.getCell(1); // Second cell in the row.
					Cell currentNickNameE  =  row.getCell(2); // Third cell in the row.
					Cell expectedResultE   =  row.getCell(3); // Forth cell in the row.
						 
						String currentUsername  =  currentUsernameE == null ? "" : currentUsernameE.toString(); 
						String currentPassword  =  currentPasswordE == null ? "" : currentPasswordE.toString();
						String currentNickName  =  currentNickNameE == null ? "" : currentNickNameE.toString(); 
						String expectedResult   =  expectedResultE  == null ? "" : expectedResultE.toString();  
						
						System.out.println("User Information From Excel Sheet:");
						System.out.println("Facebook User Name:   " + currentUsername);
						System.out.println("Facebook Password:    " + currentPassword);
						System.out.println("Facebook Nick Name:   " + currentNickName);
						System.out.println("Test Case Expected Result: " + expectedResult);
						
						
							// Condition = Username: empty + Password: empty + Expected Result: Test Failed.
							if (currentUsername.equals("") && currentPassword.equals("") && expectedResult.equals("Test Failed")) {
								System.out.println("Test case Evaluation:");
								System.out.println("Username: Blank"  );
								System.out.println("Password: Blank"  );
								System.out.println("Test case expected result: Test Failed");
								
								
								WebDriver driver = new ChromeDriver(options);
								//WebDriver driver = new FirefoxDriver();
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								driver.get("http://www.streamkar.com/");
								String homePage = driver.getWindowHandle();
								driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
								driver.findElement(By.id("facebook-btn")).click();
								driver.switchTo().window("authorize_facebook");
								driver.findElement(By.id("email")).sendKeys(currentUsername);
								driver.findElement(By.id("pass")).sendKeys(currentPassword);
								driver.findElement(By.name("login")).submit();
								Thread.sleep(3000L);
								driver.switchTo().window(homePage);
								
								String nickNameInternal = currentNickName;
								String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							    
								assertTrue(nickNameInternal != nickNameExternal);
								
								row.createCell(4).setCellValue("Test Failed");
								System.out.println("Test Status: Test Passed !!!");
								System.out.println("Reason: The username and password was empty so the user should not be able to login to the application.");
								
								driver.quit();
						
							// Condition = Username: empty + Password: provided + Expected Result: Test Failed.	
							} else if (currentUsername.equals("") && currentPassword.length() > 0 && expectedResult.equals("Test Failed")) {
								
								System.out.println("Test case Evaluation:");
								System.out.println("Username: Blank"  );
								System.out.println("Password: Provided"  );
								System.out.println("Test case expected result: Test Failed");
								
								
								WebDriver driver = new ChromeDriver(options);
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								driver.get("http://www.streamkar.com/");
								String homePage = driver.getWindowHandle();
								driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
								driver.findElement(By.id("facebook-btn")).click();
								driver.switchTo().window("authorize_facebook");
								driver.findElement(By.id("email")).sendKeys(currentUsername);
								driver.findElement(By.id("pass")).sendKeys(currentPassword);
								driver.findElement(By.name("login")).submit();
								Thread.sleep(3000L);
								driver.switchTo().window(homePage);
								
								String nickNameInternal = currentNickName;
								String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							    
								assertTrue(nickNameInternal != nickNameExternal);
								row.createCell(4).setCellValue("Test Failed");
								System.out.println("Test Status: Test Passed !!!");
								System.out.println("Reason: The username was empty so the user should not be able to login to the application.");
								
								driver.quit();
								
							// Condition = Username: provided + Password: empty + Expected Result: Test Failed.	
							} else if (currentUsername.length() > 0 && currentPassword.equals("") && expectedResult.equals("Test Failed")){
								
								System.out.println("Test case Evaluation:");
								System.out.println("Username: Provided"  );
								System.out.println("Password: Blank"  );
								System.out.println("Test case expected result: Test Failed");
								
								
								WebDriver driver = new ChromeDriver(options);
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								driver.get("http://www.streamkar.com/");
								String homePage = driver.getWindowHandle();
								driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
								driver.findElement(By.id("facebook-btn")).click();
								driver.switchTo().window("authorize_facebook");
								driver.findElement(By.id("email")).sendKeys(currentUsername);
								driver.findElement(By.id("pass")).sendKeys(currentPassword);
								driver.findElement(By.name("login")).submit();
								Thread.sleep(3000L);
								driver.switchTo().window(homePage);
								
								String nickNameInternal = currentNickName;
								String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							    
								assertTrue(nickNameInternal != nickNameExternal);
								row.createCell(4).setCellValue("Test Failed");
								System.out.println("Test Status: Test Passed !!!");
								System.out.println("Reason: The password was empty so the user should not be able to login to the application.");
								
								driver.quit();
								
							// Condition = Username: provided + Password: provided + Expected Result: Test Failed.	
							} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Failed")){
								
								System.out.println("Test case Evaluation:");
								System.out.println("Username: Provided"  );
								System.out.println("Password: Provided"  );
								System.out.println("Test case expected result: Test Failed");
								System.out.println("Reason: The username and password are not correct so the user should not be able to login to the application.");
								
								WebDriver driver = new ChromeDriver(options);
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								driver.get("http://www.streamkar.com/");
								String homePage = driver.getWindowHandle();
								driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
								driver.findElement(By.id("facebook-btn")).click();
								driver.switchTo().window("authorize_facebook");
								driver.findElement(By.id("email")).sendKeys(currentUsername);
								driver.findElement(By.id("pass")).sendKeys(currentPassword);
								driver.findElement(By.name("login")).submit();
								Thread.sleep(3000L);
								driver.switchTo().window(homePage);
								
								String nickNameInternal = currentNickName;
								String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							    
								assertTrue(nickNameInternal != nickNameExternal);
								row.createCell(4).setCellValue("Test Failed");
								System.out.println("Test Status: Test Passed !!!");
								
								driver.quit();
							
							// Condition = Username: provided + Password: provided + Expected Result: Test Passed + Logout: True.		
							} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Passed")){
								
								System.out.println("Test case Evaluation:");
								System.out.println("Username: Provided"  );
								System.out.println("Password: Provided"  );
								System.out.println("Test case expected result: Test Passed");
								
								
								WebDriver driver = new ChromeDriver(options);
								driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
								driver.get("http://www.streamkar.com/");
								String homePage = driver.getWindowHandle();
								driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
								driver.findElement(By.id("facebook-btn")).click();
								driver.switchTo().window("authorize_facebook");
								driver.findElement(By.id("email")).sendKeys(currentUsername);
								driver.findElement(By.id("pass")).sendKeys(currentPassword);
								driver.findElement(By.name("login")).submit();
								Thread.sleep(3000L);
								driver.switchTo().window(homePage);
								
								String nickNameInternal = currentNickName;
								String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
								
								assertEquals(nickNameInternal, nickNameExternal);
								
								driver.findElement(By.linkText(currentNickName.toString())).click();
								driver.findElement(By.linkText("Sign Out")).click();

								Thread.sleep(5000L);
								row.createCell(4).setCellValue("Test Passed");
								System.out.println("Test Status: Test Passed !!!");
								System.out.println("Reason: The username and password are correct so the user should  be able to login/logout the application.");
								driver.quit();
								
							}
							
							else {
								
								System.out.println("There was an exception. Please check your " + "\"Test Case Nature\"" + " in excel sheet.");
								row.createCell(4).setCellValue("Check Test case");
							} // End of second if condition.
						
						
				} else {
					
					    System.out.println("Facebook: Header Row Skipped ...");

				} // End of first if condition.
			
			
		}// For each loop completion.
		
		
		inputStream.close();
		System.out.println("File Reading Completed !!!");
		
		FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
		workbook.write(outputStream);
		System.out.println("File Writing Completed !!!");
		
		workbook.close();
		outputStream.close();
		System.out.println("Test Completed !!!");
		
		
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	@Test
	public void googleLoginTest() throws Exception{ // This test will check all login test cases with respect to google.
		
		// File path to excel sheet which contains username and passwords of sites.
		String excelFilePath = "C:\\Users\\noman\\Documents\\workspace-sts-3.8.2.RELEASE\\streamkar_testing\\src\\main\\resources\\login_successful.xls";
		
		// Input Stream for excel file.
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		// Creating Workbook.
		Workbook workbook = new HSSFWorkbook(inputStream);
		
		// Reading Sheet From Workbook.
		Sheet google = workbook.getSheet("google");
		
		//Chrome Web Driver Setup
		ChromeOptions options = new ChromeOptions();
		              options.addArguments("--start-maximized");
		      
		// File path to excel sheet which contains username and passwords of sites.	
		File fileChrome = new File(getClass().getClassLoader().getResource("chromedriver.exe").getFile());
		String chromeDriverFilePath = fileChrome.getAbsolutePath();            
		            
		
		//System Property for driver path.
		System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
		
		// File Paths
		System.out.println("Excel File Path: " + excelFilePath);
		System.out.println("Chrome Driver File Path: " + chromeDriverFilePath);

		
		  for (Row row : google) { // Start of for each loop.
			
			if (row.getRowNum() >= 1) { // Start of first if condition.
				
					System.out.println("Current Row Number: " + row.getRowNum());
					
				Cell currentUsernameE  =  row.getCell(0);
				Cell currentPasswordE  =  row.getCell(1);
				Cell currentNickNameE  =  row.getCell(2);
				Cell expectedResultE   =  row.getCell(3);
					 
					String currentUsername  =  currentUsernameE == null ? "" : currentUsernameE.toString(); // First cell in the row.
					String currentPassword  =  currentPasswordE == null ? "" : currentPasswordE.toString(); // Second cell in the row.
					String currentNickName  =  currentNickNameE == null ? "" : currentNickNameE.toString(); // Third cell in the row.
					String expectedResult   =  expectedResultE  == null ? "" : expectedResultE.toString();  // Forth cell in the row.
					
					System.out.println("User Information From Excel Sheet:");
					System.out.println("Google User Name:   " + currentUsername);
					System.out.println("Google Password:    " + currentPassword);
					System.out.println("Google Nick Name:   " + currentNickName);
					System.out.println("Test Case Expected Result: " + expectedResult);
					
					
						// Condition = Username: empty + Password: empty + Expected Result: Test Failed.
						if (currentUsername.equals("") && currentPassword.equals("") && expectedResult.equals("Test Failed")) {
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Blank"  );
							System.out.println("Password: Blank"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("google-plus-btn")).click();
							driver.switchTo().window("authorize_google");
							driver.findElement(By.id("Email")).sendKeys(currentUsername);
							driver.findElement(By.id("next")).submit();
							Thread.sleep(1000L);
							
							assertEquals("Please enter your email.", driver.findElement(By.id("errormsg_0_Email")).getText());
							
							Thread.sleep(5000L);
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username and password was empty so the user should not be able to login to the application.");
							
							driver.quit();
					
						// Condition = Username: empty + Password: provided + Expected Result: Test Failed.	
						} else if (currentUsername.equals("") && currentPassword.length() > 0 && expectedResult.equals("Test Failed")) {
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Blank"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("google-plus-btn")).click();
							driver.switchTo().window("authorize_google");
							driver.findElement(By.id("Email")).sendKeys(currentUsername);
							driver.findElement(By.id("next")).submit();
							Thread.sleep(1000L);
							
							assertEquals("Please enter your email.", driver.findElement(By.id("errormsg_0_Email")).getText());
							
							Thread.sleep(5000);
							
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username was empty so the user should not be able to login to the application.");
							
							driver.quit();
							
						// Condition = Username: provided + Password: empty + Expected Result: Test Failed.	
						} else if (currentUsername.length() > 0 && currentPassword.equals("") && expectedResult.equals("Test Failed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Blank"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("google-plus-btn")).click();
							driver.switchTo().window("authorize_google");
							driver.findElement(By.id("Email")).sendKeys(currentUsername);
							driver.findElement(By.id("next")).submit();
							driver.findElement(By.id("Passwd")).sendKeys(currentPassword);
							driver.findElement(By.id("signIn")).submit();
							Thread.sleep(1000L);
							
							assertEquals("Please enter your password.", driver.findElement(By.id("errormsg_0_Passwd")).getText());
					
							Thread.sleep(5000);
												    
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The password was empty so the user should not be able to login to the application.");
							
							driver.quit();
							
						// Condition = Username: provided + Password: provided + Expected Result: Test Failed.	
						} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Failed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Failed");
							System.out.println("Reason: The username and password are not correct so the user should not be able to login to the application.");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("google-plus-btn")).click();
							driver.switchTo().window("authorize_google");
							driver.findElement(By.id("Email")).sendKeys(currentUsername);
							Thread.sleep(2000L);
							driver.findElement(By.id("next")).submit();
							Thread.sleep(2000L);
							
							String userIdError = null;
							
							try {
								
								String emailError = driver.findElement(By.id("errormsg_0_Email")).getText();
								System.out.println(emailError);
								userIdError = emailError;
							} catch (Exception e) {
								System.out.println("Username found.");
								e.printStackTrace();
							}
							
							if (userIdError.equals("Sorry, Google doesn't recognize that email.")) {
								
								assertEquals("Sorry, Google doesn't recognize that email.", driver.findElement(By.id("errormsg_0_Email")).getText());
								
								
							} else {
								
								driver.findElement(By.id("Passwd")).sendKeys(currentPassword);
								driver.findElement(By.id("signIn")).submit();
								Thread.sleep(1000L);

							}
							
							Thread.sleep(5000);
						
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							
							driver.quit();
						
						// Condition = Username: provided + Password: provided + Expected Result: Test Passed + Logout: True.		
						} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Passed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Passed");
							
							WebDriver driver = new ChromeDriver();
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							Thread.sleep(1000L);
							String windowHandler = driver.getWindowHandle();
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("google-plus-btn")).click();
							Thread.sleep(6000L);
							driver.switchTo().window("authorize_google");
							driver.findElement(By.id("Email")).sendKeys(currentUsername);
							Thread.sleep(3000L);
							driver.findElement(By.id("next")).submit();
							Thread.sleep(3000L);
							driver.findElement(By.id("Passwd")).sendKeys(currentPassword);
							Thread.sleep(3000L);
							driver.findElement(By.id("signIn")).submit();
							Thread.sleep(8000);
							driver.findElement(By.id("submit_approve_access")).click();
							Thread.sleep(2000L);
							driver.switchTo().window(windowHandler);
							
							String nickNameInternal = currentNickName;
							String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							
							assertEquals(nickNameInternal, nickNameExternal);
							
							driver.findElement(By.linkText(currentNickName.toString())).click();
							driver.findElement(By.linkText("Sign Out")).click();

							Thread.sleep(5000L);
							row.createCell(4).setCellValue("Test Passed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username and password are correct so the user should  be able to login/logout the application.");
							driver.quit();
							
						}
						
						else {
							
							System.out.println("There was an exception. Please check your " + "\"Test Case Nature\"" + " in excel sheet.");
							row.createCell(4).setCellValue("Check Test case");
						} // End of second if condition.
					
					
			} else {
				
				    System.out.println("Google: Header Row Skipped ...");

			} // End of first if condition.
		
		
	}// For each loop completion.
	
	
	inputStream.close();
	System.out.println("File Reading Completed !!!");
	
	FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
	workbook.write(outputStream);
	System.out.println("File Writing Completed !!!");
	
	workbook.close();
	outputStream.close();
	System.out.println("Test Completed !!!");

		
		
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Test
	public void instagramLoginTest() throws Exception{ // This test will check all login test cases with respect to instagram.
		
		// File path to excel sheet which contains username and passwords of sites.
		String excelFilePath = "C:\\Users\\noman\\Documents\\workspace-sts-3.8.2.RELEASE\\streamkar_testing\\src\\main\\resources\\login_successful.xls";
				
		// Input Stream for excel file.
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		// Creating Workbook.
		Workbook workbook = new HSSFWorkbook(inputStream);
		
		// Reading Sheet From Workbook.
		Sheet instagram = workbook.getSheet("instagram");
		
		//Chrome Web Driver Setup
		ChromeOptions options = new ChromeOptions();
		              options.addArguments("--start-maximized");
		
		// File path to excel sheet which contains username and passwords of sites.	
		File fileChrome = new File(getClass().getClassLoader().getResource("chromedriver.exe").getFile());
		String chromeDriverFilePath = fileChrome.getAbsolutePath();
		      		
		//System Property for driver path.
		System.setProperty("webdriver.chrome.driver", chromeDriverFilePath);
		
		// File Paths
		System.out.println("Excel File Path: " + excelFilePath);
		System.out.println("Chrome Driver File Path: " + chromeDriverFilePath);

		
		  for (Row row : instagram) { // Start of for each loop.
			
			if (row.getRowNum() >= 1) { // Start of first if condition.
				
					System.out.println("Current Row Number: " + row.getRowNum());
					
				Cell currentUsernameE  =  row.getCell(0);
				Cell currentPasswordE  =  row.getCell(1);
				Cell currentNickNameE  =  row.getCell(2);
				Cell expectedResultE   =  row.getCell(3);
					 
					String currentUsername  =  currentUsernameE == null ? "" : currentUsernameE.toString(); // First cell in the row.
					String currentPassword  =  currentPasswordE == null ? "" : currentPasswordE.toString(); // Second cell in the row.
					String currentNickName  =  currentNickNameE == null ? "" : currentNickNameE.toString(); // Third cell in the row.
					String expectedResult   =  expectedResultE  == null ? "" : expectedResultE.toString();  // Forth cell in the row.
					
					System.out.println("User Information From Excel Sheet:");
					System.out.println("Instagram User Name:   " + currentUsername);
					System.out.println("Instagram Password:    " + currentPassword);
					System.out.println("Instagram Nick Name:   " + currentNickName);
					
					
						// Condition = Username: empty + Password: empty + Expected Result: Test Failed.
						if (currentUsername.equals("") && currentPassword.equals("") && expectedResult.equals("Test Failed")) {
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Blank"  );
							System.out.println("Password: Blank"  );
							System.out.println("Test case expected result: " + expectedResult);
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							String windowHandler = driver.getWindowHandle();
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("instagram-btn")).click();
							driver.switchTo().window("authorize_instagram");
							driver.findElement(By.id("id_username")).sendKeys(currentUsername);
							driver.findElement(By.id("id_password")).sendKeys(currentPassword);
							driver.findElement(By.cssSelector(".button-green")).click();
							Thread.sleep(2000L);
							driver.switchTo().window(windowHandler);
							Thread.sleep(2000L);
							
							String nickNameInternal = currentNickName.toString();
							String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
						    
							assertTrue(nickNameInternal != nickNameExternal);
							
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username and password was empty so the user should not be able to login to the application.");
							
							driver.quit();
					
						// Condition = Username: empty + Password: provided + Expected Result: Test Failed.	
						} else if (currentUsername.equals("") && currentPassword.length() > 0 && expectedResult.equals("Test Failed")) {
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Blank"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							String windowHandler = driver.getWindowHandle();
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("instagram-btn")).click();
							driver.switchTo().window("authorize_instagram");
							driver.findElement(By.id("id_username")).sendKeys(currentUsername);
							driver.findElement(By.id("id_password")).sendKeys(currentPassword);
							driver.findElement(By.cssSelector(".button-green")).click();
							Thread.sleep(2000L);
							driver.switchTo().window(windowHandler);
							Thread.sleep(2000L);
							
							String nickNameInternal = currentNickName.toString();
							String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
						    
							assertTrue(nickNameInternal != nickNameExternal);
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username was empty so the user should not be able to login to the application.");
							
							driver.quit();
							
						// Condition = Username: provided + Password: empty + Expected Result: Test Failed.	
						} else if (currentUsername.length() > 0 && currentPassword.equals("") && expectedResult.equals("Test Failed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Blank"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							String windowHandler = driver.getWindowHandle();
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("instagram-btn")).click();
							driver.switchTo().window("authorize_instagram");
							driver.findElement(By.id("id_username")).sendKeys(currentUsername.toString());
							driver.findElement(By.id("id_password")).sendKeys(currentPassword.toString());
							driver.findElement(By.cssSelector(".button-green")).click();
							Thread.sleep(2000L);
							driver.switchTo().window(windowHandler);
							Thread.sleep(2000L);
							
							
							String nickNameInternal = currentNickName.toString();
							String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
						    
							assertTrue(nickNameInternal != nickNameExternal);
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The password was empty so the user should not be able to login to the application.");
							
							driver.quit();
							
						// Condition = Username: provided + Password: provided + Expected Result: Test Failed.	
						} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Failed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Failed");
							System.out.println("Reason: The username and password are not correct so the user should not be able to login to the application.");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("instagram-btn")).click();
							driver.switchTo().window("authorize_instagram");
							driver.findElement(By.id("id_username")).sendKeys(currentUsername.toString());
							driver.findElement(By.id("id_password")).sendKeys(currentPassword.toString());
							driver.findElement(By.cssSelector(".button-green")).click();
							Thread.sleep(2000L);
					
							assertEquals("Please enter a correct username and password. Note that both fields are case-sensitive.", driver.findElement(By.xpath("/html/body/div/section/div/div/div/p")).getText());
		
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							
							driver.quit();
						
						// Condition = Username: provided + Password: provided + Expected Result: Test Passed + Logout: True.		
						} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Passed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Passed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							String windowHandler = driver.getWindowHandle();
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("instagram-btn")).click();
							driver.switchTo().window("authorize_instagram");
							driver.findElement(By.id("id_username")).sendKeys(currentUsername.toString());
							driver.findElement(By.id("id_password")).sendKeys(currentPassword.toString());
							driver.findElement(By.cssSelector(".button-green")).click();
							Thread.sleep(2000L);
							driver.switchTo().window(windowHandler);
							Thread.sleep(2000L);
							
							String nickNameInternal = currentNickName.toString();
							String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							
							assertEquals(nickNameInternal, nickNameExternal);
							
							driver.findElement(By.linkText(currentNickName.toString())).click();
							driver.findElement(By.linkText("Sign Out")).click();

							Thread.sleep(5000L);
							row.createCell(4).setCellValue("Test Passed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username and password are correct so the user should  be able to login/logout the application.");
							driver.quit();
							
						}
						
						else {
							
							System.out.println("There was an exception. Please check your " + "\"Test Case Nature\"" + " in excel sheet.");
							row.createCell(4).setCellValue("Check Test case");
						} // End of second if condition.
					
					
			} else {
				
				    System.out.println("Instagram: Header Row Skipped ...");

			} // End of first if condition.
		
		
	}// For each loop completion.
	
	
	inputStream.close();
	System.out.println("File Reading Completed !!!");
	
	FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
	workbook.write(outputStream);
	System.out.println("File Writing Completed !!!");
	
	workbook.close();
	outputStream.close();
	System.out.println("Test Completed !!!");

		
		
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	@Test
	public void streamkarLoginTest() throws Exception{ // This test will check all login test cases with respect to streamkar.
		
		// File path to excel sheet which contains username and passwords of sites.
		String excelFilePath = "C:\\Users\\noman\\Documents\\workspace-sts-3.8.2.RELEASE\\streamkar_testing\\src\\main\\resources\\login_successful.xls";
		
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		Workbook workbook = new HSSFWorkbook(inputStream);
		
		Sheet streamkar = workbook.getSheet("streamkar");
		
		ChromeOptions options = new ChromeOptions();
		              options.addArguments("--start-maximized");
		          
		// File path to excel sheet which contains username and passwords of sites.	
		File fileChrome = new File(getClass().getClassLoader().getResource("chromedriver.exe").getFile());
		String chromeDriverFilePath = fileChrome.getAbsolutePath();
		
		System.out.println("Excel File Path: " + excelFilePath);
		System.out.println("Chrome Driver File Path: " + chromeDriverFilePath);

		
		  for (Row row : streamkar) { // Start of for each loop.
			
			if (row.getRowNum() >= 1) { // Start of first if condition.
				
					System.out.println("Current Row Number: " + row.getRowNum());
					
				Cell currentUsernameE  =  row.getCell(0);
				Cell currentPasswordE  =  row.getCell(1);
				Cell currentNickNameE  =  row.getCell(2);
				Cell expectedResultE   =  row.getCell(3);
					 
					String currentUsername  =  currentUsernameE == null ? "" : currentUsernameE.toString(); // First cell in the row.
					String currentPassword  =  currentPasswordE == null ? "" : currentPasswordE.toString(); // Second cell in the row.
					String currentNickName  =  currentNickNameE == null ? "" : currentNickNameE.toString(); // Third cell in the row.
					String expectedResult   =  expectedResultE  == null ? "" : expectedResultE.toString();  // Forth cell in the row.
					
					System.out.println("User Information From Excel Sheet:");
					System.out.println("Streamkar User Name:   " + currentUsername);
					System.out.println("Streamkar Password:    " + currentPassword);
					System.out.println("Stream Nick Name:   " + currentNickName);
					
					
						// Condition = Username: empty + Password: empty + Expected Result: Test Failed.
						if (currentUsername.equals("") && currentPassword.equals("") && expectedResult.equals("Test Failed")) {
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Blank"  );
							System.out.println("Password: Blank"  );
							System.out.println("Test case expected result: " + expectedResult);
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("login_account")).clear();
							driver.findElement(By.id("login_account")).sendKeys(currentUsername);
							driver.findElement(By.id("login_pwd")).clear();
							driver.findElement(By.id("login_pwd")).sendKeys(currentPassword);
							driver.findElement(By.id("login_submit")).click();
							Thread.sleep(3000L);
							
							
							assertEquals("Required input is empty!", driver.findElement(By.id("login_msg")).getText());
							
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username and password was empty so the user should not be able to login to the application.");
							
							driver.quit();
					
						// Condition = Username: empty + Password: provided + Expected Result: Test Failed.	
						} else if (currentUsername.equals("") && currentPassword.length() > 0 && expectedResult.equals("Test Failed")) {
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Blank"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("login_account")).clear();
							driver.findElement(By.id("login_account")).sendKeys(currentUsername);
							driver.findElement(By.id("login_pwd")).clear();
							driver.findElement(By.id("login_pwd")).sendKeys(currentPassword);
							driver.findElement(By.id("login_submit")).click();
							Thread.sleep(3000L);
							
							assertEquals("Required input is empty!", driver.findElement(By.id("login_msg")).getText());
						
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username was empty so the user should not be able to login to the application.");
							
							driver.quit();
							
						// Condition = Username: provided + Password: empty + Expected Result: Test Failed.	
						} else if (currentUsername.length() > 0 && currentPassword.equals("") && expectedResult.equals("Test Failed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Blank"  );
							System.out.println("Test case expected result: Test Failed");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("login_account")).clear();
							driver.findElement(By.id("login_account")).sendKeys(currentUsername);
							driver.findElement(By.id("login_pwd")).clear();
							driver.findElement(By.id("login_pwd")).sendKeys(currentPassword);
							driver.findElement(By.id("login_submit")).click();
							Thread.sleep(3000L);
							
							assertEquals("Required input is empty!", driver.findElement(By.id("login_msg")).getText());
							
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The password was empty so the user should not be able to login to the application.");
							
							driver.quit();
							
						// Condition = Username: provided + Password: provided + Expected Result: Test Failed.	
						} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Failed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Failed");
							System.out.println("Reason: The username and password are not correct so the user should not be able to login to the application.");
							
							WebDriver driver = new ChromeDriver(options);
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("login_account")).clear();
							driver.findElement(By.id("login_account")).sendKeys(currentUsername.toString());
							driver.findElement(By.id("login_pwd")).clear();
							driver.findElement(By.id("login_pwd")).sendKeys(currentPassword.toString());
							driver.findElement(By.id("login_submit")).click();
							Thread.sleep(3000L);
							
							assertEquals("Wrong username or password!", driver.findElement(By.id("login_msg")).getText());
							
							row.createCell(4).setCellValue("Test Failed");
							System.out.println("Test Status: Test Passed !!!");
							
							driver.quit();
						
						// Condition = Username: provided + Password: provided + Expected Result: Test Passed + Logout: True.		
						} else if (currentUsername.length() > 0 && currentPassword.length() > 0 && expectedResult.equals("Test Passed")){
							
							System.out.println("Test case Evaluation:");
							System.out.println("Username: Provided"  );
							System.out.println("Password: Provided"  );
							System.out.println("Test case expected result: Test Passed");
							
							WebDriver driver = new ChromeDriver(options);
							
							driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
							driver.get("http://www.streamkar.com/");
							driver.findElement(By.cssSelector("li.sign-up-btn > a")).click();
							driver.findElement(By.id("login_account")).clear();
							driver.findElement(By.id("login_account")).sendKeys(currentUsername.toString());
							driver.findElement(By.id("login_pwd")).clear();
							driver.findElement(By.id("login_pwd")).sendKeys(currentPassword.toString());
							driver.findElement(By.id("login_submit")).click();
							Thread.sleep(3000L);
							
							String nickNameInternal = currentNickName.toString();
							String nickNameExternal = driver.findElement(By.xpath("/html/body/nav/div[2]/div[2]/ul/li[7]/div/a/div/span")).getText();
							
							assertEquals(nickNameInternal, nickNameExternal);
							
							driver.findElement(By.linkText(currentNickName.toString())).click();
							driver.findElement(By.linkText("Sign Out")).click();

							Thread.sleep(5000L);
							row.createCell(4).setCellValue("Test Passed");
							System.out.println("Test Status: Test Passed !!!");
							System.out.println("Reason: The username and password are correct so the user should  be able to login/logout the application.");
							driver.quit();
							
						}
						
						else {
							
							System.out.println("There was an exception. Please check your " + "\"Test Case Nature\"" + " in excel sheet.");
							row.createCell(4).setCellValue("Check Test case");
						} // End of second if condition.
					
					
			} else {
				
				    System.out.println("Streamkar: Header Row Skipped ...");

			} // End of first if condition.
		
		
	}// For each loop completion.
	
	
	inputStream.close();
	System.out.println("File Reading Completed !!!");
	
	FileOutputStream outputStream = new FileOutputStream(new File(excelFilePath));
	workbook.write(outputStream);
	System.out.println("File Writing Completed !!!");
	
	workbook.close();
	outputStream.close();
	System.out.println("Test Completed !!!");

		
		
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

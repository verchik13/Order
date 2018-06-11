package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Order {
	static int a;
	public static String getRandomString() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	protected static int getRandomZip() {

		return (int) (Math.random() * 9 + 1) * 10000;
	}

	protected static String getRandomCard() {
		Random rd = new Random();
		String[] CardType = { "ctl00_MainContent_fmwOrder_cardList_0", "ctl00_MainContent_fmwOrder_cardList_1",
				"ctl00_MainContent_fmwOrder_cardList_2" };
		a=rd.nextInt(3);
		return CardType[a];

	}
	public static String getCartNumber() {
		Random rd = new Random();
		String cart = "";
		
		switch (a) {
		case 0:
			cart += 4;
			break;
		case 1:
			cart += 5;
			break;
		case 2:
			cart += 3;
			break;
		default:
			break;
		}
		if (a == 0 || a == 1) {
			for (int i = 0; i < 15; i++) {
				cart += rd.nextInt(10);
			}
		} else {
			for (int i = 0; i < 14; i++) {
				cart += rd.nextInt(10);
			}
		}

		return cart;
	}

		public static void main(String[] args) throws InterruptedException {
			Random r1= new Random();
			int num=r1.nextInt(100)+1;
			
			System.setProperty("webdriver.chrome.driver", 
					"/Users/verachimitova/Documents/selenium dependencies/drivers/chromedriver");	
			
			WebDriver driver = new ChromeDriver();
			driver.navigate().to("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
			//findElement  --> finds element on a page
			//by.name("userName") --> how to find it
			//sendKeys() --> types on element
			Thread.sleep(2000);
			
			driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
			driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
			driver.findElement(By.name("ctl00$MainContent$login_button")).click();
			driver.findElement( By.cssSelector("a[href='Process.aspx']")).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(""+num);
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John "+getRandomString()+" Smith");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("7925 Jones Branch Dr");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Tyson");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Virginia");
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(""+getRandomZip());
			driver.findElement(By.id(getRandomCard())).click();
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(""+getCartNumber());
			driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys( "08/19");
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();


			String expected = "New order has been successfully added.";
			String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText();
			
			if ( actual.contains(expected) ) {
				System.out.println("pass");
			} else {
				System.out.println("fail");
				System.out.println("Expected:\t" + expected);
				System.out.println("Actual:\t" + actual);
			}
			
			
		}

	

		}



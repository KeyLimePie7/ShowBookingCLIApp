package com.keylimepie7.bookingapp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;

import com.keylimepie7.bookingapp.commands.ApplicationCommands;

//Since this is a shell application, you need to disable the interactive shell here, otherwise tests will not run
@SpringBootTest(properties = "spring.shell.interactive.enabled=false") 
class BookingappApplicationTests {
	
	ApplicationCommands applicationCommands = new ApplicationCommands();

	@Test
	void contextLoads() {
	}
	
	@Test
    public void simpleTest() {
        Assertions.assertTrue(true);
    }
	
	@Test
    public void testTesting() {
        String returnedString = applicationCommands.testing();
        Assertions.assertTrue(returnedString.equals("helloThere"));
    }
	
	@Test
    public void testTesting2() {
        String returnedString = applicationCommands.testing2("hi");
        Assertions.assertTrue(returnedString.equals("helloThere " + "hi"));
    }
	
	@Test
    public void testSetAdmin() {
        String returnedString = applicationCommands.setAdmin();
        Assertions.assertTrue(returnedString.equals("Logged in as Admin"));
        Assertions.assertTrue(applicationCommands.isAdmin == true);
        Assertions.assertTrue(applicationCommands.isBuyer == false);
    }
	
	@Test
    public void testSetBuyer() {
        String returnedString = applicationCommands.setBuyer();
        Assertions.assertTrue(returnedString.equals("Logged in as Buyer"));
        Assertions.assertTrue(applicationCommands.isAdmin == false);
        Assertions.assertTrue(applicationCommands.isBuyer == true);
    }
	
	@Test
    public void testCheckAccount() {
        String returnedString = applicationCommands.checkAccount();
        Assertions.assertTrue(returnedString.equals("Logged in as admin"));
    }
	
	@Test
    public void testSetupShow() {
        String returnedString = applicationCommands.setupShow("1",1,1,2);
        Assertions.assertTrue(applicationCommands.showList.containsKey("1"));
        Assertions.assertTrue(returnedString.equals("Successfully Added a New Show"));
    }
	
	@Test
    public void testAvailability() {
        applicationCommands.setupShow("1",1,1,2);
        applicationCommands.setBuyer();
        String returnedString = applicationCommands.availability("1");
        Assertions.assertTrue(applicationCommands.showList.containsKey("1"));
        Assertions.assertTrue(returnedString.equals("For Show Number 1 - Available seats: " + applicationCommands.showList.get("1").getAvailableSeats().toString()));
    }

	@Test
    public void testBook() {
		applicationCommands.setupShow("1",1,1,2);
		applicationCommands.setBuyer();
        String returnedString = applicationCommands.book("2",123,"A1");
        Assertions.assertTrue(returnedString.equals("This show does not exist"));
    }
}

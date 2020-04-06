package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import app.model.Account;

class AccountTest {

	@Test
	void should_create_single_account() {
		// arrange
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = this.getClass().getClassLoader();
		Account testAccount = null;
		try (Reader reader = new FileReader(classLoader.getResource("single_test.json").getFile())) {
			JSONObject parsedFile = (JSONObject) parser.parse(reader);
			testAccount = new Account(parsedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String expectedOutput = "1 Margalo Trobey mtrobey0@cocolog-nifty.com";
		// act
		String actualOutput = testAccount.toString();
		// assert
		assertEquals(expectedOutput, actualOutput);
	}

}

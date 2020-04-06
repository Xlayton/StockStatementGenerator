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
	/**
	 * This test is used to check all the basic information of the user, the Trades
	 * should be checked using the Trade class tester.
	 */
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
		String expectedOutput = "1 Margalo Trobey mtrobey0@cocolog-nifty.com 654-711-3196";
		// act
		String actualOutput = testAccount.toString();
		// assert
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	/**
	 * This test is essentially serving to only check the end balance of the user
	 */
	void should_get_correct_end_balance() {
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
		double expected = 3526949.14;
		// act
		double actual = testAccount.getEndingBalance();
		// assert
		assertEquals(expected, actual);
	}

	@Test
	/**
	 * This test checks for correct stock holdings balance
	 */
	void should_get_correct_stock_holdings() {
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
		double expected = 4153726.25;
		// act
		double actual = testAccount.getStockHoldings();
		// assert
		assertEquals(expected, actual);
	}

}

package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;

import app.model.Trade;

class TradeTest {

	@Test
	/**
	 * This test tests the information given by the JSON
	 */
	void should_create_single_trade() {
		// arrange
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = this.getClass().getClassLoader();
		Trade testTrade = null;
		try (Reader reader = new FileReader(classLoader.getResource("trade_test.json").getFile())) {
			JSONObject parsedFile = (JSONObject) parser.parse(reader);
			testTrade = new Trade(parsedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String expectedOutput = "CTBB SELL 7866 359.4";
		// act
		String actualOutput = testTrade.toString();
		// assert
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	void should_get_correct_total_amount() {
		//arrange
		JSONParser parser = new JSONParser();
		ClassLoader classLoader = this.getClass().getClassLoader();
		Trade testTrade = null;
		try (Reader reader = new FileReader(classLoader.getResource("trade_test.json").getFile())) {
			JSONObject parsedFile = (JSONObject) parser.parse(reader);
			testTrade = new Trade(parsedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		double expected = 2827040.4;
		//act
		double actual = testTrade.getTradeTotal();
		//assert
		System.out.println(actual);
		assertEquals(expected, actual);
	}
}

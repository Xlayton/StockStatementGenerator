package app.util;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import app.model.Account;
import app.model.Trade;

public class LoadUtil {
	public static ArrayList<Account> loadAccountsFromFilePath(String filePath) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		ClassLoader classLoader = LoadUtil.class.getClassLoader();
		JSONParser parser = new JSONParser();
		Trade testTrade = null;
		try (Reader reader = new FileReader(filePath)) {
			JSONArray parsedFile = (JSONArray) parser.parse(reader);
			for(Object obj : parsedFile) {
				JSONObject parsedObject = (JSONObject) obj;
				accounts.add(new Account(parsedObject));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
}

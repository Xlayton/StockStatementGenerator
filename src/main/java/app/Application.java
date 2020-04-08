package app;

import java.util.ArrayList;

import app.model.Account;
import app.util.FormUtil;
import app.util.LoadUtil;

public class Application {
	public static void main(String[] args) {
		String fileOutPath = "<INSERT OUTPUT FOLDER PATH>";
		String fileInPath = "<INSERT INPUT JSON PATH>";
		ArrayList<Account> accounts = LoadUtil.loadAccountsFromFilePath(fileInPath);
		try {
			FormUtil.createFormsFromAccounts(accounts, fileOutPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

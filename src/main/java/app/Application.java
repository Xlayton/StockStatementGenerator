package app;

import java.util.ArrayList;

import app.model.Account;
import app.util.FormUtil;
import app.util.LoadUtil;

public class Application {
	public static void main(String[] args) {
		String fileOutPath = "C:\\Users\\KongF\\Desktop\\grading\\output";
		String fileInPath = "C:\\Users\\KongF\\Desktop\\grading\\all_accounts.json";
		ArrayList<Account> accounts = LoadUtil.loadAccountsFromFilePath(fileInPath);
		try {
			FormUtil.createFormsFromAccounts(accounts, fileOutPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}

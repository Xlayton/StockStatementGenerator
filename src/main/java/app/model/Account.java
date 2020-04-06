package app.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Precision;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Account {
	private int accountNumber;
	private double begginingBalance;
	private double endingBalance;
	private double stockHoldings;
	
	private String ssn;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	private Trade[] stockTrades;
	
	public Account(JSONObject rawData) {
		this.accountNumber = ((Long) rawData.get("account_number")).intValue();
		this.begginingBalance = Double.parseDouble(((String)rawData.get("beginning_balance")).substring(1));
		this.endingBalance = begginingBalance;
		this.stockHoldings = 0;
		
		this.ssn = (String) rawData.get("ssn");
		this.firstName = (String) rawData.get("first_name");
		this.lastName = (String) rawData.get("last_name");
		this.email = (String) rawData.get("email");
		this.phone = (String) rawData.get("phone");
		
		List<Trade> trades = new ArrayList<Trade>();
		JSONArray rawTrades = (JSONArray) rawData.get("stock_trades");
		rawTrades.forEach((trade) -> trades.add(new Trade((JSONObject)trade)));
		this.stockTrades = trades.toArray(new Trade[0]);
		
		for(Trade t : stockTrades) {
			switch(t.getType()) {
			case BUY:
				this.endingBalance -= t.getTradeTotal();
				this.stockHoldings += t.getTradeTotal();
				break;
			case SELL:
				this.endingBalance += t.getTradeTotal();
				this.stockHoldings -= t.getTradeTotal();
				break;
			default:
				throw new IllegalArgumentException("That isn't a trade type. Something went catastropically wrong.");
			}
		}
		if(this.stockHoldings < 0) {
			this.stockHoldings = 0;
		}
		this.endingBalance = Precision.round(endingBalance, 2);
		this.stockHoldings = Precision.round(stockHoldings, 2);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getBegginingBalance() {
		return begginingBalance;
	}

	public double getEndingBalance() {
		return endingBalance;
	}
	
	public double getStockHoldings() {
		return stockHoldings;
	}

	public String getSsn() {
		return ssn;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public Trade[] getStockTrades() {
		return stockTrades;
	}
	
	@Override
	public String toString() {
		return ""+ this.accountNumber + " " + this.firstName + " " + this.lastName + " " + this.email + " " + this.phone;
	}
	
}

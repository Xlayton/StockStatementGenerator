package app.model;

import org.apache.commons.math3.util.Precision;
import org.json.simple.JSONObject;

public class Trade {
	private int shareCount;
	private double pricePerShare;
	private double tradeTotal;
	
	private String stockSymbol;
	
	private TradeType type;
	
	public Trade(JSONObject rawData) {
		this.shareCount = ((Long) rawData.get("count_shares")).intValue();
		this.pricePerShare = Double.parseDouble(((String) rawData.get("price_per_share")).substring(1));
		this.tradeTotal = Precision.round(pricePerShare * shareCount, 2);
		
		this.stockSymbol = (String) rawData.get("stock_symbol");
		
		this.type = ((String) rawData.get("type")).equals("Buy") ? TradeType.BUY : TradeType.SELL;
	}

	public int getShareCount() {
		return shareCount;
	}

	public double getPricePerShare() {
		return pricePerShare;
	}

	public double getTradeTotal() {
		return tradeTotal;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public TradeType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "" + this.stockSymbol + " " + this.type + " " + this.shareCount + " " + this.pricePerShare;
	}
	
	
}

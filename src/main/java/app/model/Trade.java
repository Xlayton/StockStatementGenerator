package app.model;

import org.json.simple.JSONObject;

public class Trade {
	private int shareCount;
	private float pricePerShare;
	private float tradeTotal;
	
	private String stockSymbol;
	
	private TradeType type;
	
	public Trade(JSONObject rawData) {
		this.shareCount = ((Long) rawData.get("count_shares")).intValue();
		this.pricePerShare = Float.parseFloat(((String) rawData.get("price_per_share")).substring(1));
		this.tradeTotal = pricePerShare * shareCount;
		
		this.stockSymbol = (String) rawData.get("stock_symbol");
		
		this.type = ((String) rawData.get("type")).equals("Buy") ? TradeType.BUY : TradeType.SELL;
	}

	public int getShareCount() {
		return shareCount;
	}

	public float getPricePerShare() {
		return pricePerShare;
	}

	public float getTradeTotal() {
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
		return "" + this.shareCount + " " + this.pricePerShare + " " + this.tradeTotal;
	}
	
	
}

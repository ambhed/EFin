import com.fasterxml.jackson.annotation.JsonProperty;

public class USDobj {
	  @JsonProperty("USD")
	  public Double usdValue;

	public Double getUsdValue() {
		return usdValue;
	}

	public void setUsdValue(Double usdValue) {
		this.usdValue = usdValue;
	}
	}




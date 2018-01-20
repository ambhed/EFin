import com.fasterxml.jackson.annotation.JsonProperty;

public class Ethobj {
	  @JsonProperty("ETH")
	  public Double ethValue;

	public Double getEthValue() {
		return ethValue;
	}

	public void setEthValue(Double ethValue) {
		this.ethValue = ethValue;
	}
	}




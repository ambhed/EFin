

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpURLConnections {

	private static final String USER_AGENT = "Mozilla/5.0";

	private static final String GET_USD_URL = "https://min-api.cryptocompare.com/data/price?fsym=ETH&tsyms=USD&e=Gdax";
	
	public double getValues(String GET_URL,Double usd){
		Double ans=0.0;
		Double eth=0.0;
		try {
			eth = sendGETETH(GET_URL);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		ans =eth*usd;
		return ans;
	}

	private  double sendGETETH(String GET_URL) throws IOException {
		URL obj = new URL(GET_URL);
		Double value=0.0;
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			ObjectMapper mapper = new ObjectMapper();
			Ethobj eth = mapper.readValue(response.toString(), Ethobj.class);
			value=eth.getEthValue();
					} else {
			System.out.println("GET request not worked");
		}
		return value;
	}
	double sendGETETHUSD() throws IOException {
		URL obj = new URL(GET_USD_URL);
		Double value=0.0;
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			ObjectMapper mapper = new ObjectMapper();
			USDobj usd = mapper.readValue(response.toString(), USDobj.class);
			value=usd.getUsdValue();
					} else {
			System.out.println("GET request not worked");
		}
		return value;

	}
	Double calculateDiff( ArrayList<Calculation> calcList) throws IOException {
		Double value=0.0;
		BigDecimal firstValue;
		BigDecimal lastValue;
		double firstValueD;
		double lastValueD;
		double afPer =0.0;
		boolean check=true;
/*		 for (int i = 0; i < calcList.size(); i++) {
			 if(i!=0){
				 Calculation c = calcList.get(i);
				 Calculation b = calcList.get(i-1);
				 if(c.getValue().compareTo(b.getValue())<= 0 && check){
					 check = check; 
				 }else{
					 check = false;
				 }
			 }
		}*/
//		if(check){
		Calculation f = calcList.get(calcList.size() - 1);
		Calculation c = calcList.get(0);
			firstValue = f.getValue();
			lastValue = c.getValue();
			firstValueD = firstValue.setScale(4, RoundingMode.CEILING).doubleValue();
			lastValueD = lastValue.setScale(4, RoundingMode.CEILING).doubleValue();
			double befPer=lastValueD-firstValueD;
			 afPer = befPer/firstValueD;
			 afPer=afPer*100;
//		 }
		return afPer;
	}
	
	
	
	public static void 	validateForAlert(String coin, double perIncreased, int timevalue){

// time = 5 min and increased >5 
// time = 5 min and decreased >5		
// time = 1 min and increased >2
// time = 1 min and decreased >2		
		Email2SMS smsSend = new Email2SMS();
		boolean sms=false;
		if(timevalue==5){
			if(perIncreased>5){
				sms=true;
			}else if(perIncreased<-5){
				sms=true;
			}
		}else{
			if(perIncreased>2){
				sms=true;
			}else if(perIncreased<-2){
				sms=true;
			}
		}
		if(sms){
			System.out.println("Sending SMS-"+coin+":"+perIncreased+":"+timevalue);
		//smsSend.email2SMS(coin, perIncreased, timevalue);
		}
		
	}
	
	
	
}
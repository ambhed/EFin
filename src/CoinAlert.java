import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CoinAlert {
	  public static void main(String[] args)throws Exception
	  {
		  try {
				 Timer timer = new Timer();
				 timer.schedule(new Thread1(0), 0, 60000);
			
		  }
		  catch (Exception e) {
				e.printStackTrace();
			}
		  }	
}
class Thread1 extends TimerTask {
	 private int timevalue=0;
	 public Thread1(int i) {
		 timevalue=i;
	}
	public void run() {				   
		  try {
			
			  String coin = "LTC";
			  int myVal = 204;
			  Double usd=0.0;
			  Email2SMS smsSend = new Email2SMS();
			usd=sendGETETHUSD();
			if(usd>=myVal){
				smsSend.email2SMS(coin, usd, timevalue);
				System.exit(0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static double sendGETETHUSD() throws IOException {
		  String GET_USD_URL = "https://min-api.cryptocompare.com/data/price?fsym=LTC&tsyms=USD&e=Gdax";
		  String USER_AGENT = "Mozilla/5.0";

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
}

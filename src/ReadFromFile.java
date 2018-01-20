import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;


public class ReadFromFile
{
  public static void main(String[] args)throws Exception
  {
	  try {
			  Timer timer = new Timer();
			  timer.schedule(new Thread(5), 0, 300000);
			  timer.schedule(new Thread(1), 0, 60000);
	  }
	  catch (Exception e) {
			e.printStackTrace();
		}
	  }
}
 class Thread extends TimerTask {
	 private int timevalue=0;
	 public Thread(int i) {
		 timevalue=i;
	}

public void run() {				   
 try {  
	double a =System.currentTimeMillis();
	 HttpURLConnections conn = new HttpURLConnections();
  File file = new File("C:\\Coin\\URL.txt");
  BufferedReader br = new BufferedReader(new FileReader(file));
  String st;
  Double usd=0.0;
  usd=conn.sendGETETHUSD();
  String start="https://min-api.cryptocompare.com/data/price?tsyms=ETH&e=binance&fsym=";
  while ((st = br.readLine()) != null){
	  int n_lines = 3;
	  int counter = 0; 
	  WriteFile wr= new WriteFile();
	  double val=  wr.writeListValues(st,start+st,n_lines,counter,timevalue,usd);
	  
	  conn.validateForAlert(st,val,timevalue);
	  } 
  double b=System.currentTimeMillis();
  double c=b-a;
  System.out.println("compTime:"+System.currentTimeMillis()+",TimeTaken:"+c);
	  } catch (Exception e) {
			e.printStackTrace();
		}
  }



}
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.io.input.ReversedLinesFileReader;

public class WriteFile {

	
	
	public  Double writeListValues(String st,String url,int n_lines,int counter,int stCount, Double usd){
		double val = 0;
		try {
			 HttpURLConnections conn = new HttpURLConnections();
			 FileWriter writer;
			 if(stCount==1){
				 writer = new FileWriter("C:/Coin/dump/"+stCount+"/"+st+".txt", true);
			 }else{
			 writer = new FileWriter("C:/Coin/dump/"+st+".txt", true);
			 }
			  BufferedWriter bufferedWriter = new BufferedWriter(writer);
			  bufferedWriter.write(conn.getValues(url,usd)+":"+ System.currentTimeMillis());
			  bufferedWriter.newLine();
			  bufferedWriter.close();
			  File file1;
			  if(stCount==1){
				  file1 = new File("C:\\Coin\\dump\\"+stCount+"\\"+st+".txt");
				 }else{
					 file1 = new File("C:\\Coin\\dump\\"+st+".txt");
				 }
			  ArrayList<Calculation> calcList = new ArrayList<Calculation>();
			  ReversedLinesFileReader object = new ReversedLinesFileReader(file1);
			  String s1=object.readLine();
			  while(!s1.isEmpty()  && counter < n_lines)
			  {
				BigDecimal date = new BigDecimal(s1.substring(s1.lastIndexOf(":")+1, s1.length()));
				BigDecimal value = new BigDecimal(s1.substring(0,s1.lastIndexOf(":")));		
				Calculation calculation= new Calculation();
				calculation.setDate(date);
				calculation.setValue(value);
				calcList.add(calculation);
				 counter++;
				 s1=object.readLine();
			  }
			   val= conn.calculateDiff(calcList);  
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

}

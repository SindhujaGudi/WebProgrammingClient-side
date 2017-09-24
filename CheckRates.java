package currpack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CheckRates {
	String symbol;
	float bid;
	float ask;
	float high;
	float low;
	int direction;
	String last;
	
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}



	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public void saveValuesToFile()
	{
		FileWriter fwriter;
	    
	    StringBuffer sb; 
	    try 
	    {
	    	sb = new StringBuffer();
	    	String url = "http://rates.fxcm.com/RatesXML";
	    	URL xURL;
	        HttpURLConnection connection = null;
	        xURL = new URL(url);
	        connection = (HttpURLConnection) xURL.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	        connection.setRequestProperty("Content-Language", "en-US");
	        connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11");
	        connection.setUseCaches(false);
	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        InputStreamReader in = new InputStreamReader((InputStream) connection.getInputStream());
	        BufferedReader buff = new BufferedReader(in);
	        String inp_line="";
	        while ((inp_line = buff.readLine()) != null) 
	        {
	        	sb.append(inp_line);
	        }
	        fwriter = new FileWriter(".//output.xml"); 
	        fwriter.write(sb.toString());
	        fwriter.close();
	    } 
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }
	}

	public int getValuesFromFile(String cur_sym, float cur_ask)
	{
		int flag = 0;
		//found="Not Found";
		try 
		{
			File xf = new File(".//output.xml");
			DocumentBuilderFactory dbfact = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbuild = dbfact.newDocumentBuilder();
			Document d = dbuild.parse(xf);
			d.getDocumentElement().normalize();

			NodeList nList = d.getElementsByTagName("Rate");
			for (int x = 0; x < nList.getLength(); x++) 
			{
				Node nNode = nList.item(x);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element element = (Element) nNode;
					if(!element.getAttribute("Symbol").equals(cur_sym))
						flag=2;
					else
					{
						
						if(Float.parseFloat(element.getElementsByTagName("Ask").item(0).getTextContent() ) >=(float) cur_ask)
						{
							setSymbol(element.getAttribute("Symbol"));
							setBid(Float.parseFloat( element.getElementsByTagName("Bid").item(0).getTextContent()));
							setAsk(Float.parseFloat(  element.getElementsByTagName("Ask").item(0).getTextContent()));
							setHigh(Float.parseFloat(  element.getElementsByTagName("High").item(0).getTextContent()));
							setLow(Float.parseFloat(  element.getElementsByTagName("Low").item(0).getTextContent()));
							setDirection(Integer.parseInt(  element.getElementsByTagName("Direction").item(0).getTextContent()));
							setLast(element.getElementsByTagName("Last").item(0).getTextContent());
							//found = "Found";
							flag=1;
							break;
						}
						else
						{
							flag=0;
							break;
						}
					}		
				}
				 
			}
			 
			
	    } 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return flag;
	}


}

package Parsing;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParsingAllCountOfSearch{
	private URL url;
	private URLConnection connection;
	private HttpURLConnection httpConnection;
	int responseCode;
	
	private InputStream in;
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document dom;
	private Element docEle;
	private NodeList nl;
	
	private Element entry;
	private Node node;
	private int total_num;
	
	public void connection(String urlAddress) {
		try {
			url = new URL(urlAddress);
			connection = url.openConnection();
			httpConnection = (HttpURLConnection) connection;
			responseCode = httpConnection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = httpConnection.getInputStream();
				dbf = DocumentBuilderFactory	.newInstance();
				db = dbf.newDocumentBuilder();
				dom = db.parse(in);
				docEle = dom.getDocumentElement();
				nl = docEle.getElementsByTagName("total");

				if ((nl != null) && (nl.getLength() > 0)) {
					for (int i = 0; i < nl.getLength(); i++) {
						entry = (Element) nl.item(i);
						node = entry.getFirstChild();
						total_num = Integer.parseInt(node.getNodeValue());
					}
				}
			}
		} catch (Exception e) {
			connection(urlAddress);
		}
	}
	
	public int getTotalNum(){return total_num;}
}

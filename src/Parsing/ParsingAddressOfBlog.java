package Parsing;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class ParsingAddressOfBlog{
	private ArrayList<String> address_list = new ArrayList<String>();
	
	public void connection(String address){		
		try {
			URL url = new URL(address);
			URLConnection connection;
			connection = url.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			int responseCode = httpConnection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream in = httpConnection.getInputStream();
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document dom = db.parse(in);
				Element docEle = dom.getDocumentElement();
				NodeList nl = docEle.getElementsByTagName("item");

				if ((nl != null) && (nl.getLength() > 0)) {
					for (int i = 0; i < nl.getLength(); i++) {
						analyzeNode(nl, i);
					}// for
				}// if
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void analyzeNode(NodeList nl, int i) {
		try {
			Element entry = (Element) nl.item(i);
			Element link = (Element) entry.getElementsByTagName("link").item(0); // linkÃßÃâ

			address_list.add(link.getFirstChild().getNodeValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> getAddressList() {
		return address_list;
	}

	public void setAddressList(ArrayList<String> address_list) {
		this.address_list = address_list;
	}
}
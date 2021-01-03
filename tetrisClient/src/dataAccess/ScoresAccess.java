package dataAccess;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import domain.RegisterScore;
import domain.Time;

public class ScoresAccess {

	private long highscore;
	private LinkedList<RegisterScore> scores;
	private String fileName;

	public ScoresAccess(String fileName) {
		this.fileName = fileName;
		this.highscore = 0;
		this.scores = new LinkedList<RegisterScore>();
		
		this.readXml(this.fileName);
	}
	
	public long getHighscore(){
		return this.highscore;
	}
	
	public List<RegisterScore> getScores(){
		return this.scores;
	}
	
	public void addScore(RegisterScore score){
		if(this.highscore < score.getScore()) {
			this.highscore = score.getScore();
		}
		this.scores.addFirst(score);
		
		this.writeXml(fileName);
	}
	
	public void readXml(String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			File file = new File(fileName);
			
			if(file.exists()) {
				db = dbf.newDocumentBuilder();
				Document doc = db.parse(file);
				
				Element root = doc.getDocumentElement();
				
				NodeList highscore = root.getElementsByTagName("highscore");
				Element elementHighscore = (Element) highscore.item(0);
				this.highscore = Long.parseLong(elementHighscore.getFirstChild().getNodeValue());
				
				NodeList scores = root.getElementsByTagName("score");
				for(int i=0; i<scores.getLength(); i++) {
					Element elementScore = (Element) scores.item(i);
					this.scores.add(new RegisterScore(Long.parseLong(elementScore.getFirstChild().getNodeValue()),
							new Time(Integer.parseInt(elementScore.getAttribute("hours")),
									Integer.parseInt(elementScore.getAttribute("minutes")),
									Integer.parseInt(elementScore.getAttribute("seconds"))),
							new Date(Long.parseLong(elementScore.getAttribute("date")))));
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeXml(String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;		
		try{
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			
			Element root = doc.createElement("scores");
			
			Element highscore = doc.createElement("highscore");
			highscore.appendChild(doc.createTextNode(""+this.highscore));
			root.appendChild(highscore);
			
			for(RegisterScore score : this.scores) {
				Element elementScore = doc.createElement("score");
				elementScore.setAttribute("date",""+score.getDate().getTime());
				elementScore.setAttribute("hours",""+score.getTime().getHours());
				elementScore.setAttribute("minutes",""+score.getTime().getMinutes());
				elementScore.setAttribute("seconds",""+score.getTime().getSeconds());
				elementScore.appendChild(doc.createTextNode(""+score.getScore()));
				root.appendChild(elementScore);
			}
			doc.appendChild(root);

			TransformerFactory tf = TransformerFactory.newInstance();
			tf.setAttribute("indent-number", Integer.valueOf(2));
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult sr = new StreamResult(new File(fileName));
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(source, sr);
		} catch(TransformerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
}

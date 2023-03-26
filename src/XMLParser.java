import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {
        HashMap<String, Malware> malwareDB = new HashMap<String, Malware>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(filename);

            Element kokElement = document.getDocumentElement();
            NodeList list1 = kokElement.getElementsByTagName("row");

            for (int i = 0; i < list1.getLength(); i++) {
                Node kisi = list1.item(i);
                Element kisiElement = (Element) kisi;
                String titlename = kisiElement.getElementsByTagName("title").item(0).getTextContent();
                int levelname = Integer.parseInt(kisiElement.getElementsByTagName("level").item(0).getTextContent());
                String hashname = kisiElement.getElementsByTagName("hash").item(0).getTextContent();
               // System.out.println(titlename + " " + levelname + " " + hashname);
                Malware malware = new Malware(titlename,levelname,hashname) ;
                malwareDB.put(malware.getHash(), malware);

            }

        } catch (Exception ex) {
            System.err.println(ex);
        }
        return malwareDB;
    }
}

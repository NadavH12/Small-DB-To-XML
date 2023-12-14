import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

public class XMLProgram {
     public static void main(String[] args){
          try {
               //creates document
               DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
               DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
               Document doc = dBuilder.newDocument();

               //creates root element (catalog), appends it to document (doc)
               Element rootElement = doc.createElement("catalog");
               doc.appendChild(rootElement);
              
               Element test = doc.createElement("test");
               CDATASection cdata = doc.createCDATASection("<><><><><><>>");
               test.appendChild(cdata);
               rootElement.appendChild(test);

               
          

               

               //creates element (book), creates attribute (id), appends (id) attribute to (book) element, sets id = "bk101"
               //appends element book to rootElement
               Element book = doc.createElement("book");
               Attr bookid = doc.createAttribute("id");
               book.setAttributeNode(bookid);
               bookid.setValue("bk101");
               rootElement.appendChild(book);

               //creates element(author),sets Text Content of (author) to (Gamaberdella, Matthew),appends element (author) to (book)
               Element author = doc.createElement("author");
               author.setTextContent("Gambardella, Matthew");
               book.appendChild(author);

               Element title = doc.createElement("title");
               title.setTextContent("XML Developer's Guide");
               book.appendChild(title);
               Element genre = doc.createElement("genre");
               genre.setTextContent("Computer");
               book.appendChild(genre);
               Element price = doc.createElement("price");
               price.setTextContent("44.95");
               book.appendChild(price);
               Element publish_date = doc.createElement("publish_date");
               publish_date.setTextContent("2000-10-01");
               book.appendChild(publish_date);
               Element description = doc.createElement("description");
               description.setTextContent("An in-depth look at creating applications with XML.");
               book.appendChild(description);

               //Add 2nd catalog entry
               Element book2 = doc.createElement("book");
               Attr bookid2 = doc.createAttribute("id");
               book2.setAttributeNode(bookid2);
               bookid2.setValue("bk102");
               rootElement.appendChild(book2);
               Element author2 = doc.createElement("author");
               author2.setTextContent("Ralls, Kim");
               book2.appendChild(author2);
               Element title2 = doc.createElement("title");
               title2.setTextContent("Midnight Rain");
               book2.appendChild(title2);
               Element genre2 = doc.createElement("genre");
               genre2.setTextContent("Fantasy");
               book2.appendChild(genre2);
               Element price2 = doc.createElement("price");
               price2.setTextContent("5.95");
               book2.appendChild(price2);
               Element publish_date2 = doc.createElement("publish_date");
               publish_date2.setTextContent("2000-12-16");
               book2.appendChild(publish_date2);
               Element description2 = doc.createElement("description");
               description2.setTextContent("A former architect battles corporate zombies, an evil sorceress, and her own childhood to become queen of the world");
               book2.appendChild(description2);

               //Code to add this is below:
               /*<acknowledgements>
                    <acknowledgement source="">Barack Obama:Interesting and provocative</acknowledgement>
                    <acknowledgement source="">New York Times:Beautifully written and so easy to understand</acknowledgement>
                    <acknowledgement source="">Wall Street Journal:Sapiens is learned, thought-provoking, and crisply written...Fascinating</acknowledgement>
                    <acknowledgement source="">Jared Diamond:Sapiens tackles the biggest questions of history and the modern world and it is written in unforgettably vivid language</acknowledgement>
               </acknowledgements>*/

               Element acknowledgements = doc.createElement("acknowledgements");
               book2.appendChild(acknowledgements);

               String[] acknowledgementTexts =  new String[] {"Barack Obama:Interesting and provocative","New York Times:Beautifully written and so easy to understand","Wall Street Journal:Sapiens is learned, thought-provoking, and crisply written...Fascinating","Jared Diamond:Sapiens tackles the biggest questions of history and the modern world " + "and it is written in unforgettably vivid language" };

               for (String a : acknowledgementTexts){
                    Element acknowledgement = doc.createElement("acknowledgement");
                    Attr source = doc.createAttribute("source");
                    acknowledgement.setAttributeNode(source);
                    source.setValue(a);
                    acknowledgements.appendChild(acknowledgement);
               }




               // write the content into xml file
               TransformerFactory transformerFactory = TransformerFactory.newInstance();
               Transformer transformer = transformerFactory.newTransformer();
               //Don't forget to enable pretty printing for xml
               transformer.setOutputProperty(OutputKeys.INDENT, "yes");
               DOMSource source = new DOMSource(doc);
               StreamResult result = new StreamResult(new File("C:\\Users\\amnon\\OneDrive\\Desktop\\XML\\output.xml"));
               transformer.transform(source, result);
               StreamResult result2 = new StreamResult(new File("C:\\Users\\amnon\\OneDrive\\Desktop\\XML\\output.txt"));
               transformer.transform(source, result2);

               // Output to console for testing
               StreamResult consoleResult = new StreamResult(System.out);
               transformer.transform(source, consoleResult);
               
          } catch (Exception e){
               e.printStackTrace();
          }
     }
}
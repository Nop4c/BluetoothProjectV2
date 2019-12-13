package outils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
public class Sms {
 
    public static String ENVOYERSMSPRO_LOGIN    =   "33611491982";//0611491982
    public static String ENVOYERSMSPRO_PASSWORD =   "4179";//4179
    public static String ENVOYERSMSPRO_HOST     =   "www.envoyersmspro.com";
    public static String ENVOYERSMSPRO_PROTOCOL =   "https";
    private boolean valide;
     
    /**
     * @param args
     */
    
    
    public Sms(){
    	
    }
    public void sendSms(String numero, String text) {
    	valide = true;
    	try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            
        	// httpClient = HttpClientBuilder.create().build();
        	
            HttpPost request = new HttpPost(ENVOYERSMSPRO_PROTOCOL+"://"+ENVOYERSMSPRO_HOST+"/api/message/send");
             
            String identifiant= ENVOYERSMSPRO_LOGIN + ":" + ENVOYERSMSPRO_PASSWORD;
            String encoded = Base64.encodeBase64String(identifiant.getBytes());
             
            request.addHeader("Content-Type","application/x-www-form-urlencoded");
            request.addHeader("charset","utf-8");
            request.addHeader("Authorization","Basic "+encoded);
             
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("text",text));
            nameValuePairs.add(new BasicNameValuePair("recipients",add33(numero)));
            nameValuePairs.add(new BasicNameValuePair("sendername","Societe"));
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
             
            HttpResponse response = httpClient.execute(request);
             
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder    = dbFactory.newDocumentBuilder();
 
            Document doc = dBuilder.parse(response.getEntity().getContent());
                 
            doc.getDocumentElement().normalize();
             
            if(doc.getElementsByTagName("status").item(0).getTextContent().equals("success"))
            {
                System.out.println("Message envoyé");
                System.out.println("Votre messageid : "+doc.getElementsByTagName("message_id").item(0).getTextContent());
                System.out.println("Nombre de SMS envoyé : "+doc.getElementsByTagName("sms_sent").item(0).getTextContent());
                System.out.println("Nombre de SMS restant : "+doc.getElementsByTagName("sms_remaining").item(0).getTextContent());
            }
            else
            {
            	valide = false;
                System.out.println("Le message n'a pas été envoyé :");
                NodeList nList=doc.getElementsByTagName("error").item(0).getChildNodes();
                for (int i = 0; i < nList.getLength(); i++)
                {
                    System.out.println(nList.item(i).getNodeName()+" : "+nList.item(i).getTextContent());
                }
            }
           // httpClient.getConnectionManager().shutdown();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public boolean getValidation() {
    	return valide;
    }
    
    public String add33(String numero) {
    	String res= "";
    	for(int i=1;i<numero.length();i++) {
    		res=res+numero.charAt(i);
    	}
    	res="33"+res;
    	return res;
    }
 
}                               
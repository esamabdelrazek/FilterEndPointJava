/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterendpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;
/**
 *
 * @author user
 */
public class FilterEndPoint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
      Filter("An").forEach((t) -> {
          System.out.println(t);
      });
    }
    
    public static List<String> Filter(String filter) throws IOException
    {
           //Creating a HttpClient object
      CloseableHttpClient httpclient = HttpClients.createDefault();
      //Creating a HttpGet object
      HttpGet httpget = new HttpGet("https://api.publicapis.org/categories");
      //Executing the Get request
      var httpresponse = httpclient.execute(httpget);
      Scanner sc = new Scanner(httpresponse.getEntity().getContent());
      String[] httpResult = sc.nextLine().replace("[", "").replace("]", "").replace("\"", "").split(",");
      List<String> filterdResult =new ArrayList<String>();
        for (String item: httpResult) {
            if(item.startsWith(filter))
            {
                filterdResult.add(item);
            }
        }  
        return filterdResult;
    }
    
}

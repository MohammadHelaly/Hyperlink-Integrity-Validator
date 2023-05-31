/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URL.Project;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author Mohamed
 */
public class ExtractLinks {
    

   public static void main(String[] args) {
      //      try {
      //          String link1 = "https://www.alexu.edu.eg/index.php/en/discover-au/4025-diamond-jubilee-celebrations-launch-in-alexandria-university";
      //          String link2 = "https://www.google.com";
     //           Document doc = Jsoup.connect(link1).get();
     //           Elements z = doc.select("a[href]");
     //           URL url= new URL(link1);            
     //           String website = url.getProtocol()+"://"+url.getHost();
     //           System.out.println("Website: "+website);
     //           ValidationOfLink v= new ValidationOfLink();
     //          
     //           for (int i=0; i<z.size(); i++)   //printing the valid and invalid links
     //               {
     //               String q= z.get(i).attr("href");
     //                   
     //                   if(q.startsWith("http"))
     //                 {
     //                     System.out.println(q);
     //                 }  
     //                   else 
     //                 {
     //                     q = website+q;  
     //                        System.out.println(q);
     //                 }
     //                   if(v.validateLink(q))
     //                 {
     //                     System.out.println("Link is Valid:  "+q);                       
     //                 }
     //                   else
     //                 {
     //                      System.out.println("Link is Invalid:  "+q); 
     //                     
     //                 }
     //               }                          
     //           }
     //       catch (IOException ex) {
     //   System.out.println("URL is invalid. Please enter another URL");
     //  }
    }
}
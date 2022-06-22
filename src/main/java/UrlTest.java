
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;
import utils.FileUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author elahi
 */
public class UrlTest {
    
    public static void main (String args[]) throws Exception
    {
        Set<String> Urls=new TreeSet<String>();
        String fileName="../resources/acoli-dicts/stable/apertium/apertium-rdf-2020-03-18/";
        FileUtils.fileToHashSet(fileName);

        testUnresolved();
    }

    private static void testUnresolved() throws ProtocolException, MalformedURLException, IOException {
        URL url = new URL("http://google.com");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int code = connection.getResponseCode();
        System.out.println("Response code of the object is "+code);
        if (code==200)
        {
            System.out.println("OK");
        }
    }
    
}

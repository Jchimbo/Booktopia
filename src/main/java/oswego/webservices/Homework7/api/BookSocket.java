package oswego.webservices.Homework7.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class BookSocket {

    static String jsonString = "";

    /**
     * Takes in a url and reads the json string returned by the url this is then append to jsonString.
     * @param url the webpage that is being visited that returns a json string
     */
    public BookSocket(URL url) {
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            BufferedReader bin = new BufferedReader (inputStreamReader );
            StringBuilder builders = new StringBuilder();
            Object[] barr = bin.lines().toArray();
            for (Object o : barr) {
                builders.append(o);
            }
            jsonString = builders.toString();
        }catch (Exception e){

                jsonString =null;


        }

    }

    public String getJsonString() {
        return jsonString;
    }

}

package com.echo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonParser {

    public String getDefinition(int position, String word){
        try {
            URL url = new URL("http://api.urbandictionary.com/v0/define?term=" + word);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer sb = new StringBuffer();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

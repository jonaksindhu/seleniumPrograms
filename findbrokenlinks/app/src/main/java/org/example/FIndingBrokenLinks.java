package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpRequest;
import java.nio.file.WatchEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class FIndingBrokenLinks {

    public static void main(String[] args) throws IOException {
        
    WebDriver  wd = new ChromeDriver();
    wd.get("file:///app/src/main/resources/brokenlink page/pagewithlink.html");

    List<WebElement> urls = wd.findElements(By.tagName("a"));
    System.out.println("Urls present"+urls.size());

    for (WebElement ele : urls)
    {
        String url = ele.getDomAttribute("href");
        checkbrokenlink(url);
    }
    }
    

    private static void checkbrokenlink(String url) {
        
        try{
        URL urllink = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)urllink.openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();

        int statuscode = connection.getResponseCode();

        if (statuscode>=400)
        {
            System.out.println("Broken link"+ url);
        }
        else if (statuscode>=200 && statuscode<400)
        {
            System.out.println("Links is working fine "+url);
        }
        }
        catch(Exception e){
            System.err.println("Caught the exception"+ e.getMessage());

        }
 
    }
}
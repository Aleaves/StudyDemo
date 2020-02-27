package com.sdk.lib;

import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class AutoPack {
    public static void main(String[] args) {
        File file = new File("a.txt");
        if(file.exists()){
            System.out.println("file is exist");
        }else{
            System.out.println("file not found");
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(fis);
        }catch (Exception e){

        }
    }
}

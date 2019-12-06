package com.example.ocr.tessaractdemo.component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

@Component
public class LoadTrainedData {

	
	public static String dataPath="";
	
	@PostConstruct
	public void init() {
		
		System.out.println("PostConstruct init called...");
		
		ClassPathResource cpr = new ClassPathResource("static/Tessaract-Data/tessdata/deu.traineddata");
		//InputStream in = getClass().getResourceAsStream("src/main/resources/static/Tessaract-Data/tessdata/deu.traineddata");
		InputStream in=null;
		try {
			in = cpr.getInputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(in!=null) {
			
			System.out.println("input stream is not null...");
			byte[] buffer = null;
			try {
				buffer = new byte[in.available()];
				in.read(buffer);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		
			
			 try {	 
				 if(buffer!=null) {
			String tempDir=System.getProperty("java.io.tmpdir");
			 File file = new File(tempDir+"deu.traineddata");
			
				FileOutputStream out=new FileOutputStream(file);
				
				out.write(buffer);
				
				dataPath=file.getParent();
				System.out.println("Temp path : "+dataPath);
				 }
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		}else {
			
			System.out.println("input stream is null...");
		}
	}
}

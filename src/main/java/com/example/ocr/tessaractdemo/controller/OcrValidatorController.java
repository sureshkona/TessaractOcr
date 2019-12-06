package com.example.ocr.tessaractdemo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ocr.tessaractdemo.component.LoadTrainedData;
import com.example.ocr.tessaractdemo.utils.Helper;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


@RestController
public class OcrValidatorController {
	
	@Value("classpath:static/Tessaract-Data/tessdata")
	private Resource resource;
	
	List<String> OcrMatchTextList;
	
	
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public boolean fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("language")String language) throws IOException, TesseractException {
		
	
		
		//String absolutePath=ResourceUtils.getFile("classpath:static/Tessaract-Data/tessdata").getAbsolutePath();
		//System.getenv();
		
		//System.out.println("AbsolutePath : "+absolutePath);
		
		 File convFile = convert(file);
	        Tesseract tesseract = new Tesseract();
	        tesseract.setLanguage(language);
	       // tesseract.setDatapath("C://work//Tessaract-Data//tessaract-demo//src//main//resources//static//Tessaract-Data");
	       // tesseract.setDatapath("C:\\work\\Tessaract-Data\\tessdata");
	        //tesseract.setDatapath("src/main/resources/static/Tessaract-Data/tessdata");
	       // System.out.println("Path : "+resource.getURI().getPath());
	      //  tesseract.setDatapath(resource.getURI().getPath());
	        tesseract.setDatapath(LoadTrainedData.dataPath);
	        String text = tesseract.doOCR(convFile);
	        
	        System.out.println("Result Text  : "+text);
	        OcrMatchTextList=new Helper().getMatchWords();
	        
	        if(OcrMatchTextList!=null && text!=null) {
	        int count = 0;
            for (int i = 0; i < OcrMatchTextList.size(); i++)
            {

                if (text.contains(OcrMatchTextList.get(i)))
                {
                    count++;

                }
            }
            
            System.out.println("Matched words count : "+count);
            //System.out.println("AbsolutePath : "+absolutePath);
            if(count>=2) {
            	
            	return true;
            }
	        }else {
	        	return false;
	        }
		
		return false;
		
	}
	
	 public static File convert(MultipartFile file) throws IOException {
	        File convFile = new File(file.getOriginalFilename());
	        convFile.createNewFile();
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	        return convFile;
	    }

}

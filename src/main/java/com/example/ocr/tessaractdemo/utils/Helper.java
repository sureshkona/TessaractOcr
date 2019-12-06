package com.example.ocr.tessaractdemo.utils;

import java.util.ArrayList;
import java.util.List;

public class Helper {

	List<String> OcrMatchTextList=new ArrayList<String>();
	
	public Helper() {
		OcrMatchTextList.add("Arbeitsunfähigkeits");
		OcrMatchTextList.add("Arbeitsunfall");
		OcrMatchTextList.add("Vorlage");
		OcrMatchTextList.add("arbeitsunfähig");
		OcrMatchTextList.add("Arbeitgetrer");
		OcrMatchTextList.add("voraussichtlich");
		OcrMatchTextList.add("festgestellt");
		
		
	}
	
	public List<String> getMatchWords(){
		return OcrMatchTextList;
	}
}

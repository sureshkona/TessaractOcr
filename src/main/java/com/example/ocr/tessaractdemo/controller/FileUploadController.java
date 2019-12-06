package com.example.ocr.tessaractdemo.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Controller
public class FileUploadController {
	
	
	@RequestMapping("/")
	public String index() {
		
		return "upload";
	}

	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public RedirectView singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Model model) throws IOException, TesseractException {

        byte[] bytes = file.getBytes();
        Path path = Paths.get("C://work//Tessaract-Data//tessaract-demo//src//main//resources//static//" + file.getOriginalFilename());
        Files.write(path, bytes);

        File convFile = convert(file);
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("deu");
        tesseract.setDatapath("C://work//Tessaract-Data//tessaract-demo//src//main//resources//static//Tessaract-Data");
        String text = tesseract.doOCR(convFile);
        redirectAttributes.addFlashAttribute("file", file);
        redirectAttributes.addFlashAttribute("text", text);
        return new RedirectView("result");
    }

    @RequestMapping("/result")
    public String result() {
        return "result";
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

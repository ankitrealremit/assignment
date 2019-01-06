package com.jpmc.assignment.rest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author Ankit Kumar
 * @date 01/06/2019
 */

@Controller
public class FileUpload {

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public String uploadFiles(@RequestParam("firstFile") MultipartFile firstFile,
                              @RequestParam("secondFile") MultipartFile secondFile
            , ModelMap model) throws IOException {
        //Commented method below was my first try but it failed when there was a blank space in teh EOF so found another method to ignore
        //boolean isIdentical = IOUtils.contentEquals(firstFile.getInputStream()secondFile.getInputStream());
        boolean isIdentical = IOUtils.contentEqualsIgnoreEOL(new InputStreamReader(firstFile.getInputStream()), new InputStreamReader(secondFile.getInputStream()));
        //Wriiten this method to verify if the files are properly being uploaded starts
        //writefile(firstFile);
        //writefile(secondFile);
        //Wriiten this method to verify if the files are properly being uploaded end
        String message = "";
        if (isIdentical) {
            message = "File are Identical!";
        } else {
            message = "Files are not Identical";
        }
        model.addAttribute("isIdentical", isIdentical);
        return "index.html";
    }

    //This method is for testing if the files are being properly uploaded
    public static void writefile(MultipartFile file) throws IOException {
        File fileInput = new File("C:/temp/", file.getOriginalFilename());
        FileUtils.writeByteArrayToFile(fileInput, file.getBytes());
    }

}
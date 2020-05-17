/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controllers;

import com.example.demo.database.models.User;
import com.example.demo.services.FileSystem;
import com.example.demo.services.FileSystem.FileSystemRespone;
import com.example.demo.services.FileSystem.SFSFile;
import com.example.demo.services.Services;
import java.io.IOException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/general")
public class GeneralController {
    
    
    @GetMapping("/get-image/{img:.+}")
    public byte[] getImage(@PathVariable String img) {
        FileSystemRespone<SFSFile> res = FileSystem.getFile(img);

        if(res.ok) return res.msg.bytes;
        else return null;
    }
    
    @PostMapping("/upload-image")
    public Response<String> uploadFile(MultipartFile file){
        try{
            FileSystemRespone<String> res = FileSystem.saveFile(new SFSFile(file.getBytes(), file.getOriginalFilename()));
            if(res.ok) return new Response(true, res.msg, "File Saved");
            else return new Response(false, "File no Saved", res.ex);
        }catch(IOException ex){
            Services.handleError(ex);
            return new Response(false, "Error getting file bytes", ex);
        }
        
        
    }
}

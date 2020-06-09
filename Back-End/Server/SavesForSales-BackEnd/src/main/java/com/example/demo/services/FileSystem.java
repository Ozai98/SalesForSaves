/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 *
 * @author German le yo
 */
public class FileSystem {
    
    public static class SFSFile{
        public final byte[] bytes;
        public final String name;
        
        
        public SFSFile(byte[] bytes, String name){
            this.bytes = bytes;
            this.name = name;
        }
    }
    
    public static final String DEFAULT_IMG = "noimg.unknown";

    public static class FileSystemRespone<T>{
        public final T msg;
        public final Exception ex;
        public final boolean ok;
        private FileSystemRespone(T msg, Exception ex){
            this.msg = msg;
            this.ex = ex;
            ok = ex == null;
        }
    }
    
    private static String SAVE_PATH = "uploads/";

    
    public static void setSavePath(String str){
        SAVE_PATH = str;
    }
    
    private static String getExt(String fileName){
        int lIndex = fileName.lastIndexOf('.');
        if(lIndex == -1)return ".unknown";
        return fileName.substring(lIndex);
    }
    
    public static FileSystemRespone<String> saveFile(SFSFile file){
        String fileName = UUID.randomUUID().toString() + getExt(file.name);
        try { 
            
            OutputStream os = new FileOutputStream(Paths.get(SAVE_PATH + fileName).toFile()); 
            os.write(file.bytes); 
            os.close(); 
            return new FileSystemRespone(fileName, null);
        } catch (Exception e) {
            Services.handleError(e);
            return new FileSystemRespone(null, e);
        } 
    }
    
    public static FileSystemRespone<SFSFile> getFile(String name){
        try{
            byte[] fileContent = Files.readAllBytes(Paths.get(SAVE_PATH + name));
            return new FileSystemRespone(new SFSFile(fileContent, name), null);
        }catch(Exception e){
            return new FileSystemRespone(null, e);
        }
        
    }
    
    public static FileSystemRespone<String> removeFile(String name){
        try{
            Files.deleteIfExists(Paths.get(SAVE_PATH + name));
            return new FileSystemRespone("Ok", null);
        }catch(Exception e){
            return new FileSystemRespone(null, e);
        }
        
    }
    
    
    
    
}

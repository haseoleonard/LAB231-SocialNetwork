/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;

/**
 *
 * @author haseo
 */
public final class FileHelpers {
    public static void writeImgToServerFile(final String imgFileName,final InputStream fileContent) throws FileNotFoundException, IOException{
        File imgFile = new File(imgFileName);
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        try{            
            fos = new FileOutputStream(imgFile);
            bis = new BufferedInputStream(fileContent);
            while(bis.available()>0){
                fos.write(bis.read());
            }
        }finally{
            if(bis!=null)bis.close();
            if(fos!=null)fos.close();
        }
    }
    public static void copyImgToContextFolder(final String realPath,final String fileName) throws FileNotFoundException, IOException{
        File inputFile = new File("D:\\PostsImg\\"+fileName);
//        File inputFile = new File("/mnt/extern-drive-1/PostsImg/"+fileName);
        File outputFile = new File(realPath+"\\"+fileName);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis=new FileInputStream(inputFile);
            fos= new FileOutputStream(outputFile);
            while(fis.available()>0){
                fos.write(fis.read());
            }
        }finally{
            if(fos!=null)fos.close();
            if(fis!=null)fis.close();
        }
        
    }
}

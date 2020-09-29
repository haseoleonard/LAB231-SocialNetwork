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
import java.util.Properties;

/**
 *
 * @author haseo
 */
public final class FileHelpers {
    private static String IMAGE_SAVING_FOLDER="";

    public static void setIMAGE_SAVING_FOLDER(String IMAGE_SAVING_FOLDER) {
        FileHelpers.IMAGE_SAVING_FOLDER = IMAGE_SAVING_FOLDER;
    }

    public static void writeImgToServerFile(final String imgFileName, final InputStream fileContent) throws FileNotFoundException, IOException {
        File imgDir = new File(IMAGE_SAVING_FOLDER);
        if (!imgDir.exists()) {
            imgDir.mkdir();
        }
        File imgFile = new File(imgDir, imgFileName);
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        try {
            fos = new FileOutputStream(imgFile);
            bis = new BufferedInputStream(fileContent);
            while (bis.available() > 0) {
                fos.write(bis.read());
            }
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    public static void copyImgToContextFolder(final String realPath, final String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File inputFile = new File(IMAGE_SAVING_FOLDER+ File.separator + fileName);
        if (inputFile.exists()) {
            File outputFile = new File(realPath + File.separator + fileName);
            if (!outputFile.exists()) {
                try {
                    fis = new FileInputStream(inputFile);
                    fos = new FileOutputStream(outputFile);
                    while (fis.available() > 0) {
                        fos.write(fis.read());
                    }
                } finally {
                    if (fos != null) {
                        fos.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                }
            }
        }
    }
}

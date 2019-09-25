package com.yunzhidata.jiushuo.website.util;

import com.yunzhidata.jiushuo.website.input.ImgInputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

@Service
public class CmykOrRgb implements ICmykOrRgb{
    @Value("${imgpath}")
    private String path;
    @Autowired
    private UtilService utilService;

    @Override
    public void isCmykOrRgb(ImgInputType input) {
        File file=utilService.isFile(input.getUrl());
        BufferedImage ori=null;
        if(file.exists()){
            File saveFile=new File(path+"test.jpg");
            try {
                saveFile.createNewFile();
                ori=ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedImage newImg = ori.getSubimage(0,0,ori.getWidth(),ori.getHeight());
            try {
                ImageIO.write(newImg,"png",saveFile);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("----cmyk");
            }
        }
    }

    //
}

/**
 *  cmtk  pixeSize=24
 *          colorSpace  minVal 0.0 0.0 0.0
 *                      maxVal 1.0 1.0 1.0
 *           numComponents=3
 *
 *  rgb   pixeSize=8
 *          colorSpace  minVal 0.0 0.0 0.0
 *                      maxVal 1.0 1.0 1.0
 *
 *
 * */

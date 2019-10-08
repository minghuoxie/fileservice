package com.yunzhidata.jiushuo.website.help.img;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgTest {
    /**
     *生成 折线图
     * */
    public static void test_gxian(){
        int width=1000;
        int hei=800;
        BufferedImage bufImg=new BufferedImage(width,hei,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d=(Graphics2D)bufImg.getGraphics();//获取画笔



        g2d.drawImage(bufImg,0,0,null);
        try {
            ImageIO.write(bufImg,"png",new File("D:/data/imgpath/gimg/zxian.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

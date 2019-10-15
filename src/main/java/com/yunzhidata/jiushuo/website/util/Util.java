package com.yunzhidata.jiushuo.website.util;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.yunzhidata.jiushuo.website.dto.ImageDto;
import com.yunzhidata.jiushuo.website.dto.MapDto;
import com.yunzhidata.jiushuo.website.input.ImgInputType;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

@Service
public class Util implements UtilService {
    @Value("${imgpath}")
    private String path;
    @Value("${fwq}")
    private String fwq;

    @Value("${imgpre}")
    private String imgpre;
    @Value("${imgnxt}")
    private String imgnxt;

    /**
     * 获取时间格式的字符串  yyyy-MM-dd hh-mm-ss    yyyyMM
     * */
    public String getFormatTime(String formatTime){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat(formatTime);
        return format.format(date);
    }

    /**
     * 获取保存的文件名
     *
     * */
    @Override
    public File getSaveFileName(String endName){
        //目录不存在 则创建目录
        File dirPath=new File(path+getFormatTime("yyyyMM")+"/");
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
        String fileName=dirPath.getAbsolutePath()+"/"+imgpre+System.currentTimeMillis()+imgnxt+endName;
        File file=new File(fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 分发派转
     * */
    @Override
    public ImageDto imgDispatch(ImgInputType input){
        File file=null;
        ImageDto dto=new ImageDto();
        dto.setAttributes(false,"参数错误",null);
        if(isInput(input)&&(file=isFile(input.getUrl())).exists()){
            switch (input.getType()){
                /**
                 * 减少图片质量
                 * */
                case 1:
                    try {
                        reduceQuaity(input,file,dto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        tailoring(input,file,dto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        ratateImg(input,file,dto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        return dto;
    }

    /**
     * 图片的旋转   有问题
     * */
    private void ratateImg(ImgInputType input,File file,ImageDto dto) throws IOException{
        isRgbOrCmyk(file);
        BufferedImage bufImage = ImageIO.read(file);
        int w=bufImage.getWidth();
        int h=bufImage.getHeight();
        int type=bufImage.getColorModel().getTransparency();
        BufferedImage newImg;
        Graphics2D graphics2D;
        (graphics2D=(newImg=new BufferedImage(w,h,type))
                .createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.rotate(Math.toRadians(input.getDegree()),w/2,h/2);
        graphics2D.drawImage(bufImage,0,0,null);
        graphics2D.dispose();
        ImageIO.write(newImg,"png",file);
        dto.setAttributes(true,"操作成功",input.getUrl());
    }

    /**
     * 图片裁剪
     * */
    private void tailoring(ImgInputType input,File file,ImageDto dto) throws IOException {
        isRgbOrCmyk(file);
        BufferedImage bufImage = ImageIO.read(file);
        int imgW=bufImage.getWidth();
        int imgH=bufImage.getHeight();
        //裁剪的参数大小验证
        if(input.getPointX()<0){
            input.setPointX(0);
        }
        if(input.getPointY()<0){
            input.setPointY(0);
        }
        if((input.getPointX()+input.getWigth())>imgW){
            input.setWigth(imgW-input.getPointX());
        }
        if((input.getPointY()+input.getHeight())>imgH){
            input.setHeight(imgH-input.getPointY());
        }
        BufferedImage newImg = bufImage.getSubimage(input.getPointX(),input.getPointY(),input.getWigth(),input.getHeight());
        ImageIO.write(newImg,"png",file);
        dto.setAttributes(true,"操作成功",input.getUrl());
    }

    /**
     * 判断Cmyk 图像韩式Rgb图像  如果是Cmyk图像 将其转换为Rgb模式图像
     * */

    private boolean isROC(File file) throws IOException {
        boolean isRgb=true;//true是Rgb否则是Cmyk
        //创建输入流
        ImageInputStream input = ImageIO.createImageInputStream(file);
        Iterator readers = ImageIO.getImageReaders(input);
        if (readers == null || !readers.hasNext()) {
            throw new RuntimeException("No ImageReaders found");
        }
        ImageReader reader = (ImageReader) readers.next();
        reader.setInput(input);
        //获取文件格式
        // BufferedImage image;
        try {
            // 尝试读取图片 (包括颜色的转换).
            reader.read(0); // RGB
            isRgb=true;
        } catch (IIOException e) {
            // 读取Raster (没有颜色的转换).
            reader.readRaster(0, null);// CMYK
            isRgb=false;
        }
        input.close();
        return  isRgb;
    }

    private void isRgbOrCmyk(File file){
//        try {
//            BufferedImage bufferedImage= ImageIO.read(file);
//            Thumbnails.of(file)
//                    .size(bufferedImage.getWidth(),bufferedImage.getHeight())
//                    .toFile(file);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        try {
            BufferedImage bufferedImage= ImageIO.read(file);
            ImageIO.write(bufferedImage,"png",file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 减少图片质量
     * */
    private void resize(File originalFile,float quality)throws IOException{
        if (quality > 1) {
            throw new IllegalArgumentException(
                    "Quality has to be between 0 and 1");
        }
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;
        int newWidth=i.getWidth(null);
        int iHeight = i.getHeight(null);
        resizedImage = i.getScaledInstance(newWidth, iHeight, Image.SCALE_SMOOTH);

        Image temp = new ImageIcon(resizedImage).getImage();
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();

        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        // Write the jpeg to a file.
        FileOutputStream out = new FileOutputStream(originalFile);

        // Encodes image as a JPEG data stream
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

        JPEGEncodeParam param = encoder
                .getDefaultJPEGEncodeParam(bufferedImage);

        param.setQuality(quality, true);

        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
        out.close();
    }
    private void reduceQuaity(ImgInputType input,File file,ImageDto dto) throws IOException {
        isRgbOrCmyk(file);
        //图片大于2M  压缩60%  大于1M压缩80%  大于900K 压缩90;
        long size=file.length();
        if((size/1024)>2*1000){
            resize(file,0.6f);
        }else if((size/1024)>1000){
            resize(file,0.7f);
        }else{
            resize(file,0.9f);
        }
        dto.setAttributes(true,"操作成功",input.getUrl());
    }

    /**
     * 验证输入参数
     * */
    private boolean isInput(ImgInputType input){
        if(input.getType()!=null&&input.getType()==1){
            return true;
        }else if(input.getType()!=null&&input.getType()==2){
            return true;
        }else if(input.getType()!=null&&input.getType()==3){
            return true;
        }
        return false;
    }

    @Override
    public MapDto delImage(String url){
        MapDto dto=new MapDto();
        File file=null;
        if((file=isFile(url)).exists()){
            if(file.delete()){
                dto.setFlag(true);
                dto.setInfo("删除图片成功");
            }
        }
        return dto;
    }

    /**
     * 验证文件是不是存在
     * http://localhost:8089/loadfile/201909/rt_sdf_ryz_dfgdfg_fdfg_1569225984014po_df_dfg_ewrdg_dfg_sdf.jpg
     *  fwq localhost:8089 /loadfile
     *  path D:/data/imgpath/
     *
     *  String uri=" http://localhost:8089/loadfile/201909/rt_sdf_ryz_dfgdfg_fdfg_1569225984014po_df_dfg_ewrdg_dfg_sdf.jpg";
     *         uri=uri.replaceAll(".*localhost:8089/loadfile","D:/data/imgpath/");
     * */
    @Override
    public File isFile(String url){
        String filePath=url.replaceAll(".*"+fwq+"/loadfile",path);
        File file=new File(filePath);
        return file;
    }

}

package com.cn.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

/**
 * @author 袁华洋
 *该JavaBean可以直接在其他Java应用程序中调用，实现屏幕的"拍照" 
 */
public class GuiCamera {
	 /** 
     * 默认的文件前缀为GuiCamera，文件格式为PNG格式 The default construct will use the default 
     * Image file surname "GuiCamera", and default image format "png" 
     */  
	private final static String FORMAT_PNG = "png";  
    @SuppressWarnings("unused")
	private final static String FORMAT_JPG = "jpg";  
    private static String filePath = "";// 存放路径  
    private static String fileName = "GuiCamera_"; // 文件的前缀  
    @SuppressWarnings("unused")
	private static int serialNum = 0;  
    private static String imageFormat = GuiCamera.FORMAT_PNG; // 图像文件的格式  
    
    private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();  
    /** 
     * 对屏幕进行拍照 snapShot the Gui once 
     */  
    public static void snapShot() throws Exception {  
        try {  
            // 拷贝屏幕到一个BufferedImage对象screenshot  
            BufferedImage screenshot = (new Robot()).createScreenCapture(new Rectangle(0, 0, (int) d.getWidth(),  
                    (int) d.getHeight()));  
            serialNum++;  
            // 根据文件前缀变量和文件格式变量，自动生成文件名  
            Calendar c = Calendar.getInstance();  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");  
            String name = filePath + fileName + sdf.format(c.getTime()) + "." + imageFormat;  
            File f = new File(name);  
            // 将screenshot对象写入图像文件  
            ImageIO.write(screenshot, imageFormat, f);  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
    public static void main(String[] args) throws Exception {  
        GuiCamera.filePath = "D://";  
        GuiCamera.snapShot();  
    }  
}

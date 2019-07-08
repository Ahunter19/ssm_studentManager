package cn.edu.hunter.sms.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @version 1.0.0
 * @description 绘制验证码图片
 * @projectName sms_studentManagerSystem
 * @anthor 陈亮
 * @data: 2019/7/8 15:38
 */
public class CpachaUtil {

    private static int CODE_LEN = 4;   //验证码长度
    private static int CODE_FONT_SIZE = 20; //验证码字体大小
    private static int CODE_IMG_WIDTH = (CODE_FONT_SIZE + 1) * CODE_LEN + 10; //验证码图片宽度
    private static int CODE_IMG_HEIGHT = CODE_FONT_SIZE + 12; //验证码图片高度
    private static int CODE_DISTURBLINE = 3;   //干扰线条数
    private static final char[] code = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };   //验证码来源
    private static final String[] fontNames = new String[]{
            "黑体", "宋体", "Arial", "Times", "Courier", "Georgia"
    };//字体

    private static final int[] fontStyles = new int[]{
            Font.BOLD, Font.ITALIC | Font.BOLD
    }; //字体样式


    public CpachaUtil() {

    }

    /**
     * @param codeLen 验证码长度
     * @description: 指定验证码长度
     * @author: 陈亮
     * @time: 2019/7/8 15:55
     */
    public CpachaUtil(int codeLen) {
        CODE_LEN = CODE_LEN;
        CODE_IMG_WIDTH = (CODE_FONT_SIZE + 1) * CODE_LEN + 10;
    }

    /**
     * @param codeLen 验证码长度
     * @param width   验证码图片宽度
     * @param height  验证码图片高度
     * @description: 指定验证码长度、图片宽度、图片高度
     * @author: 陈亮
     * @time: 2019/7/8 15:55
     */
    public CpachaUtil(int codeLen, int width, int height) {
        CODE_LEN = codeLen;
        CODE_IMG_WIDTH = width;
        CODE_IMG_HEIGHT = height;
    }

    /**
     * @description: 获取验证码图片
     * @return: BufferedImage
     * @author: 陈亮
     * @time: 2019/7/8 16:01
     */
    private BufferedImage genertorVCodeImage(String vcode, boolean drawline) {
        //创建一张验证码图片
        BufferedImage codeImage = new BufferedImage(CODE_IMG_WIDTH, CODE_IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = codeImage.getGraphics();
        //填充背景颜色
        graphics.setColor(new Color(246, 240, 250));
        graphics.fillRect(0, 0, CODE_IMG_WIDTH, CODE_IMG_HEIGHT);
        if (drawline) {
            drawDisturbLine(graphics);
        }
        //用于生成伪随机数
        Random random = new Random();
        //在图片上画验证码
        for (int i = 0; i < vcode.length(); i++) {
            //设置字体
            graphics.setFont(new Font(fontNames[random.nextInt(fontNames.length)], fontStyles[random.nextInt(fontStyles.length)], CODE_IMG_WIDTH));
            //随机生成颜色
            graphics.setColor(getRandomColor());
            //画验证码
            graphics.drawString(vcode.charAt(i) + "", i * CODE_FONT_SIZE + 10, CODE_FONT_SIZE + 5);
        }
        //释放此图形的上下文以及其他使用的所有系统资源
        graphics.dispose();
        return codeImage;
    }

    /**
     * @param : vcode
     * @param : drawline 是否画干扰线
     * @description: 获得旋转字体的验证码图片
     * @author: 陈亮
     * @time: 2019/7/8 15:23
     */
    public BufferedImage generatorRotateVCodeImage(String vcode, boolean drawline) {
        //创建验证码图片
        BufferedImage codeImage = new BufferedImage(CODE_IMG_WIDTH, CODE_IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = codeImage.createGraphics();
        graphics.setColor(new Color(246, 240, 250));
        graphics.fillRect(0, 0, CODE_IMG_WIDTH, CODE_IMG_HEIGHT);
        if (drawline) {
            drawDisturbLine(graphics);
        }

        for (int i = 0; i < vcode.length(); i++) {
            BufferedImage rotateImage = getRotateImage(vcode.charAt(i));
            graphics.drawImage(rotateImage, null, (int) ((CODE_IMG_HEIGHT * 0.7) * i), 0);
        }
        graphics.dispose();
        return codeImage;
    }

    /**
     * @description: 生成验证码
     * @return: stringBuffer.toString()
     * @author: 陈亮
     * @time: 2019/7/8 15:38
     */
    public String generatorVCode() {
        int len = CODE_LEN;
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < CODE_LEN; i++) {
            int index = random.nextInt(len);
            stringBuffer.append(code[index]);
        }
        return stringBuffer.toString();
    }

    /**
     * @description: 为验证码图片画干扰线
     * @author: 陈亮
     * @time: 2019/7/8 15:38
     */
    private void drawDisturbLine(Graphics graphics) {
        Random random = new Random();
        for (int i = 0; i < CODE_DISTURBLINE; i++) {
            int x1 = random.nextInt(CODE_IMG_WIDTH);
            int y1 = random.nextInt(CODE_IMG_HEIGHT);
            int x2 = random.nextInt(CODE_IMG_WIDTH);
            int y2 = random.nextInt(CODE_IMG_HEIGHT);
            graphics.setColor(getRandomColor());
            //画干扰线
            graphics.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * @description: 旋转图片
     * @author: 陈亮
     * @time: 2019/7/8 15:51
     */
    private BufferedImage getRotateImage(char c) {
        BufferedImage codeImage = new BufferedImage(CODE_IMG_WIDTH, CODE_IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics2D graphics = codeImage.createGraphics();
        //设置透明度为0
        graphics.setColor(new Color(255, 255, 255, 0));
        graphics.fillRect(0, 0, CODE_IMG_WIDTH, CODE_IMG_HEIGHT);
        Random random = new Random();
        graphics.setFont(new Font(fontNames[random.nextInt(fontNames.length)], fontStyles[random.nextInt(fontStyles.length)], CODE_IMG_WIDTH));
        graphics.setColor(getRandomColor());
        double theta = getTheta();
        //旋转图片
        graphics.rotate(theta, CODE_IMG_HEIGHT / 2, CODE_IMG_HEIGHT / 2);
        graphics.drawString(Character.toString(c), (CODE_IMG_HEIGHT - CODE_FONT_SIZE) / 2, CODE_FONT_SIZE + 5);
        graphics.dispose();
        return codeImage;
    }

    /**
     * @description:获取随机颜色
     * @author: 陈亮
     * @time: 2019/7/8 15:52
     */
    private Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
    }

    /**
     * @return double
     * @description: 角度
     * @author: 陈亮
     * @time: 2019/7/8 15:52
     */
    private double getTheta() {
        return (int) (Math.random() * 1000 % 2 == 0 ? -1 : 1) * Math.random();
    }
}

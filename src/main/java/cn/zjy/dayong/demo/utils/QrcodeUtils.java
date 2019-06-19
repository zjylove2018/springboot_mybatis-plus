package cn.zjy.dayong.demo.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.zxing.client.j2se.MatrixToImageConfig.BLACK;
import static com.google.zxing.client.j2se.MatrixToImageConfig.WHITE;

/**
 * 二维码生成工具类
 * Created with IDEA
 * author:zjy
 * Date:2018/10/30
 * Time:17:48
 */
public class QrcodeUtils {

    public static BitMatrix getQrcode(String content, HttpServletResponse resp){
        ServletOutputStream stream = null;
        BitMatrix bm = null;
        int width = 200;
        int height = 200;
        try {
            stream = resp.getOutputStream();
            Map<EncodeHintType, Object> hintMap = new HashMap<EncodeHintType, Object>();
            hintMap.put(EncodeHintType.CHARACTER_SET,"utf-8");
            hintMap.put(EncodeHintType.MARGIN, 0);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bm = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hintMap);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, (bm.get(x, y) ? BLACK : WHITE));
                }
            }
            Graphics2D g2 = image.createGraphics();
            if(content.contains("胖货")){
                g2.drawImage(ImageIO.read(ResourceUtils.getFile("classpath:logo.png")), 75 , 75 , 50, 50, null); // logo.png自行设置
                g2.draw(new RoundRectangle2D.Float(75 , 75 , 50, 50, 20, 20));
            }else if(content.contains("我们")){
                g2.drawImage(ImageIO.read(ResourceUtils.getFile("classpath:women.png")), 75 , 75 , 50, 50, null); // logo.png自行设置
                g2.draw(new RoundRectangle2D.Float(75 , 75 , 50, 50, 20, 20));
            }else if(content.contains("大周")){
                g2.drawImage(ImageIO.read(ResourceUtils.getFile("classpath:dayong.jpg")), 75 , 75 , 50, 50, null); // logo.png自行设置
                g2.draw(new RoundRectangle2D.Float(75 , 75 , 50, 50, 20, 20));
            }
            g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(Color.white);
            g2.dispose();
            image.flush();
//            MatrixToImageWriter.writeToStream(bm, "jpg", stream);
            ImageIO.write(image, "jpg", stream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.flush();
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }
}

package com.leon.tank;


import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.junit.Test;

/**
 * 图片读取测试类
 *
 * @author : lihuiming
 * @version : 2021/1/16 21:28
 * @modified:
 */
public class ImageReadTest {

  @Test
  public void test() {
    try {
      BufferedImage image = ImageIO.read(new File("src/main/resources/images/bulletD.gif"));
      assertNotNull(image);
      BufferedImage image2 = ImageIO
          .read(this.getClass().getClassLoader().getResourceAsStream("images/0.gif"));
      assertNotNull(image2);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

package com.leon.tank.config;

import com.leon.tank.util.ImageUtil;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * 资源管理类
 *
 * @author : lihuiming
 * @version : 2021/1/16 19:49
 * @modified:
 */
public class ResourceMgr {

  public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
  public static BufferedImage badTankL, badTankU, badTankR, badTankD;
  public static BufferedImage bulletL, bulletU, bulletR, bulletD;
  public static BufferedImage[] explodes = new BufferedImage[16];

  static {
    try {
      goodTankU = ImageIO
          .read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
      goodTankL = ImageUtil.rotateImage(goodTankU, -90);
      goodTankR = ImageUtil.rotateImage(goodTankU, 90);
      goodTankD = ImageUtil.rotateImage(goodTankU, 180);
      badTankU = ImageIO
          .read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
      badTankL = ImageUtil.rotateImage(goodTankU, -90);
      badTankR = ImageUtil.rotateImage(goodTankU, 90);
      badTankD = ImageUtil.rotateImage(goodTankU, 180);
      bulletU = ImageIO
          .read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
      bulletL = ImageUtil.rotateImage(bulletU, -90);
      bulletR = ImageUtil.rotateImage(bulletU, 90);
      bulletD = ImageUtil.rotateImage(bulletU, 180);

      for (int i = 0; i < explodes.length; i++) {
        explodes[i] = ImageIO.read(
            ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

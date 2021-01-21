package com.leon.tank.basic.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * 图片工具类
 *
 * @author : lihuiming
 * @version : 2021/1/18 16:07
 * @modified:
 */
public class ImageUtil {

  /**
   * 图片旋转
   *
   * @param bufferedImage 图片
   * @param degree 旋转角度
   */
  public static BufferedImage rotateImage(final BufferedImage bufferedImage, final int degree) {
    int           w    = bufferedImage.getWidth();
    int           h    = bufferedImage.getHeight();
    int           type = bufferedImage.getColorModel().getTransparency();
    BufferedImage img  = new BufferedImage(w, h, type);
    Graphics2D    graphics2D;
    graphics2D = img.createGraphics();
    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    graphics2D.rotate(Math.toRadians(degree), w >> 1, h >> 1);
    graphics2D.drawImage(bufferedImage, 0, 0, null);
    graphics2D.dispose();
    return img;
  }
}

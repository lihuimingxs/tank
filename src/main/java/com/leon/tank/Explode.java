package com.leon.tank;

import com.leon.tank.config.ResourceMgr;
import java.awt.Graphics;

/**
 * 爆炸类
 *
 * @author : lihuiming
 * @version : 2021/1/16 19:49
 * @modified:
 */
public class Explode {

  private int       x;
  private int       y;
  private TankFrame tf;

  private int step = 0;

  public Explode(int x, int y, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.tf = tf;
    // 播放声音
    new Audio("audio/explode.wav").play();
  }

  public void paint(Graphics g) {
    g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
    if (step >= ResourceMgr.explodes.length) {
      tf.getExplodes().remove(this);
      step = 0;
    }
  }
}

package com.leon.tank;

import static com.leon.tank.consts.BulletConsts.*;
import static com.leon.tank.consts.ExplodeConsts.*;
import static com.leon.tank.consts.TankConsts.*;
import static com.leon.tank.consts.TankFrameConsts.*;

import com.leon.tank.config.ResourceMgr;
import com.leon.tank.enums.GroupEnum;
import com.leon.tank.enums.DirEnum;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 子弹类
 *
 * @author : lihuiming
 * @version : 2021/1/16 19:49
 * @modified:
 */
public class Bullet {

  private int       x;
  private int       y;
  private DirEnum   dir;
  private int       speed;
  private boolean   living;
  private TankFrame tf;
  private GroupEnum group;
  private Rectangle rectangle;

  public Bullet(int x, int y, DirEnum dir, GroupEnum group, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.tf = tf;
    this.speed = 10;
    this.living = true;
    this.group = group;
    this.rectangle = new Rectangle(this.x, this.y, BULLET_UP_WIDTH, BULLET_UP_HEIGHT);
  }

  public void paint(Graphics g) {
    if (!living) {
      return;
    }
    // 简单构建几何图形
//    Color color = g.getColor();
//    g.setColor(Color.WHITE);
//    g.fillOval(x, y, width, height);
//    g.setColor(color);
    // 使用图片
    switch (dir) {
      case LEFT:
        g.drawImage(ResourceMgr.bulletL, x, y, null);
        break;
      case UP:
        g.drawImage(ResourceMgr.bulletU, x, y, null);
        break;
      case RIGHT:
        g.drawImage(ResourceMgr.bulletR, x, y, null);
        break;
      case DOWN:
        g.drawImage(ResourceMgr.bulletD, x, y, null);
        break;
      default:
        break;
    }
    move();
  }

  public void move() {
    switch (dir) {
      case LEFT:
        x -= speed;
        break;
      case UP:
        y -= speed;
        break;
      case RIGHT:
        x += speed;
        break;
      case DOWN:
        y += speed;
        break;
      default:
        break;
    }
    // 更新 rectangle
    rectangle.x = x;
    rectangle.y = y;

    if (x < 0 || y < 0 || x > GAME_WIDTH || y > GAME_HEIGHT) {
      living = false;
    }
  }

  public void collideWith(Tank tank) {
    if (this.group == tank.getGroup()) {
      return;
    }

    // 判断是否相交
    if (this.rectangle.intersects(tank.getRectangle())) {
      tank.die();
      this.die();
      int     eX      = tank.getX() + (TANK_LEFT_WIDTH >> 2) - (EXPLODE_WIDTH >> 2);
      int     eY      = tank.getY() + (TANK_LEFT_HEIGHT >> 2) - (EXPLODE_HEIGHT >> 2);
      Explode explode = new Explode(eX, eY, tf);
      tf.getExplodes().add(explode);
    }
  }

  public boolean isLiving() {
    return this.living;
  }

  public void die() {
    this.living = false;
  }

  public GroupEnum getGroup() {
    return group;
  }

  public void setGroup(GroupEnum group) {
    this.group = group;
  }
}

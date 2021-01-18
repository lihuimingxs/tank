package com.leon.tank;

import static com.leon.tank.consts.BulletConsts.*;
import static com.leon.tank.consts.TankConsts.*;
import static com.leon.tank.consts.TankFrameConsts.*;

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
  private GroupEnum group;
  private Rectangle rectangle;

  public Bullet(int x, int y, DirEnum dir, GroupEnum group) {
    this.x = x;
    this.y = y;
    this.dir = dir;
    this.speed = 10;
    this.living = true;
    this.group = group;
    this.rectangle = new Rectangle(x, y, BULLET_UP_WIDTH, BULLET_UP_HEIGHT);
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

    if (x < 0 || y < 0 || x > GAME_WIDTH || y > GAME_HEIGHT) {
      living = false;
    }
  }

  public void collideWith(Tank tank) {
    if (this.group == tank.getGroup()) {
      return;
    }

    Rectangle bulletRect;
    Rectangle tankRect;
    switch (dir) {
      case LEFT:
        bulletRect = new Rectangle(this.x, this.y, BULLET_LEFT_WIDTH, BULLET_LEFT_HEIGHT);
        break;
      case UP:
        bulletRect = new Rectangle(this.x, this.y, BULLET_UP_WIDTH, BULLET_UP_HEIGHT);
        break;
      case RIGHT:
        bulletRect = new Rectangle(this.x, this.y, BULLET_RIGHT_WIDTH, BULLET_RIGHT_HEIGHT);
        break;
      case DOWN:
        bulletRect = new Rectangle(this.x, this.y, BULLET_DOWN_WIDTH, BULLET_DOWN_HEIGHT);
        break;
      default:
        bulletRect = new Rectangle();
        break;
    }
    switch (tank.getDir()) {
      case LEFT:
        tankRect = new Rectangle(tank.getX(), tank.getY(), TANK_LEFT_WIDTH, TANK_LEFT_HEIGHT);
        break;
      case UP:
        tankRect = new Rectangle(tank.getX(), tank.getY(), TANK_UP_WIDTH, TANK_UP_HEIGHT);
        break;
      case RIGHT:
        tankRect = new Rectangle(tank.getX(), tank.getY(), TANK_RIGHT_WIDTH, TANK_RIGHT_HEIGHT);
        break;
      case DOWN:
        tankRect = new Rectangle(tank.getX(), tank.getY(), TANK_DOWN_WIDTH, TANK_DOWN_HEIGHT);
        break;
      default:
        tankRect = new Rectangle();
        break;
    }
    // 判断是否相交
    if (bulletRect.intersects(tankRect)) {
      tank.die();
      this.die();
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

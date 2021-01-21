package com.leon.tank.basic;

import com.leon.tank.basic.config.ResourceMgr;
import com.leon.tank.basic.consts.BulletConsts;
import com.leon.tank.basic.consts.TankConsts;
import com.leon.tank.basic.consts.TankFrameConsts;
import com.leon.tank.basic.enums.DirEnum;
import com.leon.tank.basic.enums.GroupEnum;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 坦克类
 *
 * @author : lihuiming
 * @version : 2021/1/16 19:29
 * @modified:
 */
public class Tank {

  private int       x;
  private int     y;
  private DirEnum dir;
  private int     speed;
  private int       level;
  private boolean   moving;
  private Color     color;
  private TankFrame tf;
  private boolean   living;
  private Random    random;
  private GroupEnum group;
  private int       firing;
  private Rectangle rectangle;

  public Tank(int x, int y, GroupEnum group, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = DirEnum.UP;
    this.level = TankConsts.LEVEL_1;
    this.moving = true;
    this.speed = 5;
    this.color = Color.WHITE;
    this.tf = tf;
    this.living = true;
    this.random = new Random();
    this.group = group;
    this.rectangle = new Rectangle(this.x, this.y, BulletConsts.BULLET_UP_WIDTH, BulletConsts.BULLET_UP_HEIGHT);
  }

  public boolean paint(Graphics g) {
    if (!living) {
      tf.getEnemies().remove(this);
      return false;
    }
    // 简单构建几何图形
//    Color c = g.getColor();
//    g.setColor(color);
//    g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
//    g.setColor(c);
    // 使用图片
    switch (dir) {
      case LEFT:
        BufferedImage tankL = this.group == GroupEnum.GOOD ? ResourceMgr.goodTankL
                                                           : ResourceMgr.badTankL;
        g.drawImage(tankL, x, y, null);
        break;
      case UP:
        BufferedImage tankU = this.group == GroupEnum.GOOD ? ResourceMgr.goodTankU
                                                           : ResourceMgr.badTankU;
        g.drawImage(ResourceMgr.goodTankU, x, y, null);
        break;
      case RIGHT:
        BufferedImage tankR = this.group == GroupEnum.GOOD ? ResourceMgr.goodTankR
                                                           : ResourceMgr.badTankR;
        g.drawImage(ResourceMgr.goodTankR, x, y, null);
        break;
      case DOWN:
        BufferedImage tankD = this.group == GroupEnum.GOOD ? ResourceMgr.goodTankD
                                                           : ResourceMgr.badTankD;
        g.drawImage(ResourceMgr.goodTankD, x, y, null);
        break;
      default:
        break;
    }
    move();
    return true;
  }

  public void fire() {
    if (DirEnum.LEFT == dir) {
      tf.getBullets().add(
          new Bullet(this.x, this.y + TankConsts.TANK_LEFT_HEIGHT / 2 - BulletConsts.BULLET_LEFT_HEIGHT / 2, this.dir,
              this.group, tf));
    }
    if (DirEnum.UP == dir) {
      tf.getBullets()
          .add(new Bullet(this.x + TankConsts.TANK_UP_WIDTH / 2 - BulletConsts.BULLET_UP_WIDTH / 2, this.y, this.dir,
              this.group, tf));
    }
    if (DirEnum.RIGHT == dir) {
      tf.getBullets().add(new Bullet(this.x + TankConsts.TANK_RIGHT_WIDTH,
          this.y + TankConsts.TANK_RIGHT_HEIGHT / 2 - BulletConsts.BULLET_RIGHT_HEIGHT / 2, this.dir, this.group, tf));
    }
    if (DirEnum.DOWN == dir) {
      tf.getBullets().add(new Bullet(this.x + TankConsts.TANK_DOWN_WIDTH / 2 - BulletConsts.BULLET_DOWN_WIDTH / 2,
          this.y + TankConsts.TANK_DOWN_HEIGHT, this.dir, this.group, tf));
    }
  }

  public void die() {
    this.living = false;
  }

  private void move() {
    if (!moving) {
      return;
    }
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
    this.boundCheck();
    // 更新 rectangle
    rectangle.x = x;
    rectangle.y = y;

    if (GroupEnum.GOOD == this.group) {
      return;
    }
    // 敌方坦克随机改变方向，固定间隔开火
    int dirRandom = random.nextInt(300);
    if (dirRandom < 5) {
      this.dir = DirEnum.LEFT;
    } else if (dirRandom < 10) {
      this.dir = DirEnum.UP;
    } else if (dirRandom < 15) {
      this.dir = DirEnum.RIGHT;
    } else if (dirRandom < 20) {
      this.dir = DirEnum.DOWN;
    } else {
      if (firing < 20) {
        firing++;
      } else {
        this.fire();
        firing = 0;
      }
    }
  }

  private void boundCheck() {
    if (this.x < 0) {
      x = 0;
    }
    if (this.y < 20) {
      y = 20;
    }
    if (this.x > TankFrameConsts.GAME_WIDTH - TankConsts.TANK_RIGHT_WIDTH) {
      x = TankFrameConsts.GAME_WIDTH - TankConsts.TANK_RIGHT_WIDTH;
    }
    if (this.y > TankFrameConsts.GAME_HEIGHT - TankConsts.TANK_DOWN_HEIGHT) {
      y = TankFrameConsts.GAME_HEIGHT - TankConsts.TANK_DOWN_HEIGHT;
    }
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public DirEnum getDir() {
    return dir;
  }

  public void setDir(DirEnum dir) {
    this.dir = dir;
  }

  public boolean isMoving() {
    return moving;
  }

  public void setMoving(boolean moving) {
    this.moving = moving;
  }

  public GroupEnum getGroup() {
    return group;
  }

  public void setGroup(GroupEnum group) {
    this.group = group;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  public void setRectangle(Rectangle rectangle) {
    this.rectangle = rectangle;
  }
}

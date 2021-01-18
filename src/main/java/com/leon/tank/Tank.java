package com.leon.tank;

import static com.leon.tank.consts.BulletConsts.*;
import static com.leon.tank.consts.TankConsts.*;
import static com.leon.tank.consts.TankFrameConsts.*;

import com.leon.tank.enums.GroupEnum;
import com.leon.tank.enums.DirEnum;
import java.awt.Color;
import java.awt.Graphics;
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
  private int       y;
  private DirEnum   dir;
  private int       speed;
  private int       level;
  private boolean   moving;
  private Color     color;
  private TankFrame tf;
  private boolean   living;
  private Random    random;
  private GroupEnum group;
  private int       firing;

  public Tank(int x, int y, GroupEnum group, TankFrame tf) {
    this.x = x;
    this.y = y;
    this.dir = DirEnum.UP;
    this.level = LEVEL_1;
    this.moving = true;
    this.speed = 5;
    this.color = Color.WHITE;
    this.tf = tf;
    this.living = true;
    this.random = new Random();
    this.group = group;
  }

  public void paint(Graphics g) {
    if (!living) {
      tf.getEnemies().remove(this);
      return;
    }
    // 简单构建几何图形
//    Color c = g.getColor();
//    g.setColor(color);
//    g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
//    g.setColor(c);
    // 使用图片
    switch (dir) {
      case LEFT:
        g.drawImage(ResourceMgr.tankL, x, y, null);
        break;
      case UP:
        g.drawImage(ResourceMgr.tankU, x, y, null);
        break;
      case RIGHT:
        g.drawImage(ResourceMgr.tankR, x, y, null);
        break;
      case DOWN:
        g.drawImage(ResourceMgr.tankD, x, y, null);
        break;
      default:
        break;
    }
    move();
  }

  public void fire() {
    if (DirEnum.LEFT == dir) {
      tf.getBullets().add(
          new Bullet(this.x, this.y + TANK_LEFT_HEIGHT / 2 - BULLET_LEFT_HEIGHT / 2, this.dir,
              this.group));
    }
    if (DirEnum.UP == dir) {
      tf.getBullets()
          .add(new Bullet(this.x + TANK_UP_WIDTH / 2 - BULLET_UP_WIDTH / 2, this.y, this.dir,
              this.group));
    }
    if (DirEnum.RIGHT == dir) {
      tf.getBullets().add(new Bullet(this.x + TANK_RIGHT_WIDTH,
          this.y + TANK_RIGHT_HEIGHT / 2 - BULLET_RIGHT_HEIGHT / 2, this.dir, this.group));
    }
    if (DirEnum.DOWN == dir) {
      tf.getBullets().add(new Bullet(this.x + TANK_DOWN_WIDTH / 2 - BULLET_DOWN_WIDTH / 2,
          this.y + TANK_DOWN_HEIGHT, this.dir, this.group));
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
        if (x > 0) {
          x -= speed;
        }
        break;
      case UP:
        if (y > 0) {
          y -= speed;
        }
        break;
      case RIGHT:
        if (x < GAME_WIDTH) {
          x += speed;
        }
        break;
      case DOWN:
        if (y < GAME_HEIGHT) {
          y += speed;
        }
        break;
      default:
        break;
    }

    if (GroupEnum.BAD == this.group) {
      int dirRandom = random.nextInt(200);
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
}

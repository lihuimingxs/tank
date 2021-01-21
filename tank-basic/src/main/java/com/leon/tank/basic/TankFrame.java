package com.leon.tank.basic;

import com.leon.tank.basic.consts.TankFrameConsts;
import com.leon.tank.basic.enums.GroupEnum;
import com.leon.tank.basic.enums.DirEnum;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 坦克大战页面
 *
 * @author : lihuiming
 * @version : 2021/1/16 19:49
 * @modified:
 */
public class TankFrame extends Frame {

  private Image         offScreenImage = null;
  private Tank          myTank         = new Tank(
      TankFrameConsts.TANK_PLAYER_1_X, TankFrameConsts.TANK_PLAYER_1_Y, GroupEnum.GOOD,
      this);
  private List<Bullet>  bullets        = new ArrayList<>();
  private List<Tank>    enemies        = new ArrayList<>();
  private List<Explode> explodes       = new ArrayList<>();

  public TankFrame() {
    this.setSize(TankFrameConsts.GAME_WIDTH, TankFrameConsts.GAME_HEIGHT);
    this.setResizable(false);
    this.setTitle("tank war");
    this.setVisible(true);

    this.addKeyListener(new MyKeyListener());

    this.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  @Override
  public void update(Graphics g) {
    if (offScreenImage == null) {
      offScreenImage = this.createImage(TankFrameConsts.GAME_WIDTH, TankFrameConsts.GAME_HEIGHT);
    }
    Graphics gOffScreen = offScreenImage.getGraphics();
    Color    c          = gOffScreen.getColor();
    gOffScreen.setColor(Color.BLACK);
    gOffScreen.fillRect(0, 0, TankFrameConsts.GAME_WIDTH, TankFrameConsts.GAME_HEIGHT);
    gOffScreen.setColor(c);
    paint(gOffScreen);
    g.drawImage(offScreenImage, 0, 0, null);
  }

  @Override
  public void paint(Graphics g) {
    Color c = g.getColor();
    g.setColor(Color.WHITE);
    g.drawString("子弹的数量" + bullets.size(), 10, 60);
    g.drawString("敌人的数量" + enemies.size(), 10, 80);
    g.drawString("爆炸的数量" + explodes.size(), 10, 100);
    g.setColor(c);
    myTank.paint(g);
    if (bullets != null && bullets.size() > 0) {
      bullets.removeIf(bullet -> !bullet.isLiving());
      for (Bullet bullet : bullets) {
        bullet.paint(g);
      }
    }
    if (enemies != null && enemies.size() > 0) {
      for (Iterator<Tank> i = enemies.iterator(); i.hasNext(); ) {
        Tank    enemy   = i.next();
        boolean isPaint = enemy.paint(g);
        if (!isPaint) {
          i.remove();
        }
      }
    }
    if (explodes != null && explodes.size() > 0) {
      for (Explode explode : explodes) {
        explode.paint(g);
      }
    }
    if (bullets != null && bullets.size() > 0) {
      for (Bullet bullet : bullets) {
        if (enemies != null && enemies.size() > 0) {
          for (Tank enemy : enemies) {
            bullet.collideWith(enemy);
          }
        }
        if (myTank != null) {
          bullet.collideWith(myTank);
        }
      }
    }
  }

  public List<Bullet> getBullets() {
    return bullets;
  }

  public List<Tank> getEnemies() {
    return enemies;
  }

  public List<Explode> getExplodes() {
    return explodes;
  }

  class MyKeyListener extends KeyAdapter {

    boolean bL = false;
    boolean bU = false;
    boolean bR = false;
    boolean bD = false;

    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      switch (key) {
        case KeyEvent.VK_LEFT:
          bL = true;
          break;
        case KeyEvent.VK_UP:
          bU = true;
          break;
        case KeyEvent.VK_RIGHT:
          bR = true;
          break;
        case KeyEvent.VK_DOWN:
          bD = true;
          break;
        default:
          break;
      }

      setMainTankDir();
    }

    @Override
    public void keyReleased(KeyEvent e) {
      int key = e.getKeyCode();
      switch (key) {
        case KeyEvent.VK_LEFT:
          bL = false;
          break;
        case KeyEvent.VK_UP:
          bU = false;
          break;
        case KeyEvent.VK_RIGHT:
          bR = false;
          break;
        case KeyEvent.VK_DOWN:
          bD = false;
          break;
        case KeyEvent.VK_SPACE:
          myTank.fire();
          break;
        default:
          break;
      }
      setMainTankDir();
    }

    private void setMainTankDir() {
      if (!bL && !bU && !bR && !bD) {
        myTank.setMoving(false);
      } else {
        myTank.setMoving(true);
        if (bL) {
          myTank.setDir(DirEnum.LEFT);
        }
        if (bU) {
          myTank.setDir(DirEnum.UP);
        }
        if (bR) {
          myTank.setDir(DirEnum.RIGHT);
        }
        if (bD) {
          myTank.setDir(DirEnum.DOWN);
        }
      }
    }
  }
}

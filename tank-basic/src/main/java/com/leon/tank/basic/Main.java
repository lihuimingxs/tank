package com.leon.tank.basic;

import static com.leon.tank.basic.consts.TankFrameConsts.*;

import com.leon.tank.basic.config.PropertyMgr;
import com.leon.tank.basic.enums.GroupEnum;
import java.util.Objects;

/**
 * Main 方法
 *
 * @author : lihuiming
 * @version : 2021/1/18 20:11
 * @modified:
 */
public class Main {

  public static void main(String[] args) throws InterruptedException {
    TankFrame tf = new TankFrame();

    System.out.println(PropertyMgr.get("initEnemiesCount"));
    // 初始化敌方坦克
    int initTankCount = Integer
        .parseInt(Objects.requireNonNull(PropertyMgr.get("initEnemiesCount")).toString());
    int x = 0, y = 20;
    for (int i = 0; i < initTankCount; i++) {
      tf.getEnemies().add(new Tank(x, y, GroupEnum.BAD, tf));
      x += 250;
      if (x > GAME_WIDTH) {
        x = 0;
      }
    }

    while (true) {
      Thread.sleep(50);
      tf.repaint();
    }
  }
}

package com.leon.tank;

import static com.leon.tank.consts.TankFrameConsts.*;

import com.leon.tank.config.PropertyMgr;
import com.leon.tank.enums.GroupEnum;
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

    // 初始化敌方坦克
    int initTankCount = Integer
        .parseInt(Objects.requireNonNull(PropertyMgr.get("initTankCount")).toString());
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

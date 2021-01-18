package com.leon.tank;

import static com.leon.tank.consts.TankFrameConsts.*;

import com.leon.tank.enums.GroupEnum;
import java.util.Random;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    TankFrame tf = new TankFrame();

    // 初始化敌方坦克
    int x = 0, y = 20;
    for (int i = 0; i < 5; i++) {
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

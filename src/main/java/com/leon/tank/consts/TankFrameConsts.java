package com.leon.tank.consts;

import static com.leon.tank.consts.TankConsts.TANK_UP_HEIGHT;

import com.leon.tank.ResourceMgr;

/**
 * 坦克类常量
 *
 * @author : lihuiming
 * @version : 2021/1/16 21:01
 * @modified:
 */
public class TankFrameConsts {

  /**
   * 游戏界面宽度
   */
  public static final int GAME_WIDTH  = 1080;
  /**
   * 游戏界面高度
   */
  public static final int GAME_HEIGHT = 960;
  /**
   * 坦克 player1 X 坐标
   */
  public static final int TANK_PLAYER_1_X   = GAME_WIDTH / 2 - 50;
  /**
   * 坦克 player1 Y 坐标
   */
  public static final int TANK_PLAYER_1_Y   = GAME_HEIGHT - TANK_UP_HEIGHT;
  /**
   * 坦克 player2 X 坐标
   */
  public static final int TANK_PLAYER_2_X   = GAME_WIDTH / 2 + 50;
  /**
   * 坦克 player1 Y 坐标
   */
  public static final int TANK_PLAYER_2_Y   = GAME_HEIGHT - TANK_UP_HEIGHT;
}

package com.leon.tank.consts;

import com.leon.tank.ResourceMgr;

/**
 * 坦克类常量
 *
 * @author : lihuiming
 * @version : 2021/1/16 21:01
 * @modified:
 */
public class TankConsts {

  /**
   * 等级 1 -> 射速 5，可打泥土，不可打石头
   */
  public static final int LEVEL_1 = 1;
  /**
   * 等级 2 -> 射速 10，可打泥土，不可打石头
   */
  public static final int LEVEL_2 = 2;
  /**
   * 等级 3 -> 射速 10，可打泥土，可打石头
   */
  public static final int LEVEL_3 = 3;
  /**
   * 坦克向左-宽度
   */
  public static final int TANK_LEFT_WIDTH   = ResourceMgr.tankL.getWidth();
  /**
   * 坦克向左-高度
   */
  public static final int TANK_LEFT_HEIGHT  = ResourceMgr.tankL.getHeight();
  /**
   * 坦克向上-宽度
   */
  public static final int TANK_UP_WIDTH     = ResourceMgr.tankU.getWidth();
  /**
   * 坦克向上-高度
   */
  public static final int TANK_UP_HEIGHT    = ResourceMgr.tankU.getHeight();
  /**
   * 坦克向右-宽度
   */
  public static final int TANK_RIGHT_WIDTH  = ResourceMgr.tankR.getWidth();
  /**
   * 坦克向右-高度
   */
  public static final int TANK_RIGHT_HEIGHT = ResourceMgr.tankR.getHeight();
  /**
   * 坦克向下-宽度
   */
  public static final int TANK_DOWN_WIDTH   = ResourceMgr.tankD.getWidth();
  /**
   * 坦克向下-高度
   */
  public static final int TANK_DOWN_HEIGHT  = ResourceMgr.tankD.getHeight();
}

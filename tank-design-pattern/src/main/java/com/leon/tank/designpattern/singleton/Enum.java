package com.leon.tank.designpattern.singleton;

/**
 * 枚举式（线程安全，防止反序列化）
 *
 * @author : lihuiming
 * @version : 2021/1/20 23:34
 * @modified:
 */
public enum Enum {

  /**
   * 单例
   */
  INSTANCE;

  public void m() {
    System.out.println("m");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> System.out.println(Enum.INSTANCE.hashCode())).start();
    }
  }
}

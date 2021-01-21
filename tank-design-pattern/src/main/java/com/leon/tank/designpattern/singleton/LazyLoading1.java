package com.leon.tank.designpattern.singleton;

/**
 * 懒汉式（按需初始化，线程不安全）
 *
 * @author : lihuiming
 * @version : 2021/1/20 23:34
 * @modified:
 */
public class LazyLoading1 {

  public static LazyLoading1 INSTANCE;

  private LazyLoading1() {
  }

  public static LazyLoading1 getInstance() {
    if (INSTANCE == null) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      INSTANCE = new LazyLoading1();
    }
    return INSTANCE;
  }

  public void m() {
    System.out.println("m");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> System.out.println(LazyLoading1.getInstance().hashCode())).start();
    }
  }
}

package com.leon.tank.designpattern.singleton;

/**
 * 懒汉式-sychronized（按需初始化，线程安全，但效率降低）
 *
 * @author : lihuiming
 * @version : 2021/1/20 23:34
 * @modified:
 */
public class LazyLoading2 {

  public static LazyLoading2 INSTANCE;

  private LazyLoading2() {
  }

  public static synchronized LazyLoading2 getInstance() {
    if (INSTANCE == null) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      INSTANCE = new LazyLoading2();
    }
    return INSTANCE;
  }

  public void m() {
    System.out.println("m");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> System.out.println(LazyLoading2.getInstance().hashCode())).start();
    }
  }
}

package com.leon.tank.designpattern.singleton;

/**
 * 懒汉式-双重检查（按需初始化，线程安全）
 *
 * @author : lihuiming
 * @version : 2021/1/20 23:34
 * @modified:
 */
public class LazyLoading3 {

  public static LazyLoading3 INSTANCE;

  private LazyLoading3() {
  }

  public static LazyLoading3 getInstance() {
    if (INSTANCE == null) {
      synchronized (LazyLoading3.class) {
        if (INSTANCE == null) {
          try {
            Thread.sleep(1);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          INSTANCE = new LazyLoading3();
        }
      }
    }
    return INSTANCE;
  }

  public void m() {
    System.out.println("m");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> System.out.println(LazyLoading3.getInstance().hashCode())).start();
    }
  }
}

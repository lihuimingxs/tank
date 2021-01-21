package com.leon.tank.designpattern.singleton;

/**
 * 懒汉式-静态内部类（按需初始化，线程安全）
 *
 * @author : lihuiming
 * @version : 2021/1/20 23:34
 * @modified:
 */
public class LazyLoading4 {

  private LazyLoading4() {
  }

  private static class LazyLoading4Holder {
    private final static LazyLoading4 INSTANCE = new LazyLoading4();
  }

  public static LazyLoading4 getInstance() {
    return LazyLoading4Holder.INSTANCE;
  }

  public void m() {
    System.out.println("m");
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(() -> System.out.println(LazyLoading4.getInstance().hashCode())).start();
    }
  }
}

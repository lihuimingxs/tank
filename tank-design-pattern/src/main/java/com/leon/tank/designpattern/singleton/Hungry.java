package com.leon.tank.designpattern.singleton;

/**
 * 饿汉式（类加载到内存后，实例化一个单例，JVM保证线程安全，推荐使用。唯一缺点是不管用到与否，类装载时就完成初始化。）
 *
 * @author : lihuiming
 * @version : 2021/1/20 23:34
 * @modified:
 */
public class Hungry {

  public static final Hungry INSTANCE = new Hungry();

  private Hungry() {
  }

  public static Hungry getInstance() {
    return INSTANCE;
  }

  public void m() {
    System.out.println("m");
  }

  public static void main(String[] args) {
    Hungry hungry1 = Hungry.getInstance();
    Hungry hungry2 = Hungry.getInstance();
    System.out.println(hungry1 == hungry2);
  }
}

package com.ognice.basic;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/13
 */
public class TestFinal {
    private final String S = "final实例变量S";
    private final int A = 100;
    public final int B = 90;

    public static final int C = 80;
    private static final int D = 70;

    public final int E; //final空白,必须在初始化对象的时候赋初值

    public TestFinal(int x) {
        E = x;
    }

    public static void main(String[] args) {
        TestFinal t = new TestFinal(2);
        //t.A=101;    //出错,final变量的值一旦给定就无法改变
        //t.B=91; //出错,final变量的值一旦给定就无法改变
        //t.C=81; //出错,final变量的值一旦给定就无法改变
        //t.D=71; //出错,final变量的值一旦给定就无法改变

        System.out.println(t.A);
        System.out.println(t.B);
        System.out.println(t.C); //不推荐用对象方式访问静态字段
        System.out.println(t.D); //不推荐用对象方式访问静态字段
        System.out.println(TestFinal.C);
        System.out.println(TestFinal.D);
        //System.out.println(TestFinal.E); //出错,因为E为final空白,依据不同对象值有所不同.
        System.out.println(t.E);

        TestFinal t1 = new TestFinal(3);
        System.out.println(t1.E); //final空白变量E依据对象的不同而不同
    }

    private void test() {
        System.out.println(new TestFinal(1).A);
        System.out.println(TestFinal.C);
        System.out.println(TestFinal.D);
    }

    public void test2() {
        final int a;     //final空白,在需要的时候才赋值
        final int b = 4;    //局部常量--final用于局部变量的情形
        final int c;    //final空白,一直没有给赋值.
        a = 3;
        //a=4;    出错,已经给赋过值了.
        //b=2; 出错,已经给赋过值了.
    }

}

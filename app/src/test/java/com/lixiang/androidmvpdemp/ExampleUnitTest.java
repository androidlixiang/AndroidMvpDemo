package com.lixiang.androidmvpdemp;


import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        StringBuilder builder=new StringBuilder();
        builder.append("1");
        builder.append("2");
        builder.append("3");
        builder.append("4");
        System.out.println(builder.toString());
        builder.setLength(3);
        System.out.println(builder.toString());
    }
}
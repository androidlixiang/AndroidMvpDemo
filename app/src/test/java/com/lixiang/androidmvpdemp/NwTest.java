package com.lixiang.androidmvpdemp;

/**
 * Copyright (C),  国民健康科技有限公司
 *
 * @ProjectName: 脉诊
 * @Description: java类作用描述
 * @Author: lixinag
 * @CreateDate: 2019/12/23 10:45
 */
public class NwTest {

    // 发货地点
    public String location = "重庆";

    // 所有货物在不同一趟车上，货物到了下一站，分别更新对应的快递信息
    public synchronized void changeLocationNotify(String location) {
        this.location = location;
        this.notify();
    }

    // 所有货物在同一趟快递车上，货物到了下一站，全部信息更新。
    public synchronized void changeLocationNotifyAll(String location) {
        this.location = location;
        System.out.println("changeLocationNotifyAll");
        this.notifyAll();
    }


    public static class LocationThread extends Thread {
        public final NwTest mNwTest;

        public LocationThread(NwTest nwTest) {
            this.mNwTest = nwTest;
        }

        @Override
        public void run() {
            super.run();
            try {
                synchronized (mNwTest) {
                    System.out.println("LocationThread  current location : " + mNwTest.location);
                    // 等待位置更新
                    mNwTest.wait();
                    String name = Thread.currentThread().getName();
                    System.out.println("LocationThread——>current thread name : " + name);

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            // 获取更新后的位置
            System.out.println("LocationThread  update location : " + mNwTest.location);


        }
    }


}

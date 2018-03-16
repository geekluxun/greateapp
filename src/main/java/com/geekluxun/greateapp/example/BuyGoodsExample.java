package com.geekluxun.greateapp.example;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class BuyGoodsExample {
    private AtomicLong stockCount = new AtomicLong(888);
    private AtomicLong allBuyedCount = new AtomicLong(0);

    public static void main(String[] argc){
        BuyGoodsExample exmaple = new BuyGoodsExample();
        exmaple.init();

    }

    public void init(){
        ExecutorService executorService = new ThreadPoolExecutor(
                10,
                10,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        /* 模拟5个用户同时购买*/
        for (int i = 0; i < 5; i++) {
            executorService.execute(new BuyGoods());
        }
    }


    private class BuyGoods implements Runnable{

        @Override
        public void run() {
            long buyedCount = 0;
            while (true){
                if (stockCount.decrementAndGet() >= 0){
                    buyedCount++;
                } else {
                    System.out.println("当前用户购买到商品" + Thread.currentThread().getId()  + ":" + buyedCount);
                    System.out.println("已买总数：" + allBuyedCount.addAndGet(buyedCount));
                    break;
                }

                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //读库存
    //public Long selectCount(Long itemId);
    //减库存
    //public Long updateStock(Long itemId);

}






//#读库存（方法签名为 Long selectCount(Long itemId);）
//select count
//from t_stock
//where itemId = #{itemId}
//
//#更新库存（方法签名为 Long updateStock(Long itemId)）
//update t_stock
//set count = count -1
//where itemId = #{itemId} and version = #{version}
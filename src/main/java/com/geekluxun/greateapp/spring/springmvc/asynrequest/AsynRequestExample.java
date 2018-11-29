package com.geekluxun.greateapp.spring.springmvc.asynrequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Copyright,2018-2019,geekluxun Co.,Ltd.
 *
 * @Author: luxun
 * @Create: 2018-11-29 18:45
 * @Description:
 * @Other:
 */
@Controller
@RequestMapping("/async")
@Slf4j
public class AsynRequestExample {
    
    private AtomicLong count = new AtomicLong(0); 
    
    @GetMapping("/async-deferredresult")
    @ApiOperation("异步请求示例")
    public DeferredResult<ResponseEntity<?>> handleReqDefResult(Model model) {
        log.info("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        // 请求处理在另外一个线程
        ForkJoinPool.commonPool().submit(() -> {
            log.info("Processing in separate thread");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                
            }
            output.setResult(ResponseEntity.ok("ok"));
        });

        log.info("servlet thread freed");
        return output;
    }

    @PostMapping("/fileUpload")
    @ApiOperation("文件异步上传")
    public Callable<ResponseEntity<String>> processUpload(final MultipartFile file) {

        //处理在一个新的线程
        return new Callable<ResponseEntity<String>>() {
            public ResponseEntity<String> call() throws Exception {
                log.info("=======文件异步上传=====");
                Thread.sleep(5000);
                return ResponseEntity.ok("文件上传完毕!!!");
            }
        };
    }


    @GetMapping("/events")
    @ApiOperation("多个线程异步处理产生流响应示例")
    public ResponseBodyEmitter handle() throws Exception{
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        CountDownLatch downLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            executorService.submit(new Task(emitter, downLatch));
        }
        
        ForkJoinPool.commonPool().submit(()->{
            try {
                log.info("====等待所有任务处理完成...");
                downLatch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("====所有任务都处理完成了======");
            emitter.complete();
        });
        return emitter;
    }
    
    
    private class Task implements Runnable{
        private ResponseBodyEmitter emitter;
        private CountDownLatch downLatch;
        public Task(ResponseBodyEmitter emitter, CountDownLatch downLatch){
            this.emitter = emitter;
            this.downLatch = downLatch;
        }
        
        @Override
        public void run() {
            try {
                emitter.send("数据" + count.incrementAndGet() +"值:" +  Integer.valueOf(new Random().nextInt()));
                Thread.sleep(2000);
                downLatch.countDown();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @GetMapping("/download")
    @ApiOperation("文件下载直接返回一个流响应示例")
    public StreamingResponseBody handle2() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                outputStream.write("我是文件内容".getBytes());
                outputStream.flush();
                outputStream.close();
            }
        };
    }
    
}

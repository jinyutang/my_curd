package com.hxkj.common.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * @author chuang
 * @date 2018/9/10
 */
public class ExecutorServiceUtils {
    private final static Logger LOG = Logger.getLogger(ExecutorServiceUtils.class);

    // 固定大小线程池
    public  static ExecutorService pool;
    static {
         LOG.info("----- 初始化线程池 -----");
         pool =new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
                new ThreadFactoryBuilder().setNameFormat("my_curd-%d").setDaemon(true).build());
    }
}

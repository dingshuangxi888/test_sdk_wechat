package biz.bbtec.ncwc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Dean on 2014/5/8.
 */
public class ThreadPoolUtil {
    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);

    private ExecutorService pool;

    public static ExecutorService getPool() {
        return ThreadPoolSingletonHolder.instance;
    }

    private static class ThreadPoolSingletonHolder {
        private static ExecutorService instance = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
    }
}

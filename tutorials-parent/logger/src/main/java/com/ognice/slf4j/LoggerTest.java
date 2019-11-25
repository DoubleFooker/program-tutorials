package com.ognice.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/14
 */
public class LoggerTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void testLogger() {
        logger.error("这是个error级别错误日志测试，{}-{}", "hello", "word");
    }

    public static void main(String[] args) {
        LoggerTest loggerTest = new LoggerTest();
        loggerTest.testLogger();
    }
}

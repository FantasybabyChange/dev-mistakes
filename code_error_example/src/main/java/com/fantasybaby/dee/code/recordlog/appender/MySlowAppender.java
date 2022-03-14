package com.fantasybaby.dee.code.recordlog.appender;

import ch.qos.logback.core.ConsoleAppender;

import java.util.concurrent.TimeUnit;

/**
 * Created on 3/31/2021.
 *
 * @author Reid Liu
 */
public class MySlowAppender extends ConsoleAppender {
    @Override
    protected void subAppend(Object event) {
        try { // 模拟慢日志
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.subAppend(event);
    }
}

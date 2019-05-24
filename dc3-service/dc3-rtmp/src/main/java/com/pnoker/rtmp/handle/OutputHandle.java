package com.pnoker.rtmp.handle;

import com.pnoker.rtmp.bean.CommandTask;
import com.pnoker.rtmp.bean.Tasker;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <p>Copyright(c) 2018. Pnoker All Rights Reserved.
 * <p>Author     : Pnoker
 * <p>Email      : pnokers@gmail.com
 * <p>Description: Process输出处理线程
 */
@Slf4j
@Data
public class OutputHandle implements Runnable {
    private String taskId;
    /**
     * 运行状态，false：运行失败
     */
    private volatile boolean status = true;
    private Process process;

    public OutputHandle(String taskId, Process process) {
        this.taskId = taskId;
        this.process = process;
    }

    @Override
    public void run() {
        if (status) {
            read(process.getErrorStream());
        }
    }

    public void read(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while (null != (line = reader.readLine())) {
                log.info(line);
                handle(line);
                if (!status) {
                    throw new IOException();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                inputStream.close();
                process.destroyForcibly();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public void handle(String message) {
        message = message.toLowerCase();
        if (message.contains("fail") || message.contains("miss")) {
            this.status = false;
            Tasker tasker = CommandTask.taskMap.get(taskId);
            tasker.setTimes(tasker.getTimes() + 1);
            tasker.setStatus(3);
            try {
                CommandTask.taskQueue.put(tasker);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
package com.wakeup.hellojenkins.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * wget工具类
 *
 * @author www@yiynx.cn
 */
public class WGetUtil {
    private WGetUtil() {
    }

    /**
     * wget
     *
     * @param option   wget参数
     * @param url      下载地址
     * @param logConsumer 输出日志消费者
     * @throws IOException
     * @throws InterruptedException
     * @throws RuntimeException     wget执行异常
     */
    public static void wget(String option, String url, Consumer<String> logConsumer) throws IOException, InterruptedException {
        option = option != null ? option + " " : "";
        String cmd = "wget " + option + url;
        Process process = Runtime.getRuntime().exec(cmd);
        String lastMsg = reader(process.getErrorStream(), logConsumer);
        if (0 != process.waitFor()) {
            throw new RuntimeException("wget执行异常：" + lastMsg);
        }
    }

    /**
     * wget 百分比进度输出（每个百分比只输出一条）
     *
     * @param option   wget参数
     * @param url      下载地址
     * @param progressConsumer 下载进度消费者
     * @throws IOException
     * @throws InterruptedException
     */
    public static void wgetProgressRation(String option, String url, Consumer<Progress> progressConsumer) throws IOException, InterruptedException {
        Map<String, Boolean> progressRationMap = new HashMap<>(135); // 101 / 0.75 + 1
        wget(option, url, line -> {
            if (line.contains("%")) {
                // 格式：12900K .......... .......... .......... .......... ..........  2% 5.49M 9m38s
                String progressRation = line.replaceAll(".* ([0-9]{1,3})% .*", "$1");
                if (progressRationMap.putIfAbsent(progressRation, true) == null) {
                    progressConsumer.accept(new Progress(progressRation, line));
                }
            } else if (line.contains("already fully retrieved")) {
                progressConsumer.accept(new Progress("100", "文件已完全下载(无需下载)"));
            }
        });
    }

    private static String reader(InputStream inputStream, Consumer<String> consumer) throws IOException {
        AtomicReference<String> lastMsg = new AtomicReference<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            bufferedReader.lines().filter(line -> !line.isEmpty()).forEach(line -> {
                lastMsg.set(line);
                if (consumer != null) {
                    consumer.accept(line);
                }
            });
        }
        return lastMsg.get();
    }

    static class Progress {
        private String progress;
        private String log;

        public Progress(String progress, String log) {
            this.progress = progress;
            this.log = log;
        }

        public String toJSONString() {
            return "{\"progress\":" + progress + ", \"log\":\"" + log + "\"}";
        }
    }
}
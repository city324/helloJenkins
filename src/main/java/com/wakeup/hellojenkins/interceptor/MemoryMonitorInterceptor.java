package com.wakeup.hellojenkins.interceptor;

import cn.hutool.core.io.unit.DataSizeUtil;
import cn.hutool.core.util.ByteUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class MemoryMonitorInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 在请求处理之前执行的逻辑
    // 检查当前JVM内存占用是否超过80%

    double memoryUsage = (double) (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / Runtime.getRuntime().maxMemory();
    System.out.println("总内存："+DataSizeUtil.format(Runtime.getRuntime().totalMemory()));
    System.out.println("剩余内存："+DataSizeUtil.format(Runtime.getRuntime().freeMemory()));
    if (memoryUsage > 0.8) {
      // 当内存占用超过80%时，进行处理，例如返回错误信息、跳转到错误页面等
      response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Server is overloaded");
      System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX+>请求拒绝！");
      return false; // 中断请求处理链
    }
    System.out.println("√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√请求接受！");
    return true; // 允许请求继续处理
  }
}

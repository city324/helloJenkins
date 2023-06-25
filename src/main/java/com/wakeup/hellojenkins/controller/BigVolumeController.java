package com.wakeup.hellojenkins.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.wakeup.hellojenkins.pojo.BigVolume;
import java.util.ArrayList;
import java.util.List;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/big_volume")
public class BigVolumeController {

  @PostMapping("/create")
  public String register(@RequestParam int num) {
    String BigVolumeLayout = ClassLayout.parseClass(BigVolume.class).toPrintable();
//    System.out.println(BigVolumeLayout);
    List<BigVolume> list = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      list.add(new BigVolume(RandomUtil.randomInt(10000), RandomUtil.randomString(5), RandomUtil.randomInt(10000), RandomUtil.randomString(5)));
    }
    String jsonStr = JSONUtil.toJsonStr(list);
    System.out.println("==============================");
    return jsonStr;
  }
}

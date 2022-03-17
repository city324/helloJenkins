package com.wakeup.hellojenkins.inter;

import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

@Component
public class TestSpi {

    public  static  void getSpi() {
        ServiceLoader<SpiInterface> spi = ServiceLoader.load(SpiInterface.class);
        spi.forEach(s -> s.show("AAAAAAAAAAAAAAAA"));
    }
}

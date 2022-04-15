package com.wakeup.hellojenkins.inter;

import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

@Component
public class TestSpi {

    public static ServiceLoader<SpiInterface> spi = null;

    static {
        spi = ServiceLoader.load(SpiInterface.class);
    }

    public static void getSpiShwo() {
        spi.forEach(s -> s.show("AAAAAAAAAAAAAAAA"));
    }
}

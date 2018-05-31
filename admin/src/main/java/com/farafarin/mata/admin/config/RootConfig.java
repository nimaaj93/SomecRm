package com.farafarin.mata.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by K550 VX on 10/31/2017.
 */
@ComponentScan(basePackages = {"com.farafarin.mata.admin.back",
        "com.farafarin.mata.common"})
@Configuration
public class RootConfig {
}

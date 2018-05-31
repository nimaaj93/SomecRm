package com.farafarin.mata.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by azhdari on 10/9/2017.
 */
@ComponentScan(basePackages = {"com.farafarin.mata.web.back",
                                "com.farafarin.mata.common","com.farafarin.mata.test"})
@Configuration
public class RootConfig {
}

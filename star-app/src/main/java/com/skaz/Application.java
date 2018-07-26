package com.skaz;

import com.skaz.utils.Apps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author jungle
 */
@Slf4j
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        Apps.newSpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
}

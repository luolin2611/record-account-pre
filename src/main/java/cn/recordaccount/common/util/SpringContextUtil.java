package cn.recordaccount.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 获取Spring的ApplicationContext对象工具
 */
@Configuration
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 获取spring.profiles.active
     * @return
     */
    public static String getActiveProfile(){
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}

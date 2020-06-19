package com.JavaEE.homework.init;

import com.JavaEE.homework.constant.WebConstant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class InitConfig implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        // 项目路径
        WebConstant.WEB_FILE_ROOT = ClassUtils.getDefaultClassLoader().getResource("").getPath().substring(1);
    }

}

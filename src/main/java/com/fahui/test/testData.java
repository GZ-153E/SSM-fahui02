package com.fahui.test;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class testData {

    @Test
    public void test(){
        ApplicationContext ac = null;
        {
            ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        }
        DataSource dataSource=ac.getBean(DataSource.class);
        }
}

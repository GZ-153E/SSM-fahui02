package com.fahui.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    @Test
    public void date() throws ParseException {
        //字符串转date类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-10-31");
        System.out.println(date);
    }
    @Test
    public void date1(){
        //date类型转字符串
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        System.out.println(dateString);
    }

    @Test
    public void date2() throws ParseException {
//        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
//        System.out.println(bartDateFormat.format(date));

        String date1 = "2018/2/3";
        String date2 = "2018/2/27";
        int i = date1.compareTo(date2);
        System.out.println(i);
    }

    @Test
    public void date3() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(sf.format(date));

        //select * from tbl_emp where hiredate > to_date('2018/1','yyyy/mm' )
    }
}


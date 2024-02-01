package com.ohgiraffers.parameterized.section01.params;

import java.time.Month;

public class DateValidator {

    public static boolean isCorrect(Month month){
        int monthValue = month.getValue();

        return monthValue >= 1 && monthValue <=12;
    }

    public static int getLastDayOf(Month month){
        return month.maxLength();
    }
}

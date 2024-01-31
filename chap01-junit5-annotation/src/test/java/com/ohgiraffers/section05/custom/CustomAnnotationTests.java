package com.ohgiraffers.section05.custom;

public class CustomAnnotationTests {

    @WindowsTests // 이런 식으로 커스텀한 어노테이션을 쓸 수 있다.
    public void testOnWindowsOs(){

    }

    @DevelopmentTest
    public void testDevelopmentCustomTag(){

    }

    @ProductionTest
    public void testProductionCustomTag(){

    }
}

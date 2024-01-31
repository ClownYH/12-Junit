package com.ohgiraffers.section05.custom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 런타임시 실행
@Target(ElementType.METHOD) // 메소드에 적용
@EnabledOnOs(OS.WINDOWS) // 윈도우에서만 가능
@Test
public @interface WindowsTests {
}

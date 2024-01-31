package com.ohgiraffers.assertions.section01.jupiter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class JupiterAssertionsTests {
    /*
    * junit jupiter는 junit4로부터 온 assertion 메소드와 새롭게 자바 8 람다 표현식으로 추가된 메소드를 제공한다.
    * 모든 jupiter assertions의 메소드는 정적(static) 메소드 형태로 제공하고 있다.
    * */

    /*
    * 테스트 코드의 기본 구조
    * given : 테스트 시 필요한 파라미터를 준비한다.
    * when : 테스트를 할 메소드를 호출한다.
    * then : 실행 결과를 검증
    * */
    @Test
    @DisplayName("더하기 기능 테스트")
    void testAssertEquals(){
        // given
        int firstNum = 10;
        int secondNum = 20;
        int expected = 30;

        // when
        Calculator calculator = new Calculator();
        int result = calculator.plusTwoNumbers(firstNum, secondNum);

        // then
        Assertions.assertEquals(expected, result);

        // 실패시 호출할 메세지
//        Assertions.assertEquals(expected, result, "실패하는 경우 이게 보임");

        // 오차의 허용 범위를 지정할 수 있다.
//     Assertions.assertEquals(expected, result, 1);

        // 람다 표현식을 이용한 결과는 동일하지만 불필요한 경우 메세지를 만들지 않도록 지연로딩을 이용한다.
//        Assertions.assertEquals(expected, result, () -> "실패할 떄 보여줄 메세지");
    }

    @Test
    @DisplayName("인스턴스 동일성 비교 메세지")
    void testAssertNotEqualsWithInstances(){
        // given & when
        Object obj1 = new Object();
        Object obj2 = new Object();
        // then
        Assertions.assertNotEquals(obj1, obj2); // Object는 주소값을 비교
    }

    // assertNull(actual) 메소드는 레퍼런스 변수가 null 값을 가지는지를 판단한다.
    @Test
    @DisplayName("null 인지 테스트")
    void testAssertNull(){
        // given
        String str;
        // when
        str = null;

        // then
        Assertions.assertNull(str);
    }

    @Test
    @DisplayName("두 값이 같은지 확인")
    void testAssertTrue(){
        int num1 = 10;
        int num2 = 10;

        boolean result = num1 == num2;

//        Assertions.assertTrue(result);
//        Assertions.assertEquals(result, num2 == num1);
        Assertions.assertFalse(result);
    }

    @Test
    @DisplayName("동시에 여러 가지 값에 대한 검증을 수행하는 경우 테스트")
    void testAssertAll(){
        String firstName = "길동";
        String lastName = "홍";

        Person person = new Person(firstName, lastName);

        Assertions.assertAll(
                () -> Assertions.assertEquals(firstName, person.getFirstName(), "first name이 잘못됨"),
                () -> Assertions.assertEquals(lastName, person.getLastName(), "lastName이 잘못됨")
        ); // 둘 다 통과되야 성공됨
    }

    @Test
    @DisplayName("인스턴스의 타입에 대한 검증을 수행하는 경우")
    void testAssertType(){
        // given : 테스트 시 필요한 파라미터를 준비한다.
        String firstName = "길동";
        String lastName = "홍";

        // when : 테스트 대상을 준비해준다.
        Person person = new Person(firstName, lastName);

        // then : 검증하는 로직
        Assertions.assertInstanceOf(Person.class, person);
        Assertions.assertAll( // 유지보수성을 고려해 정확히 검사대상을 분류해서 묶는다.
                () -> Assertions.assertInstanceOf(Person.class, person),
                () -> Assertions.assertEquals(firstName, person.getFirstName()),
                () -> Assertions.assertEquals(lastName,  person.getLastName())
        );
    }

    /*
     * assertDoesNotThrow(excutable) 메소드 호출 시 아무런 예외가 발생하지 않는지 확인
     * */
    @Test
    @DisplayName("void 메소드의 경우 exception 발생이 없이 정상적으로 동작하는지 테스트")
    void testAssertDoesNotThrow(){

        // given
        int firstNum = 10;
        int secondNum = 3;

        // when & then
        PositiveNumberValidator validator = new PositiveNumberValidator();
        Assertions.assertDoesNotThrow(() -> validator.checkDividableNumbers(firstNum, secondNum)); // throw가 없을 때 성공
    }

    @Test
    @DisplayName("void 메소드의 경우 exception 발생하는지 테스트")
    void testAssertThrow(){
        // given
        int firstNum = 10;
        int secondNum = 0;
        String exceptedErrorMessage = "0으로 나눌 수 없습니다.";

        // when & then
//        PositiveNumberValidator validator = new PositiveNumberValidator();
//        Assertions.assertThrows(
//                IllegalArgumentException.class,
//                () -> validator.checkDividableNumbers(firstNum, secondNum),
//                exceptedErrorMessage // throw가 있을 때
//        );
        // when
        PositiveNumberValidator validator = new PositiveNumberValidator();
        Exception exception = Assertions.assertThrows(Exception.class,
                () -> validator.checkDividableNumbers(firstNum, secondNum)
        );

        System.out.println(exception);

        // then
        Assertions.assertAll(
                () -> Assertions.assertInstanceOf(PositiveNumberValidator.class, validator), // 클래스 비교
                () -> Assertions.assertInstanceOf(IllegalArgumentException.class, exception), // 예외 비교
                () -> Assertions.assertDoesNotThrow(() -> validator.checkDividableNumbers(secondNum, firstNum)), // 오류 안나는 경우
                () -> Assertions.assertEquals(exceptedErrorMessage, exception.getMessage())
        );
    }

    @Test
    @DisplayName("예상 목록이 실제 목록과 일치하지 않는지 확인")
    void testAssertLineMatch(){
        // given
        List<String> expected = Arrays.asList("java", "database", "spring");

        // when
        List<String> actual = Arrays.asList("java", "database", "spring");

        // then
        Assertions.assertLinesMatch(expected, actual, () -> "두 리스트의 값이 일치하지 않음");
    }

}


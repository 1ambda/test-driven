
1. 가능한 아름다운 인터페이스를 테스트코드로 작성한다. 또는 설계상의 결함을, 테스트로 작성할 수 있다.
2. 쉽고 간결한 방법이 있으면, 코드로 작성한다. 그럴수 없으면 적어놓고, 빠르게 초록막대를 보도록 죄악을 범한다.
3. 중복을 제거한다.

내가 해야할 유일한 일, 가장 중요한 일은 설계에 대한 결정과, 그에 대한 피드백 사이의 간격을 조절하는 것이다.

### 두 테스트가 동시에 실패하면 망한다.

곱셈 테스트는, 동치성 테스트에 의존한다. 이런 위험은 관리해야 한다.

```java
@Test
public void testMultiplication() {
  Dollar five = new Dollar(5);
  Dollar ten = five.times(2);
  assertEquals(new Dollar(5), five);
  assertEquals(new Dollar(10), ten);

  Dollar fifteen = five.times(3);
  assertEquals(new Dollar(15), fifteen);
  assertEquals(new Dollar(5), five);
}

@Test
public void testEquality() {
  assertTrue(new Dollar(5).equals(new Dollar(5)));
  assertFalse(new Dollar(6).equals(new Dollar(5)));
}
```

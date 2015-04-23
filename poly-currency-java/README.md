# TDD Example: Poly Currency

[Ref: Test Driven Development: By Example][http://www.amazon.com/Test-Driven-Development-By-Example/dp/0321146530]

## Day 2

- ~공용 equal~
- ~~공용 times~~
- ~~Franc, Dollar 중복~~
- ~~Franc, Dollar 비교~~

- Currency 개념. flyweight factory 를 이용할수도 있으나 지금은 간단하게
  더 많은 동기가 있기 전까지는, 더 많은 설계를 도입하지 말자.

  http://ko.wikipedia.org/wiki/%ED%94%8C%EB%9D%BC%EC%9D%B4%EC%9B%A8%EC%9D%B4%ED%8A%B8_%ED%8C%A8%ED%84%B4ç


- Equal Null
- Equal Object
- hashCode()

- getClass 비교보다는 Currency 개념


1. 모든것에 대해 테스트코드를 작성하려고 노력할 필요는 없다. 예를 들어, 상속과 같은 **문법적** 기능에 대해 테스팅 하지 않아도 된다. (필요하면 당연히 해야한다.)

문법적 기능보다는, 객체의 기능을 테스트하는데 노력을 쏟자. 다시 말해서 제품을 개선하는 방향으로의 테스트를 우선적으로 하자.

2. 객체의 기능이 정상적으로 변경되었나, 테스트하면서 확인할 수 있다. 당연한 사실인데도 테스트를 **실행하는데** 익숙지 않으면 잘 하지 않게된다. printf 찍듯이, 자주 run 을 실행하자.

3. 더 많은 동기가 있기 전까지는, **더 많은 설계를 도입하지 마라.**

4. 하위 클래스에 대한 직접적인 참조가 적어지면, 하위 클래스의 중복을 제거할 수 있다.

5. 빨간 막대에서는, 테스트를 더 작성하지 않는 것이 좋다.

6. 빨간 막대에서, 기능을 변경하려고 하는데 해당 기능에 대한 테스트가 없다면
다시 초록막대로 돌아가서, 테스트를 작성하는 보수적인 방법을 사용할 수 있다.

## Day 1

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

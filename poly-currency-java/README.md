# TDD Example: Poly Currency

[Ref: Test Driven Development: By Example][http://www.amazon.com/Test-Driven-Development-By-Example/dp/0321146530]

## Day 6

**TODO**

- ~~5$ + 5$ = 10$~~
- 5$ + 5$ 는 `Money`
- ~~가짜 구현 `Money.plus` 리팩토링~~
- ~~가짜 구현 'Bank.reduce` 리팩토링~~
- `Money.times` 지저분하다.

**LESSON**

- 두 구현체 `Money`, `Sum` 에서 `reudce` 가 가능한지 검토하고, 인터페이스 메소드로 추출했다. 이 과정에서 지저분한 캐스팅이 사라졌다.

즉, 명시적인 클래스 검사 및 캐스팅을 제거하기 위해 **다형성** 을 사용했다. 
이 과정에서, 먼저 한곳에서 캐스팅을 이용해서 구현했다가 이후에 코드를 다른곳으로 옮겼다. 
캐스팅을 이용해도 리팩토링에서 제거할 수 있으므로, 흘러가는대로 하면 된다는 사실을 깨달았다.
  
  
```java
// after
public class Bank {
    public Money reduce(IExpression expr, String to) {
        return expr.reduce(to);
    }
}
```

  
- 다시 한번, **리팩토링은 초록막대에서만 진행한다** 는 사실을 마음에 새겼다. 
- 연산의 외부 행위가 아닌 내부 구현에 대해 집중하는 테스트는, 좋지 않다는 것을 배웠다.
- 외부에서 내부 필드(Variable)에 너무나 많이 접근한다면 메서드 본문을 해당 클래스로 옮기는것도 생각해 볼 만하다.


```java
// before

public class Bank {
    public Money reduce(IExpression expr, String currency) {
        Sum sum = (Sum) expr;

        Money augend = sum.augend;
        Money addend = sum.addend;

        if (!augend.currency().equals(addend.currency())
                && augend.currency().equals(currency))
            return null;

        return new Money(augend.amount() + addend.amount(), currency);
    }
}



// after

public class Bank {
    public Money reduce(IExpression expr, String currency) {
        if (expr instanceof Money) return (Money) expr;

        Sum sum = (Sum) expr;
        return sum.reduce(currency);
    }
}

public class Sum implements IExpression {
    ...
    ...
    
    public Money reduce(String currency) {
        if (!augend.currency().equals(addend.currency())
                && augend.currency().equals(currency)) {
            return null;
        }

        int amount = augend.amount() + augend.amount();

        return new Money(amount, currency);
    }

}
```
 

## Day 4

**TODO**

- 5$ + 5$ = 10$
- 5$ + 5$ 는 `Money`
- 가짜 구현 `Money.plus` 리팩토링
- 가짜 구현 'Bank.reduce` 리팩토링

**LESSON**

- 다중 통화를 표현하고 싶지만 시스템의 다른 부분이 모르도록 하고 싶다. 
- 참조 통화 대신에, 다중 통화 *수식* 그 자체를 사용하고 싶다.
- 이를 위해서 `Expression` 을 도입했다. 

- `reduce` 의 책임을 `Expression` 이 아니라 `Bank` 에게 전가했다.
- 이는 핵심 객체인 `Expression` 이 시스템의 다른부분에 대해서 모르도록 하기 위해서다.

```java
@Test
public void testSimpleAddtion() {
	Money five = Money.dollar(5);
	Expression sum = five.plus(five);

	Bank bank = new Bank();
	Money reduced = bank.reduce(sum, "USD");

	assertEquals(Money.dollar(10), reduced);
}
```

## Day 3

**TODO**

- 더하기 (가짜로 먼저 구현)

**LESSON**

- 실험해본 걸 물리고, 또 다른 테스틀 작성했다. 때때로 다시 테스트가 성공하도록 되돌아 간뒤, 실패하는 테스트를 다시 작성하고 전진할 필요가 있다.
- 기능이 복잡할때는, 간단한 버전부터 시작해보자. 예를 들어 완벽한 화폐 덧셈을 구현하기 전에 `5$ + 5$ = 10$` 을 만들어 볼 수 있다. 

## Day 2

- ~~공용 equal~~
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

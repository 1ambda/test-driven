# XUnit Implementation using Python

[Ref - Test Driven Development: By Example](http://www.amazon.com/Test-Driven-Development-Kent-Beck/dp/0321146530/ref=sr_1_1?s=books&ie=UTF8&qid=1430807061&sr=1-1&keywords=test+driven+development+by+example+in+Books)


## Day 3

### TODO

- ~~테스트 메서드 호출하기~~
- ~~먼저 `setUp` 호출하기~~
- ~~나중에 `tearDown` 호출하기~~
- 테스트 메서드가 실패하더라도, `tearDown` 호출하기
- 여러개의 테스트를 실행하기
- **수집된 결과를 출력하기**
- **실패한 테스트 보고하기**

### SUMMARY

- 테스트를 작성할 때, 나에게 가르침을 줄 것 같고, 만들 수 있다는 확신이 드는 것 부터 구현했다.


## Day 2

### TODO

- ~~테스트 메서드 호출하기~~
- **먼저 `setUp` 호출하기**
- **나중에 `tearDown` 호출하기**
- 테스트 메서드가 실패하더라도, `tearDown` 호출하기
- 여러개의 테스트를 실행하기
- 수집된 결과를 출력하기

### SUMMARY

- 무언가 배우기를 기대한다면, 한 번의 하나의 메소드만 수정하면서 테스트가 성공할 수 있도록 고민하자.
- `setUp` 에서 사용했던 플래그 전략을 버렸다. 플래그가 많아지니까 귀찮아졌다. 그리고 플래그보다 
더 테스트 순서에 대해 자세히 기록할 수 있는 로그 방식을 택했다. 메소드의 본질에 집중하려고 노력했
다

```python
class TestCaseSpec(TestCase):
    def testTemplateMethod(self):
        self.test = WasRun("testMethod")
        self.test.run()
        assert self.test.log == "setUp testMethod "

TestCaseSpec("testTemplateMethod").run()
```

이렇게 변경하니까, 이제 `tearDown` 을 검사하는 일은 `setUp testMethod teatDown ` 과 로그를 비교하기만 하면 된다!


## Day 1

### TODO

- **테스트 메서드 호출하기**
- 먼저 `setUp` 호출하기
- 나중에 `tearDown` 호출하기
- 테스트 메서드가 실패하더라도, `tearDown` 호출하기
- 여러개의 테스트를 실행하기
- 수집된 결과를 출력하기

### SUMMARY

- **pluggable selector** 를 사용했다. 더 자세한 내용은 [여기](http://junit.sourceforge.net/doc/cookstour/cookstour.htm) 를 참조하자.


### LESSON

우선 테스트 케이스를 만들고, 테스트 메서드를 작성할 수 있도록 해야 한다. 그러려면 테스트 케이스 작성을 도와주는 프레임워크를 테스트하기 위한 테스트 케이스를 작성해야 한다. ~~응?~~

작은 것 부터 수동으로 시작하자. 테스트가 실행되었는지 알려주는 `wasRun` 플래그를, `WasRun` 클래스에 담아서 테스트 메소드가 실행되었는지 알 수 있게끔 하자. 당연히 테스트부터 작성한다.

```python
# Xunit.
from XUnit import WasRun

test = WasRun("testMethod")
print test.wasRun # None
test.testMethod()
print test.wasRun # 1

# XUnit.py
class WasRun:
    def __init__(self, name):
        self.wasRun = None
        self.name = name

    def testMethod(self):
        self.wasRun = 1

    def run(self):
        method = getattr(self, self.name)
        method()

```

`WasRun` 클래스는 두 개의 독립된 책임이 있다. 하나는 메소드를 실행하는 것이고, 하나는 `wasRun` 을 다루는 일이다. 
메소드를 실행하는 부분을 `TestCase` 로 옮기고 `WasRun` 클래스가 이를 상속받게 하자.

```python
class TestCase:
    def __init__(self, name):
        self.name = name

    def run(self):
        method = getattr(self, self.name)
        method()

class WasRun(TestCase):
    def __init__(self, name):
        self.wasRun = None
        TestCase.__init__(self, name)

    def testMethod(self):
        self.wasRun = 1
 ```
 
이 `TestCase` 를 이용해 테스트를 하는 `TestCaseSpec` 을 만들어 보자.

```python
class TestCaseSpec(TestCase):
    def testRunning(self):
        test = WasRun("testMethod")
        assert(not test.wasRun)
        test.run()
        assert(test.wasRun)

TestCaseSpec("testRunning").run()
```

좀 괴상하지만, 어쨌든 이제 `print` 가 필요없다.

  



class TestSuite:
    def __init__(self):
        self.tests = []

    def add(self, case):
        methods = [m for m in dir(case) if callable(getattr(case, m)) if m.startswith('test')]

        for m in methods:
            self.tests.append(case(m))


    def run(self, result):
        for t in self.tests:
            t.run(result)

class TestCase:
    def __init__(self, name):
        self.name = name

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def run(self, result):
        try:
            self.setUp()
        except Exception as e:
            pass

        try:
            result.testStarted()
            method = getattr(self, self.name)
            method()
        except Exception as e:
            result.testFailed()

        self.tearDown()
        return result

class SuccessCase(TestCase):
    def __init__(self, name):
        TestCase.__init__(self, name)
        self.log = ""

    def setUp(self):
        self.log += "setUp "

    def testMethod(self):
        self.log += "testMethod "

    def tearDown(self):
        self.log += "tearDown"

class FailureCase(TestCase):
    def __init__(self, name):
        TestCase.__init__(self, name)
        self.log = ""

    def setUp(self):
        a = 1 / 0

    def testMethod(self):
        self.log += "testMethod "

    def tearDown(self):
        self.log += "tearDown"




class TestResult:
    def __init__(self):
        self.runCount = 0
        self.failCount = 0

    def testStarted(self):
        self.runCount += 1

    def testFailed(self):
        self.failCount += 1

    def summary(self):
        return "%d run, %d failed" % (self.runCount, self.failCount)

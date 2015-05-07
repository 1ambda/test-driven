
class TestCase:
    def __init__(self, name):
        self.name = name

    def setUp(self):
        pass

    def tearDown(self):
        pass

    def run(self):
        self.setUp()
        method = getattr(self, self.name)
        method()
        result = TestResult()
        result.testStarted()
        self.tearDown()
        return result

class WasRun(TestCase):
    def __init__(self, name):
        TestCase.__init__(self, name)
        self.log = ""

    def setUp(self):
        self.log += "setUp "

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

from XUnit import *

class TestCaseSpec(TestCase):

    def setUp(self):
        self.result = TestResult()

    def testTemplateMethod(self):
        self.test = SuccessCase("testMethod")

        self.test.run(self.result)

        assert "setUp testMethod tearDown" == self.test.log
        assert "1 run, 0 failed" == self.result.summary()

    def testFailedCase(self):
        self.test = FailureCase("brokenMethod")

        self.test.run(self.result)

        # failed to setup
        assert("tearDown" == self.test.log)
        # failed to call the test method
        assert("1 run, 1 failed" == self.result.summary())

    def testSuiteAdd(self):
        suite = TestSuite()
        suite.add(SampleSpec)
        suite.add(SuccessCase)
        suite.add(FailureCase)
        suite.run(self.result)

        assert("4 run, 1 failed" == self.result.summary())


class SampleSpec(TestCase):
    def testSuccess(self):
        assert(1 == 1)

    def testFailure(self):
        assert(1 == 2)


suite = TestSuite()
suite.add(TestCaseSpec)

result = TestResult()
suite.run(result)
print result.summary()


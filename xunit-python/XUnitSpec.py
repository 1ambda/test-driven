from XUnit import *


class TestCaseSpec(TestCase):
    def testTemplateMethod(self):
        self.test = WasRun("testMethod")
        result = self.test.run()
        assert "setUp testMethod tearDown" == self.test.log
        assert "1 run, 0 failed" == result.summary()

    def testBrokenMethod(self):
        self.test = WasRun("brokenMethod")
        result = self.test.run()
        assert "1 run, 1 failed" == result.summary()


TestCaseSpec("testTemplateMethod").run()
TestCaseSpec("testBrokenMethod").run()



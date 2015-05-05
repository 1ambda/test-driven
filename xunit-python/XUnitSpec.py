from XUnit import *


class TestCaseSpec(TestCase):
    def testRunning(self):
        test = WasRun("testMethod")
        assert not test.wasRun
        test.run()
        assert test.wasRun

TestCaseSpec("testRunning").run()



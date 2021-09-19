import org.junit.Assert
import org.junit.Test

class WorkAllocationCalculatorTest {

    @Test
    fun shouldCreateWorkAllocation() {
        val workAllocation = WorkAllocation()
        Assert.assertNotNull(workAllocation)
    }

    @Test
    fun shouldReturnDefaultAgileCeremoniesWorkAllocation() {
        val expectedDefaultValue = 20
        val workAllocation = WorkAllocation()
        Assert.assertEquals(expectedDefaultValue, workAllocation.agileCeremonies)
    }

    @Test
    fun shouldReturnDefaultStoryWorkWorkAllocation() {
        val expectedDefaultValue = 20.0
        val workAllocation = WorkAllocation()
        Assert.assertEquals(expectedDefaultValue, workAllocation.agileCeremonies, 0.0)
    }

    @Test
    fun shouldCreateWorkAllocationCalculatorWithDefaultAllocation() {
        val workAllocation = WorkAllocation()
        val calculator = WorkAllocationCalculator(workAllocation)
        Assert.assertNotNull(calculator)
    }

    @Test
    fun shouldReportAverageWorkAllocationForSingleDefaultWorkAllocation() {
        val expectedReport = "The average work allocation is 20% Agile Ceremonies / 70% Story work / 10% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val actualReport = calculator.reportAverageWorkAllocation()

        Assert.assertEquals(expectedReport, actualReport)
    }
}
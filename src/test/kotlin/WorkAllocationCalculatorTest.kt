import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class WorkAllocationCalculatorTest {

    @Test
    fun shouldCreateWorkAllocation() {
        val workAllocation = WorkAllocation()
        Assert.assertNotNull(workAllocation)
    }

    @Test
    fun shouldReturnDefaultAgileCeremoniesWorkAllocation() {
        val expectedDefaultValue = 20.0
        val workAllocation = WorkAllocation()
        Assert.assertEquals(expectedDefaultValue, workAllocation.agileCeremonies, 0.0)
    }

    @Test
    fun shouldReturnDefaultFeatureDevelopmentWorkAllocation() {
        val expectedDefaultValue = 70.0
        val workAllocation = WorkAllocation()
        Assert.assertEquals(expectedDefaultValue, workAllocation.featureDevelopment, 0.0)
    }

    @Test
    fun shouldReturnDefaultAdministrativeWorkAllocation() {
        val expectedDefaultValue = 10.0
        val workAllocation = WorkAllocation()
        Assert.assertEquals(expectedDefaultValue, workAllocation.administrative, 0.0)
    }

    @Test
    fun shouldCreateWorkAllocationCalculatorWithDefaultAllocation() {
        val workAllocation = WorkAllocation()
        val calculator = WorkAllocationCalculator(workAllocation)
        Assert.assertNotNull(calculator)
    }

    @Test
    fun shouldReportAverageWorkAllocationForSingleDefaultWorkAllocation() {
        val expectedReport = "The average work allocation is 20% Agile Ceremonies / 70% Feature Development / 10% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val actualReport = calculator.reportAverageWorkAllocation(emptyList())

        Assert.assertEquals(expectedReport, actualReport)
    }

    @Test
    fun shouldReportAverageWorkAllocationForTwoDifferentWorkAllocations() {
        val expectedReport = "The average work allocation is 30% Agile Ceremonies / 30% Feature Development / 40% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations = listOf(
            WorkAllocation(10.0, 20.0, 70.0),
            WorkAllocation(50.0, 40.0, 10.0))

        val actualReport = calculator.reportAverageWorkAllocation(workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }

    @Test
    fun shouldReportAverageWorkAllocationForDateRange() {
        val expectedReport = "The average work allocation is 30% Agile Ceremonies / 30% Feature Development / 40% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations = listOf(
            WorkAllocation(10.0, 20.0, 70.0),
            WorkAllocation(50.0, 40.0, 10.0))

        val startDate = LocalDate.parse("2021-04-01")
        val endDate = LocalDate.parse("2021-04-02")

        val actualReport = calculator.reportAverageWorkAllocation(startDate, endDate, workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }
}
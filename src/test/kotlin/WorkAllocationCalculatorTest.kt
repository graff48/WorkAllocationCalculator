import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class WorkAllocationCalculatorTest {

    private val defaultReportMessage = "The average work allocation is 20% Agile Ceremonies / 70% Feature Development / 10% Administrative"
    private val day1 = LocalDate.parse("2021-04-01")
    private val day2 = LocalDate.parse("2021-04-02")
    private val day3 = LocalDate.parse("2021-04-03")
    private val day4 = LocalDate.parse("2021-04-04")
    private val day5 = LocalDate.parse("2021-04-05")

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
        val expectedReport = defaultReportMessage
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations: List<WorkAllocation> = emptyList()
        val startDate = day1
        val endDate = day2

        val actualReport = calculator.reportAverageWorkAllocation(startDate, endDate, workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }

    @Test
    fun shouldReportAverageWorkAllocationForTwoDifferentWorkAllocations() {
        val expectedReport = "The average work allocation is 30% Agile Ceremonies / 30% Feature Development / 40% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations = listOf(
            WorkAllocation(day1,10.0, 20.0, 70.0),
            WorkAllocation(day2,50.0, 40.0, 10.0))

        val startDate = day1
        val endDate = day2

        val actualReport = calculator.reportAverageWorkAllocation(startDate, endDate, workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }

    @Test
    fun shouldReportAverageWorkAllocationForDateRange() {
        val expectedReport = "The average work allocation is 30% Agile Ceremonies / 30% Feature Development / 40% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations = listOf(
            WorkAllocation(day1,10.0, 20.0, 70.0),
            WorkAllocation(day2,50.0, 40.0, 10.0))

        val startDate = day1
        val endDate = day2

        val actualReport = calculator.reportAverageWorkAllocation(startDate, endDate, workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }

    @Test
    fun shouldReportAverageWorkAllocationWhenDatesOutsideOfDataSetDateRange() {
        val expectedReport = defaultReportMessage
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations = listOf(
            WorkAllocation(day1,10.0, 20.0, 70.0),
            WorkAllocation(day2,50.0, 40.0, 10.0))

        val startDate = day3
        val endDate = day4

        val actualReport = calculator.reportAverageWorkAllocation(startDate, endDate, workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }

    @Test
    fun shouldReportAverageWorkAllocationForDateRangeSmallerThanRangeRepresentedInList() {
        val expectedReport = "The average work allocation is 30% Agile Ceremonies / 30% Feature Development / 40% Administrative"
        val calculator =  WorkAllocationCalculator(WorkAllocation())
        val workAllocations = listOf(
            WorkAllocation(day1, 10.0, 20.0, 70.0),
            WorkAllocation(day2, 50.0, 40.0, 10.0),
            WorkAllocation(day3,20.0, 30.0, 50.0))

        val startDate = day1
        val endDate = day2

        val actualReport = calculator.reportAverageWorkAllocation(startDate, endDate, workAllocations)

        Assert.assertEquals(expectedReport, actualReport)
    }
}
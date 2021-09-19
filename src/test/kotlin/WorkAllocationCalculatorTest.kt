import org.junit.Assert
import org.junit.Test

class WorkAllocationCalculatorTest {

    @Test
    fun shouldCreateWorkAllocation() {
        val workAllocation = WorkAllocation()
        Assert.assertNotNull(workAllocation)
    }

    @Test
    fun shouldCreateWorkAllocationCalculatorWithDefaultAllocation() {
        val workAllocation = WorkAllocation()
        val calculator = WorkAllocationCalculator(workAllocation)
        Assert.assertNotNull(calculator)
    }

    /**Given a collection of WorkAllocation(agile ceremonies, story work, administrative),  a date range, and a default WorkAllocation - calculate
    the average work across the date range.  Use the default WorkAllocation for any missing or invalid WorkAllocation's
     */

}
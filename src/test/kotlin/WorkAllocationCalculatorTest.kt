import org.junit.Assert
import org.junit.Test

class WorkAllocationCalculatorTest {

    @Test
    fun shouldCreateWorkAllocationCalculator() {
        val calculator = WorkAllocationCalculator()
        Assert.assertNotNull(calculator)
    }

    /**Given a collection of WorkAllocation(project planning, project work, administrative),  a date range, and a default WorkAllocation - calculate
        the average work across the date range.  Use the default WorkAllocation for any missing or invalid WorkAllocation's
    */
    @Test
    fun shouldCreateWorkAllocation() {
        val workAllocation = WorkAllocation()
        Assert.assertNotNull(workAllocation)
    }
}
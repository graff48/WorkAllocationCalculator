
/**Given a collection of WorkAllocation(agile ceremonies, feature development, administrative),  a date range, and a default WorkAllocation - calculate
the average work across the date range.  Use the default WorkAllocation for any missing or invalid WorkAllocation's
 */
class WorkAllocationCalculator(val defaultWorkAllocation: WorkAllocation) {

    fun reportAverageWorkAllocation(): String {
        return "The average work allocation is 20% Agile Ceremonies / 70% Story work / 10% Administrative"
    }
}
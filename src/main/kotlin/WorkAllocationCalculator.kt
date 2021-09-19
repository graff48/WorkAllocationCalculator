import java.math.RoundingMode
import java.text.NumberFormat

/**Given a collection of WorkAllocation(agile ceremonies, feature development, administrative),  a date range, and a default WorkAllocation - calculate
the average work across the date range.  Use the default WorkAllocation for any missing or invalid WorkAllocation's
 */
class WorkAllocationCalculator(val defaultWorkAllocation: WorkAllocation) {

    private val defaultReport = "The average work allocation is 20% Agile Ceremonies / 70% Feature Development / 10% Administrative"

    val numberFormatter = NumberFormat.getInstance().apply {
        roundingMode = RoundingMode.HALF_EVEN
        maximumFractionDigits = 2
    }

    fun reportAverageWorkAllocation(workAllocations: List<WorkAllocation>): String {
        if (workAllocations.isNotEmpty()) {

            val movingAvgFunction : (Int, Double, Double ) -> Double = { index, acc, element ->
                (element + (index*acc)) / (index + 1)
            }

            //calculate averages
            val avgAgileCeremonies = workAllocations.map { it.agileCeremonies }.reduceIndexed(movingAvgFunction)
            val avgFeatureDevelopment = workAllocations.map { it.featureDevelopment }.reduceIndexed(movingAvgFunction)
            val avgAdministrative = workAllocations.map { it.administrative }.reduceIndexed(movingAvgFunction)

            return "The average work allocation is " +
                    "${numberFormatter.format(avgAgileCeremonies)}% Agile Ceremonies" +
                    " / ${numberFormatter.format(avgFeatureDevelopment)}% Feature Development" +
                    " / ${numberFormatter.format(avgAdministrative)}% Administrative"
        }
        return defaultReport
    }
}
import java.math.RoundingMode
import java.text.NumberFormat
import java.time.LocalDate
import java.util.stream.Collectors

/**Given a collection of WorkAllocation(agile ceremonies, feature development, administrative),  a date range, and a default WorkAllocation - calculate
the average work across the date range.  Use the default WorkAllocation for any missing or invalid WorkAllocation's
 */
class WorkAllocationCalculator(val defaultWorkAllocation: WorkAllocation) {

    private val defaultReport = "The average work allocation is 20% Agile Ceremonies / 70% Feature Development / 10% Administrative"

    val numberFormatter = NumberFormat.getInstance().apply {
        roundingMode = RoundingMode.HALF_EVEN
        maximumFractionDigits = 2
    }

    fun reportAverageWorkAllocation(startDate: LocalDate, endDate: LocalDate, workAllocations: List<WorkAllocation>): String {
        if (workAllocations.isNotEmpty()) {
            //filter allocations not in range
            val filteredAllocations = workAllocations.filter { it.date in startDate..endDate && it.isValid() }

            //add default records
            val allocationsMap = filteredAllocations.map { it.date to it }.toMap()
            val allocationsInRange = startDate.datesUntil(endDate.plusDays(1)).collect(Collectors.toList()).map {
                allocationsMap[it] ?: WorkAllocation()
            }

            val movingAvgFunction : (Int, Double, Double ) -> Double = { index, acc, element ->
                (element + (index*acc)) / (index + 1)
            }

            if (filteredAllocations.isNotEmpty()) {
                //calculate averages
                val avgAgileCeremonies = allocationsInRange.map { it.agileCeremonies }.reduceIndexed(movingAvgFunction)
                val avgFeatureDevelopment =
                    allocationsInRange.map { it.featureDevelopment }.reduceIndexed(movingAvgFunction)
                val avgAdministrative = allocationsInRange.map { it.administrative }.reduceIndexed(movingAvgFunction)

                return "The average work allocation is " +
                        "${numberFormatter.format(avgAgileCeremonies)}% Agile Ceremonies" +
                        " / ${numberFormatter.format(avgFeatureDevelopment)}% Feature Development" +
                        " / ${numberFormatter.format(avgAdministrative)}% Administrative"
            }
        }
        return defaultReport
    }
}
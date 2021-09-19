import java.time.LocalDate

data class WorkAllocation(val date: LocalDate, val agileCeremonies: Double, val featureDevelopment: Double, val administrative: Double) {
    constructor() : this(date = LocalDate.now(), agileCeremonies = 20.0, featureDevelopment = 70.0, administrative = 10.0)

    fun isValid(): Boolean {
        return (agileCeremonies + featureDevelopment + administrative) == 100.0
    }
}
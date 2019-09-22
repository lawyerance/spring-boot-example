package pers.lyks.spring.example.model

import java.io.Serializable
import java.math.BigDecimal
import java.util.Date

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
class EmployeeModel : Serializable {
    var id: Long = 0
    var empno: String? = null
    var name: String? = null
    var age: Int = 0
    var birthday: Date? = null
    var salary: BigDecimal? = null
}

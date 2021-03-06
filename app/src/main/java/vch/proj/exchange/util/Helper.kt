package vch.proj.exchange.util

import android.util.Log
import vch.com.exchange.util.Constant
import java.util.*

/**
 * Helper - utils for handle app
 */
object Helper {
    const val MY_LOG = "my_log"
    const val prefix = "___"

    fun l(data: String) {
        Log.e(MY_LOG, prefix + data)
    }

    fun l(data: Int) {
        Log.e(MY_LOG, prefix + data.toString())
    }

    fun l(data: Long) {
        Log.e(MY_LOG, prefix + data.toString())
    }

    fun l(data: Double) {
        Log.e(MY_LOG, prefix + data.toString())
    }

    fun l(vararg data: Any) {
        for (`object` in data) {
            Log.e(MY_LOG, prefix + `object`.toString())
        }
    }

    /**
     * DateFormat - handle data format
     * @param calendar - instance of Calendar
     * @param separator - String
     * @param format - Int
     * @return String - formatted Calendar data
     */
    fun dateFormat(calendar: Calendar = Calendar.getInstance(), separator: String = "", format: Int = Constant.NBU_API): String {
        val y = calendar.get(Calendar.YEAR).toString()
        val m = "%02d".format(calendar.get(Calendar.MONTH) + 1)
        val d = "%02d".format(calendar.get(Calendar.DAY_OF_MONTH))

        return when (format) {
            Constant.UI -> {
                "${d}${separator}${m}${separator}${y}"
            }
            Constant.PB_API -> {
                "${d}${separator}${m}${separator}${y}"
            }
            Constant.NBU_API -> {
                "${y}${separator}${m}${separator}${d}"
            }
            else -> "${y}${separator}${m}${separator}${d}"
        }
    }
}
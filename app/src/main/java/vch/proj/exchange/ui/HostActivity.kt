package vch.com.exchange.ui

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel
import vch.com.exchange.ui.pb.fragment.NBUFragment
import vch.com.exchange.ui.pb.fragment.PBDetailFragment
import vch.com.exchange.ui.pb.fragment.PBFragment
import vch.com.exchange.util.Constant
import vch.proj.exchange.R
import vch.proj.exchange.model.ExchangeModel
import vch.proj.exchange.util.Helper.dateFormat
import vch.proj.exchange.util.Helper.l
import java.util.*

class HostActivity : AppCompatActivity() {
    val TO_PB_FRAGMENT = 1
    val TO_PB_DETAIL_FRAGMENT = 2
    val TO_NBU_FRAGMENT = 3
    var currencyDate: Calendar = Calendar.getInstance()

    lateinit var PBBackAction: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ex_layout)

        run()
    }

    fun run() {
        PBBackAction = findViewById(R.id.ex_im_pb_back)
        PBBackAction.setOnClickListener {
            changeFragment(null, TO_PB_FRAGMENT)
        }

        updateUI()

        val datePicker = findViewById<ImageView>(R.id.ex_iv_nbu_datepicker)
        datePicker.setOnClickListener {

            val calendar = Calendar.getInstance()

            val datePickerOnDataSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                currencyDate = calendar
                updateUI()
            }
            DatePickerDialog(this, datePickerOnDataSetListener, calendar
                    .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    fun updateUI() {
        //run main PB currencies list
        changeFragment(null, TO_PB_FRAGMENT)

        //run main NBU currencies list
        changeFragment(null, TO_NBU_FRAGMENT)

        val nbuDate = findViewById<TextView>(R.id.ex_tv_nbu_date)
        nbuDate.text = dateFormat(
                calendar = currencyDate,
                separator = ".",
                format = Constant.UI
        )
    }

    fun changeFragment(model: ExchangeModel?, goToFragment: Int, findCurrency: String? = null) {
        var fragmentManager: FragmentManager = supportFragmentManager
        var backImageState = ImageView.GONE

        when (model) {
            null -> {
                when (goToFragment) {
                    TO_PB_FRAGMENT -> {
                        backImageState = ImageView.GONE
                        PBBackAction.visibility = backImageState
                        fragmentManager.beginTransaction()
                                .replace(R.id.ex_main_pb_container, PBFragment(currencyDate))
                                .commit()
                    }
                    TO_NBU_FRAGMENT -> {
                        fragmentManager.beginTransaction()
                                .replace(R.id.ex_main_nbu_container, NBUFragment(currencyDate))
                                .commit()
                    }
                }
            }
            is PBModel -> { //to PD currency detail
                backImageState = ImageView.VISIBLE
                PBBackAction.visibility = backImageState
                fragmentManager.beginTransaction()
                        .replace(R.id.ex_main_pb_container, PBDetailFragment.getInstance(model))
                        .commit()

                if (null != findCurrency) {
                    val fragment =  NBUFragment.getInstance(findCurrency, currencyDate)
                    fragmentManager.beginTransaction()
                            .replace(R.id.ex_main_nbu_container, fragment)
                            .commit()
                }
            }
            is NBUModel -> { //to NBU currency detail
                l("changeFragment with NBUModel")
            }
        }
    }
}
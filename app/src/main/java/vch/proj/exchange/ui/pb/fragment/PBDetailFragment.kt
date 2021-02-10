package vch.com.exchange.ui.pb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import vch.com.exchange.model.PBModel
import vch.proj.exchange.R
import vch.proj.exchange.model.ExchangeModel
import vch.proj.exchange.util.Helper.l

fun TextView.priceFormat(price: Double): String = "%.2f".format(price).toString()

class PBDetailFragment : Fragment() {
    private lateinit var data: PBModel.ExchangeRate

    companion object {
        fun getInstance(model: ExchangeModel) : Fragment {
            val args = Bundle()
            args.putParcelable("data", model)
            val fragment = PBDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        data = (arguments?.get("data")) as PBModel.ExchangeRate
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.ex_pb_item_details, container, false)

        val name = v.findViewById<TextView>(R.id.ex_tv_name)
        name.text = data.currency

        val buy = v.findViewById<TextView>(R.id.ex_tv_buy)
        buy.text = buy.priceFormat(data.purchaseRate)

        val sale = v.findViewById<TextView>(R.id.ex_tv_sale)
        sale.text = sale.priceFormat(data.saleRate)

        return v
    }
}
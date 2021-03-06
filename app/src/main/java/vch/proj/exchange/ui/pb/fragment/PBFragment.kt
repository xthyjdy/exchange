package vch.com.exchange.ui.pb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import vch.com.exchange.model.PBModel
import vch.com.exchange.repository.Repository
import vch.com.exchange.ui.HostActivity
import vch.com.exchange.ui.pb.ExchangePBViewModelFactory
import vch.com.exchange.ui.pb.PBAdapter
import vch.com.exchange.ui.pb.PBViewModel
import vch.com.exchange.util.Constant
import vch.proj.exchange.R
import vch.proj.exchange.util.Helper
import vch.proj.exchange.util.Helper.l
import java.util.*

/**
 * PBFragment - fragment for Private Bank fragment container
 */
class PBFragment(
    private val currencyDate: Calendar = Calendar.getInstance()
) : Fragment(), PBAdapter.PBItemClickListener {
    lateinit var viewModel: PBViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.ex_pb_fragment, container, false)

        val repository = Repository()
        viewModel = ViewModelProvider(this, ExchangePBViewModelFactory(repository)).get(PBViewModel::class.java)
        val pbAdapter = PBAdapter(this)
        val recyclerView = v.findViewById<RecyclerView>(R.id.ex_recycler_view_pb)
        recyclerView.apply {
            adapter = pbAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.model.observe(requireActivity(), Observer { r ->
            if (r.isSuccessful) {
                val pbModel: PBModel = r.body() as PBModel
                val exchangeRate = pbModel.exchangeRate as List<PBModel.ExchangeRate>
                pbAdapter.submitList(exchangeRate)
            }
        })

        getCurrencyList()

        return v
    }

    /**
     * ItemClick - transfer selected item to HOST activity
     */
    override fun itemClick(model: PBModel.ExchangeRate) {
        val host = (activity as HostActivity)
        host.changeFragment(
            model = model,
            goToFragment = HostActivity.SpecifyDispay.TO_PB_DETAIL_FRAGMENT,
            desiredСurrency = model.currency)
    }

    /**
     * Get Currency List - get currency list from repository
     */
    private fun getCurrencyList() {
        val date = Helper.dateFormat(
                calendar = currencyDate,
                separator = ".",
                format = Constant.PB_API
        )

        viewModel.getByDate(date)
    }
}
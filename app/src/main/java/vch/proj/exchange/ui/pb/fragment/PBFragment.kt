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
import vch.proj.exchange.R
import vch.proj.exchange.util.Helper.l
import java.util.*

class PBFragment(var currencyDate: Calendar = Calendar.getInstance()) : Fragment(), PBAdapter.PBItemClickListener {
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

        viewModel.models.observe(requireActivity(), Observer { r ->
            if (r.isSuccessful) {
                pbAdapter.submitList(r.body())
            } else {
                l(r.errorBody().toString())
            }
        })

        getCurrencyList()

        return v
    }

    override fun itemClick(model: PBModel) {
        val host = (activity as HostActivity)
        host.changeFragment(model = model, goToFragment =  HostActivity().TO_PB_DETAIL_FRAGMENT, findCurrency = model.ccy)
    }

    private fun getCurrencyList() {
        viewModel.getAll()
    }
}
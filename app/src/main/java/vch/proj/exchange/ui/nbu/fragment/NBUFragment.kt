package vch.com.exchange.ui.pb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll
import vch.com.exchange.model.NBUModel
import vch.com.exchange.model.PBModel
import vch.com.exchange.repository.Repository
import vch.com.exchange.ui.HostActivity
import vch.com.exchange.ui.nbu.NBUAdapter
import vch.com.exchange.ui.pb.*
import vch.com.exchange.util.Constant
import vch.proj.exchange.R
import vch.proj.exchange.model.ExchangeModel
import vch.proj.exchange.util.Helper.dateFormat
import vch.proj.exchange.util.Helper.l
import java.util.*

class NBUFragment(var currencyDate: Calendar = Calendar.getInstance()) : Fragment() {
    lateinit var viewModel: NBUViewModel
    private var findCurrency: String? = null

    companion object {
        fun getInstance(findCurrency: String, currencyDate: Calendar = Calendar.getInstance()) : NBUFragment {
            val args = Bundle()
            args.putString("find_currency", findCurrency)
            val fragment = NBUFragment(currencyDate)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (null != arguments) {
            findCurrency = arguments?.get("find_currency")?.toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.ex_nbu_fragment, container, false)


        val repository = Repository()
        viewModel = ViewModelProvider(this, ExchangeNBUViewModelFactory(repository)).get(NBUViewModel::class.java)
        val nbuAdapter = NBUAdapter(findCurrency)
        val recyclerView = v.findViewById<RecyclerView>(R.id.ex_recycler_view_nbu)
        recyclerView?.apply {
            adapter = nbuAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.models.observe(requireActivity(), Observer { r ->
            if (r.isSuccessful) {
                nbuAdapter.submitList(r.body())
            } else {
                l(r.errorBody().toString())
            }
        })

        getCurrencyList()

        //mark searched currency in (nbu) list
        if (null != findCurrency) {
            lifecycleScope.launch {
                var result = false
                delay(500)
                nbuAdapter.currentList.forEachIndexed { index, nbuModel ->
                    if (nbuModel.cc == findCurrency) {
                        recyclerView.smoothScrollToPosition(index)
                        result = true
                    }
                }
                if (false == result) {
                    Snackbar.make(requireView(), "This currency is not listed (only EUR, USD)", Snackbar.LENGTH_LONG).show()
                }
            }
        }

        return v
    }

    private fun getCurrencyList() {
        val date = dateFormat(
                calendar = currencyDate,
                separator = "",
                format = Constant.API
        )

        viewModel.getAll(date)
    }
}
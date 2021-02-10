package vch.com.exchange.ui.pb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vch.com.exchange.model.PBModel
import vch.com.exchange.model.PBModel.ExchangeRate
import vch.proj.exchange.R
import vch.proj.exchange.util.Helper.l

class PBAdapter(
        private val listener: PBItemClickListener
) : ListAdapter<PBModel.ExchangeRate, PBAdapter.PBViewHolder>(PBComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PBViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.ex_item_pb, parent, false)

        return PBViewHolder(v)
    }

    override fun onBindViewHolder(holder: PBViewHolder, position: Int) {
        val currentModel: PBModel.ExchangeRate = getItem(position)
        holder.bind(currentModel)
    }

    inner class PBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.ex_tv_name)
        var price: TextView = itemView.findViewById(R.id.ex_tv_price)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position) {
                    val model = getItem(position) as PBModel.ExchangeRate
                    listener.itemClick(model)
                }
            }
        }

        fun bind(model: PBModel.ExchangeRate) {
            title.text = model.currency
            price.text = model.purchaseRate.toString()
        }
    }

    class PBComparator() : DiffUtil.ItemCallback<PBModel.ExchangeRate>() {
        override fun areItemsTheSame(oldItem: PBModel.ExchangeRate, newItem: PBModel.ExchangeRate) = oldItem.currency == newItem.currency

        override fun areContentsTheSame(oldItem: PBModel.ExchangeRate, newItem: PBModel.ExchangeRate) = oldItem == newItem
    }

    /**
     * PBItemClickListener - interface for handle clicks
     */
    interface PBItemClickListener {
        /**
         * ItemClick - handle selected item
         */
        fun itemClick(model: PBModel.ExchangeRate)
    }
}


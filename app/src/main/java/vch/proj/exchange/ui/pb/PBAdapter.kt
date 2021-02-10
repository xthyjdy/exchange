package vch.com.exchange.ui.pb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vch.com.exchange.model.PBModel
import vch.proj.exchange.R

class PBAdapter(
        private val listener: PBItemClickListener
) : ListAdapter<PBModel, PBAdapter.PBViewHolder>(PBComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PBViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.ex_item_pb, parent, false)

        return PBViewHolder(v)
    }

    override fun onBindViewHolder(holder: PBViewHolder, position: Int) {
        val currentModel: PBModel = getItem(position)
        holder.bind(currentModel)
    }

    inner class PBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.ex_tv_name)
        var price: TextView = itemView.findViewById(R.id.ex_tv_price)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (RecyclerView.NO_POSITION != position) {
                    val model = getItem(position) as PBModel
                    listener.itemClick(model)
                }
            }
        }

        fun bind(model: PBModel) {
            title.text = model.ccy
            price.text = model.buy.toString()
        }
    }

    class PBComparator() : DiffUtil.ItemCallback<PBModel>() {
        override fun areItemsTheSame(oldItem: PBModel, newItem: PBModel) = oldItem.base_ccy == newItem.base_ccy

        override fun areContentsTheSame(oldItem: PBModel, newItem: PBModel) = oldItem == newItem
    }

    /**
     * PBItemClickListener - interface for handle clicks
     */
    interface PBItemClickListener {
        /**
         * ItemClick - handle selected item
         */
        fun itemClick(model: PBModel)
    }
}


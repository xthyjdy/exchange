package vch.com.exchange.ui.nbu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import vch.com.exchange.model.NBUModel
import vch.proj.exchange.R
import vch.proj.exchange.util.Helper.l

class NBUAdapter(
        private var desiredСurrency: String? = null
) : ListAdapter<NBUModel, NBUAdapter.NBUViewHolder>(NBUComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NBUViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.ex_item_nbu, parent, false)

        return NBUViewHolder(v)
    }

    override fun onBindViewHolder(holder: NBUViewHolder, position: Int) {
        val currentModel: NBUModel = getItem(position)
        holder.bind(currentModel)
    }

    inner class NBUViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.ex_tv_name)
        var price: TextView = itemView.findViewById(R.id.ex_tv_price)
        var rl: RelativeLayout = itemView.findViewById(R.id.ex_nbu_rl_item_wrapper)

        fun bind(model: NBUModel) {
            if (model.cc == desiredСurrency) {
                rl.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.green))
            }

            title.text = model.txt
            price.text = model.rate.toString()
        }
    }

    class NBUComparator() : DiffUtil.ItemCallback<NBUModel>() {
        override fun areItemsTheSame(oldItem: NBUModel, newItem: NBUModel) = oldItem.txt == newItem.txt

        override fun areContentsTheSame(oldItem: NBUModel, newItem: NBUModel) = oldItem == newItem
    }
}
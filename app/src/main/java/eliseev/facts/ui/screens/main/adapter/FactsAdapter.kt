package eliseev.facts.ui.screens.main.adapter

import android.widget.ImageView
import coil.api.load
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import eliseev.facts.R
import eliseev.facts.data.models.Fact

class FactsAdapter : BaseQuickAdapter<Fact, BaseViewHolder>(R.layout.item_fact) {

    override fun convert(holder: BaseViewHolder, item: Fact) {
        with(holder) {
            getView<ImageView>(R.id.ivFact).load(item.photo)

            setText(R.id.tvFact, item.text)

            if (item.createdAt == null) {
                setGone(R.id.tvDate, true)
            } else {
                setVisible(R.id.tvDate, true)
                setText(R.id.tvDate, item.formattedCreatedAt)
            }
        }
    }
}

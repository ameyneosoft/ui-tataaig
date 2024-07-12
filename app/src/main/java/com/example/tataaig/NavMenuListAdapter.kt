import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.core.content.ContextCompat
import com.example.tataaig.R
import com.example.tataaig.databinding.ListGroupBinding
import com.example.tataaig.databinding.ListItemBinding



class NavMenuListAdapter(
    private val context: Context,
    private val list: List<NavigationMenuGroup>
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return list.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return list[groupPosition].items.size
    }

    override fun getGroup(groupPosition: Int): NavigationMenuGroup {
        return list[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String {
        return list[groupPosition].items[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding = if (convertView == null) {
            ListGroupBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ListGroupBinding.bind(convertView)
        }

        val indicator = if (list[groupPosition].items.isEmpty()) {
            null
        } else if (isExpanded) {
            ContextCompat.getDrawable(context, R.drawable.ic_up)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_down)
        }
        val data = getGroup(groupPosition)
        binding.listGroup.text = data.text
        if (getGroup(groupPosition).icon != 0) {
            binding.listGroup.setCompoundDrawablesRelativeWithIntrinsicBounds(
                ContextCompat.getDrawable(
                    context,
                    data.icon
                ), null, indicator, null
            )
        }

        return binding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding = if (convertView == null) {
            ListItemBinding.inflate(LayoutInflater.from(context), parent, false)

        } else {
            ListItemBinding.bind(convertView)
        }

        binding.expandedListItem.text = getChild(groupPosition, childPosition)
        return binding.root
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}
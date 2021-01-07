package com.assignment.shaadi.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.assignment.shaadi.R
import com.assignment.shaadi.data.model.MatchInvitation
import com.assignment.shaadi.other.INVITATION_STATUS
import com.assignment.shaadi.other.hide
import com.assignment.shaadi.other.show
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.adapter_email_item.view.*
import javax.inject.Inject

class MatchInvitationAdapter @Inject constructor(
	private val glide: RequestManager
) : RecyclerView.Adapter<MatchInvitationAdapter.MatchInvitationViewHolder>() {

	class MatchInvitationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


	companion object {
		const val TAG = "MatchInvitationAdapter"
	}

	private val diffCallback = object : DiffUtil.ItemCallback<MatchInvitation>() {
		override fun areItemsTheSame(
			oldItem: MatchInvitation,
			newItem: MatchInvitation
		): Boolean {
			return oldItem.uuid == newItem.uuid
		}

		override fun areContentsTheSame(
			oldItem: MatchInvitation,
			newItem: MatchInvitation
		): Boolean {
			return oldItem == newItem
		}
	}

	private val differ = AsyncListDiffer(this, diffCallback)

	var matchInvitations: List<MatchInvitation>
		get() = differ.currentList
		set(value) = differ.submitList(value)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchInvitationViewHolder {
		return MatchInvitationViewHolder(
			LayoutInflater.from(parent.context).inflate(
				R.layout.adapter_email_item,
				parent,
				false
			)
		)
	}

	private var onMatchAttemptedListener: ((MatchInvitation, String) -> Unit)? = null

	fun setOnMatchAttemptedListener(listener: (MatchInvitation, String) -> Unit) {
		onMatchAttemptedListener = listener
	}

	override fun onBindViewHolder(holder: MatchInvitationViewHolder, position: Int) {
		val item = matchInvitations[position]
		holder.itemView.apply {
			glide.load(item.imageUrl).into(imageView)

			tvName.text = "${item.firstName} ${item.lastName?.get(0)?.toUpperCase()}"
			tvDetailLine1.text = item.age.toString()
			tvDetailLine2.text = "${item.city}, ${item.state}"

			when (item.invitation_status) {
				INVITATION_STATUS.PENDING -> {
					groupSelection.show()
					tvMessage.hide()
				}
				INVITATION_STATUS.ACCEPTED -> {
					groupSelection.hide()
					tvMessage.show()

					tvMessage.text = context.getString(R.string.label_member_accepted)
				}
				INVITATION_STATUS.DECLINED -> {
					groupSelection.hide()
					tvMessage.show()

					tvMessage.text = context.getString(R.string.label_member_declined)
				}
			}

			btnAccept.setOnClickListener {
				onMatchAttemptedListener?.invoke(item, INVITATION_STATUS.ACCEPTED)
				tvMessage.text = context.getString(R.string.label_member_accepted)
				notifyItemChanged(position)
			}

			btnDecline.setOnClickListener {
				onMatchAttemptedListener?.invoke(item, INVITATION_STATUS.DECLINED)
				tvMessage.text = context.getString(R.string.label_member_declined)
				notifyItemChanged(position)
			}
		}
	}

	override fun getItemCount() = matchInvitations.size

}




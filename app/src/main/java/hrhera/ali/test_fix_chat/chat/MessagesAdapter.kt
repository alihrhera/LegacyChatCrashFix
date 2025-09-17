package hrhera.ali.test_fix_chat.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hrhera.ali.test_fix_chat.R

class MessagesAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size
}

class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.text_message)
    private val senderView: TextView = itemView.findViewById(R.id.text_sender)

    fun bind(message: ChatMessage) {
        textView.text = message.message
        senderView.text = message.name
    }
}

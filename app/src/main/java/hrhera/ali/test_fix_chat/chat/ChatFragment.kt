package hrhera.ali.test_fix_chat.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import hrhera.ali.test_fix_chat.databinding.FragmentChatBinding
import kotlin.getValue

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private var adapter = MessagesAdapter(emptyList())
    private val viewModel by viewModels<ChatViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.messages.observe(viewLifecycleOwner) {
            binding.messages.adapter = MessagesAdapter(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentChatBinding.inflate(inflater, container, false)
        binding.messages.adapter = adapter
        return binding.root
    }
}
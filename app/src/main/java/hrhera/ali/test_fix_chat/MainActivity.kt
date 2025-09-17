package hrhera.ali.test_fix_chat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import hrhera.ali.test_fix_chat.chat.ChatFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btn_simulate_network_toggle).setOnClickListener {
            openChatFragment()
        }
    }

    private fun openChatFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ChatFragment())
            .addToBackStack(null)
            .commit()
    }


}

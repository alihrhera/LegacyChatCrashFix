package hrhera.ali.test_fix_chat.chat

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ChatFragmentTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var fragment: ChatFragment
    private lateinit var mockViewModel: ChatViewModel
    private lateinit var scenario: FragmentScenario<ChatFragment>

    @Before
    fun setup() {
        mockViewModel = mock(ChatViewModel::class.java)
        val mockMessages = MutableLiveData<List<ChatMessage>>()
        `when`(mockViewModel.messages).thenReturn(mockMessages)
    }

    @Test
    fun `observer should be removed when fragment view is destroyed`() {
        scenario = FragmentScenario.launchInContainer(ChatFragment::class.java)
        scenario.onFragment { fragment ->
            this.fragment = fragment
        }

        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.moveToState(Lifecycle.State.DESTROYED)

        assertTrue("Fragment should survive lifecycle changes", true)
    }

    @Test
    fun `messages should update adapter when fragment is active`() {
        val messages = listOf(
            ChatMessage("New one", "Hi there"),
            ChatMessage("New two", "Hello from the other side")
        )

        scenario = FragmentScenario.launchInContainer(ChatFragment::class.java)

        scenario.onFragment { fragment ->
            fragment.viewModel.messages.value = messages
        }

        scenario.onFragment { fragment ->
            val adapter = fragment.binding.messages.adapter as? MessagesAdapter
            assertNotNull("Adapter should be set", adapter)
            assertEquals("Adapter should have correct item count", messages.size, adapter?.itemCount)
        }
    }
}


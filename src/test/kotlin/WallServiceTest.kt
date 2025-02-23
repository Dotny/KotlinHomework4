import org.junit.Assert.*
import org.junit.Before
import kotlin.test.Test

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addNoNull() {
        val comments = Comments()
        val likes = Likes()
        val post1 = Post(1, 2, 2, 2025, "Hello", comments, likes)

        assertEquals(WallService.add(post1).id != 0, true)
    }

    @Test
    fun updateTrue() {
        val comments = Comments()
        val likes = Likes()
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes)
        val post2 = Post(1, 2, 2, 2026, "Hello",  comments, likes)
        WallService.add(post1)
        assertEquals(WallService.update(post2), true)
    }

    @Test
    fun updateFalse() {
        val comments = Comments()
        val likes = Likes()
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes)
        val post2 = Post(2, 2, 2, 2026, "Hello",  comments, likes)
        WallService.add(post1)
        assertEquals(WallService.update(post2), false)
    }
}
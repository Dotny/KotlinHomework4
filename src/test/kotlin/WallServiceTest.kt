import org.junit.Assert.*
import kotlin.test.Test

class WallServiceTest {

    @org.junit.Test
    fun add() {
        val comments = Comments()
        val likes = Likes()
        val post1 = Post(1, 2, 2, 2025, "Hello", true, comments, likes)

        assertEquals(WallService.add(post1).id != 0, true)
    }

    @org.junit.Test
    fun update() {
        val comments = Comments()
        val likes = Likes()
        val post1 = Post(1, 2, 2, 2025, "Hello", true, comments, likes)
        val post2 = Post(1, 2, 2, 2026, "Hello", true, comments, likes)
        val post3 = Post(11, 2, 2, 2026, "Hello", true, comments, likes)
        WallService.add(post1)
        assertEquals(WallService.update(post2), true)
        assertEquals(WallService.update(post3), false)
    }
}
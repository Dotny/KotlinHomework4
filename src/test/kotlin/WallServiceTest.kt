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
        val video1: Attachment = Video(1, 1, 2025, "Funny", "Discovery", 1800, 1920, 1080)
        val post1 = Post(1, 2, 2, 2025, "Hello", comments, likes, video1)

        assertEquals(WallService.add(post1).id != 0, true)
    }

    @Test
    fun updateTrue() {
        val comments = Comments()
        val likes = Likes()
        val video1: Attachment = Video(1, 1, 2025, "Funny", "Discovery", 1800, 1920, 1080)
        val file1: Attachment = File(2, 1, 2023, 1024, "project", "pdf")
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes, video1)
        val post2 = Post(1, 2, 2, 2026, "Hello",  comments, likes, file1)
        WallService.add(post1)
        assertEquals(WallService.update(post2), true)
    }

    @Test
    fun updateFalse() {
        val comments = Comments()
        val likes = Likes()
        val video1: Attachment = Video(1, 1, 2025, "Funny", "Discovery", 1800, 1920, 1080)
        val file1: Attachment = File(2, 1, 2023, 1024, "project", "pdf")
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes, file1)
        val post2 = Post(2, 2, 2, 2026, "Hello",  comments, likes, video1)
        WallService.add(post1)
        assertEquals(WallService.update(post2), false)
    }
}
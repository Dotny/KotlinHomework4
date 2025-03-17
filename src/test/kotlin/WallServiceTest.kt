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
        val video1 = Video("Funny video", "Travel", 3600, 1080, 1920)
        val video1Attachment: Attachment = VideoAttachment(1, 1, 2025, video1)
        val post1 = Post(1, 2, 2, 2025, "Hello", comments, likes, video1Attachment)

        assertEquals(WallService.add(post1).id != 0, true)
    }

    @Test
    fun updateTrue() {
        val comments = Comments()
        val likes = Likes()
        val video1 = Video("Funny video", "Travel", 3600, 1080, 1920)
        val video1Attachment: Attachment = VideoAttachment(1, 1, 2025, video1)
        val file1 = File(2048, "project", "pdf")
        val file1Attachment: Attachment = FileAttachment(1, 2, 2020, file1)
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes, video1Attachment)
        val post2 = Post(1, 2, 2, 2026, "Hello",  comments, likes, file1Attachment)
        WallService.add(post1)
        assertEquals(WallService.update(post2), true)
    }

    @Test
    fun updateFalse() {
        val comments = Comments()
        val likes = Likes()
        val video1 = Video("Funny video", "Travel", 3600, 1080, 1920)
        val video1Attachment: Attachment = VideoAttachment(1, 1, 2025, video1)
        val file1 = File(2048, "project", "pdf")
        val file1Attachment: Attachment = FileAttachment(1, 2, 2020, file1)
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes, file1Attachment)
        val post2 = Post(2, 2, 2, 2026, "Hello",  comments, likes, video1Attachment)
        WallService.add(post1)
        assertEquals(WallService.update(post2), false)
    }

    @Test
    fun createComment(){
        val comments = Comments()
        val likes = Likes()
        val file1 = File(2048, "project", "pdf")
        val file1Attachment: Attachment = FileAttachment(1, 2, 2020, file1)
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes, file1Attachment)
        val comment = Comment(1, 1, 2023, "Hello netology")
        WallService.add(post1)
        assertEquals(WallService.createComment(1, comment), comment)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val comments = Comments()
        val likes = Likes()
        val file1 = File(2048, "project", "pdf")
        val file1Attachment: Attachment = FileAttachment(1, 2, 2020, file1)
        val post1 = Post(1, 2, 2, 2025, "Hello",  comments, likes, file1Attachment)
        WallService.add(post1)
        val comment = Comment(1, 1, 2023, "Hello netology")
        WallService.createComment(1234, comment)
    }
}
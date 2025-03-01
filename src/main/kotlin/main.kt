data class Post(
    val id: Int,
    val ownerId: Int?,
    val fromId: Int,
    val date: Int,
    val text: String,
    val comments: Comments,
    val likes: Likes,
    val attachment: Attachment?,
    val views: Int = 0,
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
)

class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

interface Attachment {
    val type: String
    val id: Int
    val ownerId: Int
    val date: Int
}

data class Photo(val album_id: Int, val text: String, val width: Int, val height: Int)
data class PhotoAttachment(override val id: Int, override val ownerId: Int, override val date: Int, val photo: Photo): Attachment{
    override val type: String = "photo"
}

data class Audio(val albumId: Int, val artist: String, val text: String, val duration: Int)
data class AudioAttachment(override val id: Int, override val ownerId: Int, override val date: Int, val audio: Audio): Attachment{
    override val type: String = "audio"
}

data class Video(val description: String, val title: String, val duration: Int, val width: Int, val height: Int)
data class VideoAttachment(override val id: Int, override val ownerId: Int, override val date: Int, val video: Video): Attachment{
    override val type: String = "video"
}

data class File(val size: Long, val title: String, val ext: String)
data class FileAttachment( override val id: Int, override val ownerId: Int, override val date: Int, val file: File): Attachment{
    override val type: String = "file"
}

data class Url(val url: String, val title: String, val description: String)
data class UrlAttachment(override val id: Int, override val ownerId: Int, override val date: Int, val url: Url): Attachment{
    override val type: String = "url"
}

object WallService {

    private var posts = emptyArray<Post>()
    private var id = 0

    fun add(post: Post): Post {
        id += 1
        posts += post.copy(id = id)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        var result: Boolean = true
        for ((index, postId) in posts.withIndex()) {
            if (postId.id == post.id) {
                posts[index] = post
            } else {
                result = false
            }
        }
        return result
    }

    fun clear() {
        posts = emptyArray()
        id = 0
    }
}


fun main() {
    val comments = Comments()
    val likes = Likes()
    val video1 = Video("Funny video", "Travel", 3600, 1080, 1920)
    val video1Attachment: Attachment = VideoAttachment(1, 1, 2025, video1)
    val file1 = File(2048, "project", "pdf")
    val file1Attachment: Attachment = FileAttachment(1, 2, 2020, file1)
    var post1 = Post(1, 2, 3, 2025, "Hello", comments, likes, video1Attachment)
    var post2 = Post(2, 2, 3, 2026, "Hello", comments, likes, file1Attachment)
    WallService.add(post1)
    println(WallService.update(post2))
}
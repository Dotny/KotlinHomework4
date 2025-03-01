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

open class Attachment(
    val type: String,
    val id: Int,
    val ownerId: Int,
    val date: Int
)

class Photo(
    id: Int,
    ownerId: Int,
    date: Int,
    val album_id: Int,
    val text: String,
    val width: Int,
    val height: Int,
    type: String = "Photo"
) : Attachment(type, id, ownerId, date)

class Audio(
    id: Int,
    ownerId: Int,
    date: Int,
    val artist: String,
    val text: String,
    val duration: Int,
    val albumId: Int,
    type: String = "Audio"
) : Attachment(type, id, ownerId, date)

class Video(
    id: Int,
    ownerId: Int,
    date: Int,
    val description: String,
    val title: String,
    val duration: Int,
    val width: Int,
    val height: Int,
    type: String = "Video"
) : Attachment(type, id, ownerId, date)

class File(
    id: Int,
    ownerId: Int,
    date: Int,
    val size: Long,
    val title: String,
    val ext: String,
    type: String = "File"
) : Attachment(type, id, ownerId, date)

class Url(
    id: Int,
    ownerId: Int,
    date: Int,
    val url: String,
    val title: String,
    val description: String,
    type: String = "Url"
) : Attachment(type, id, ownerId, date)

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
    val video1: Attachment = Video(1, 1, 2025, "Funny", "Discovery", 1800, 1920, 1080)
    val file1: Attachment = File(2, 1, 2023, 1024, "project", "pdf")
    var post1 = Post(1, 2, 3, 2025, "Hello", comments, likes, video1)
    var post2 = Post(2, 2, 3, 2026, "Hello", comments, likes, file1)
    WallService.add(post1)
    println(WallService.update(post2))
}
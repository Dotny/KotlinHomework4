data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val comments: Comments,
    val likes: Likes,
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

class   Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

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
    var post1 = Post(1, 2, 3, 2025, "Hello", comments, likes)
    var post2 = Post(2, 2, 3, 2026, "Hello", comments, likes)
    WallService.add(post1)
    println(WallService.update(post2))
}
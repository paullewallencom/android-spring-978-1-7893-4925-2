package com.packtpub.sunnat629.social_network.repository

import com.packtpub.sunnat629.social_network.model.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class DeletePCLRepository : DeletePCLByIDInterface {

    @Autowired
    private lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    private lateinit var postRepository: PostRepository


    override fun deleteAllUsersInfoByUserID(userID: Long): Any {

        val deletePosts = "SELECT FROM post WHERE profile_id = ?;"

        jdbcTemplate.queryForObject(deletePosts, Post::class.java, userID)

        return "DONE"
    }
}

interface DeletePCLByIDInterface {
    fun deleteAllUsersInfoByUserID(userID: Long): Any
}

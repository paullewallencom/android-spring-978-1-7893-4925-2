package com.packtpub.sunnat629.social_network.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import java.io.Serializable
import java.time.Instant
import javax.persistence.FetchType


@Entity
class Post(text: String, postedBy: Profile) : Serializable {

    @Id
    @GeneratedValue
    var id: Long? = 0

    var text: String? = text

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    @JsonIgnoreProperties(/*"username",*/"password", "email","accCreatedTime",/*"firstName","lastName",*/
            "contactNumber","dob","city","country")
    var postedBy: Profile? = postedBy

    @JsonIgnore
    @JsonProperty("postCreatedTime")
    var postCreatedTime: Instant? = Instant.now()

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval=true)
    val comments = mutableListOf<Comment>()


    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var likes: List<LikeObj>? = emptyList()

    companion object {

        private const val serialVersionUID = 1L
    }
}

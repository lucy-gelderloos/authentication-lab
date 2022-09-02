package com.gelderloos.authentication.models;

import com.gelderloos.authentication.repositories.AppPostRepository;

import javax.persistence.*;

@Entity
public class AppPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postAuthorUserName;
    private String postContent;

    @ManyToOne
    private AppUser appUser;

    protected AppPost(){
    }

    public AppPost(String postAuthorUserName, String postContent) {
        this.postAuthorUserName = postAuthorUserName;
        this.postContent = postContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostAuthorUserName() {
        return postAuthorUserName;
    }

    public void setPostAuthorUserName(String postAuthorUserId) {
        this.postAuthorUserName = postAuthorUserId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }
}

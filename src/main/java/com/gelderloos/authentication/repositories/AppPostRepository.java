package com.gelderloos.authentication.repositories;

import com.gelderloos.authentication.models.AppPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppPostRepository extends JpaRepository<AppPost, Long> {
//    public AppPost findByPostsByAuthor(String username);
}

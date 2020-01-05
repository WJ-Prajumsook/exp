package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wj.prajumsook.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}

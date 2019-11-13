package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wj.prajumsook.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}

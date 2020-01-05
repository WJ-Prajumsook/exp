package org.wj.prajumsook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wj.prajumsook.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findCommentByPostId(Integer postId);

}

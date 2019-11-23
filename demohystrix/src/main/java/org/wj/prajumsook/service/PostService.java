package org.wj.prajumsook.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wj.prajumsook.entity.Post;
import org.wj.prajumsook.entity.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private PostRepository repository;

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post findById(String id) {
        return repository.findById(id).get();
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public Post deleteById(String id) {
        Post post = findById(id);
        repository.deleteById(post.getId());

        return post;
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}

package org.wj.prajumsook.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wj.prajumsook.entity.Post;
import org.wj.prajumsook.service.PostService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    @HystrixCommand(groupKey = "get all post", fallbackMethod = "getAllPostFallback")
    public List<Post> getAllPost() {
        return postService.findAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable(value = "id")String id) {
        return postService.findById(id);
    }

    public List<Post> getAllPostFallback() {
        //return Collections.emptyList();
        return Arrays.asList(new Post("ID:123456", "Empty title", "Empty content"));
    }
}

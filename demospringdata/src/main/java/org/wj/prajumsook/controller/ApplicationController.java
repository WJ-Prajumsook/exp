package org.wj.prajumsook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wj.prajumsook.entity.Address;
import org.wj.prajumsook.entity.Comment;
import org.wj.prajumsook.entity.Post;
import org.wj.prajumsook.entity.User;
import org.wj.prajumsook.repository.AddressRepository;
import org.wj.prajumsook.repository.CommentRepository;
import org.wj.prajumsook.repository.PostRepository;
import org.wj.prajumsook.repository.UserRepository;

import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/user")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/{id}/address")
    public Address getUserAddress(@PathVariable(value = "id")Integer userId) {
        return addressRepository.findByUserId(userId);
    }

    @PostMapping("/user/{id}/address")
    public Address createUserAddress(@PathVariable(value = "id")Integer userId,
                                     @RequestBody Address address
    ) throws Exception {
        return userRepository.findById(userId).map(user -> {
            address.setUser(user);
            return addressRepository.save(address);
        }).orElseThrow(() -> new Exception());
    }

    @GetMapping("/post")
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @PostMapping("/user/{id}/post")
    public Post createPost(@PathVariable(value = "id")Integer userId, @RequestBody Post post) throws Exception {
        return userRepository.findById(userId).map(user -> {
            post.setUser(user);
            return postRepository.save(post);
        }).orElseThrow(() -> new Exception());
    }

    @GetMapping("/post/{id}/comment")
    public List<Comment> getAllComment(@PathVariable(value = "id")Integer postId) {
        return commentRepository.findByPostId(postId);
    }

    @PostMapping("/post/{id}/comment")
    public Comment createComment(@RequestBody Comment comment,
                                 @PathVariable(value = "id")Integer postId
    ) throws Exception {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new Exception());
    }
}

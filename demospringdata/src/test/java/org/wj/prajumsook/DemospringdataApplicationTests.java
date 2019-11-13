package org.wj.prajumsook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.wj.prajumsook.entity.Address;
import org.wj.prajumsook.entity.Comment;
import org.wj.prajumsook.entity.Post;
import org.wj.prajumsook.entity.User;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemospringdataApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		User user = new User();
		user.setName("John Doe");
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://localhost:"+port+"/user",
				user, User.class);
		System.out.println("============ new create user");
		System.out.println(responseEntity.getBody());

		// create address
		int userId = responseEntity.getBody().getId();
		Address address = new Address();
		address.setAddress("Old street 12");
		address.setPostAddress("111 22 Old town");

		String url = "http://localhost:"+port+"/user/"+userId+"/address";
		ResponseEntity<Address> addressResponseEntity = restTemplate.postForEntity(url, address, Address.class);
		System.out.println("============ new create address");
		System.out.println(addressResponseEntity.getBody());


		// Get User address
		ResponseEntity<Address> responseEnt = restTemplate.getForEntity(url, Address.class);
		System.out.println("============ get user address");
		System.out.println(responseEnt.getBody());

		System.out.println("============ create post");
		url = "http://localhost:"+port+"/user/"+userId+"/post";
		Post post = new Post();
		post.setPostTitle("The post title");
		post.setPostContent("This is post content");
		ResponseEntity<Post> postResponseEntity = restTemplate.postForEntity(url, post, Post.class);
		int postId = postResponseEntity.getBody().getId();

		System.out.println("============ create some comment");
		Comment comment = new Comment();
		comment.setComment("Comment for " + postResponseEntity.getBody().getPostTitle() + " number one");
		url = "http://localhost:"+port+"/post/"+postId+"/comment";
		restTemplate.postForEntity(url, comment, Comment.class);
		comment = new Comment();
		comment.setComment("Comment number two");
		restTemplate.postForEntity(url, comment, Comment.class);

		System.out.println("============ get post");
		url = "http://localhost:"+port+"/post/";
		ResponseEntity<List<Post>> listResponseEntity = restTemplate.exchange(url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Post>>() {
				});
		System.out.println(listResponseEntity.getBody());

		postId = listResponseEntity.getBody().get(0).getId();
		System.out.println("============ get post comments");
		url = "http://localhost:"+port+"/post/"+postId+"/comment";
		ResponseEntity<List<Comment>> listResponseEntity1 = restTemplate.exchange(url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Comment>>() {
				});
		System.out.println(listResponseEntity1.getBody());
	}

}

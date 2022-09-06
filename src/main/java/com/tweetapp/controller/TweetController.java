package com.tweetapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tweetapp.dto.TweetDTO;
import com.tweetapp.service.TweetService;

@RestController
@RequestMapping("/api/v1.0/tweets")
public class TweetController {
	
	@Autowired
	private TweetService tweetService;
	
	@GetMapping(value = "/username")
	public List<TweetDTO> getAllTweetOfUser(@RequestParam("username") String username) {
		return tweetService.getAllTweetsOfUser(username);
	}
	
	@GetMapping(value = "/all")
	public List<TweetDTO> getAllTweets() {
		return tweetService.getAllTweets();
	}
		
	@PostMapping(value = "/{username}/add")
	public String createTweet (@PathVariable(name = "username") String username, @RequestBody TweetDTO tweetText) {
		return tweetService.createTweet(username,tweetText);
	}
	
	@PutMapping(value = "/{username}/update/{id}")
	public String updateTweet (@PathVariable(name = "username") String username, @PathVariable(name = "id") String tweetId, @RequestBody TweetDTO tweetText) {
		return tweetService.updateTweet(username, tweetId,  tweetText);
	}
		
	@DeleteMapping(value = "/{username}/delete/{id}")
	public String deleteTweet (@PathVariable(name = "username") String username, @PathVariable(name = "id") String tweetId) {
		return tweetService.deleteById(username , tweetId);
	}
	
	@PutMapping(value = "/{username}/like/{id}")
	public List<String> likeTweet (@PathVariable(name = "username") String username, @PathVariable(name = "id") String tweetId) {
		return tweetService.likeTweet(username , tweetId);
	}
	
	@PostMapping(value = "/{username}/reply/{id}")
	public List<String> replyToTweet (@PathVariable(name = "username") String username, @PathVariable(name = "id") String tweetId, @RequestBody TweetDTO tweetText) {
		return tweetService.replyToTweet(username, tweetId,  tweetText);
	}
	
}

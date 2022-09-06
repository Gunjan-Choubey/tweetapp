package com.tweetapp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.dto.TweetDTO;
import com.tweetapp.model.Tweet;
import com.tweetapp.service.TweetService;

@ExtendWith(SpringExtension.class)
public class TweetControllerTest {
	
	@InjectMocks
	TweetController tweetController;
	
	@Mock
	private TweetService tweetService;

	@Test
	public void getAllTweetOfUserTest() {
		String username = "Gunjan@1";
		when(tweetService.getAllTweetsOfUser(username)).thenReturn(Stream.of(new TweetDTO(),
				new TweetDTO()).collect(Collectors.toList()));
		assertEquals(2, tweetController.getAllTweetOfUser(username).size());
	}
	
	@Test
	public void getAllTweetsTest() {
		when(tweetService.getAllTweets()).thenReturn(Stream.of(new TweetDTO(),
				new TweetDTO()).collect(Collectors.toList()));
		assertEquals(2, tweetController.getAllTweets().size());
	}
	
	@Test
	public void createTweetTest () {
		Tweet tweet = new Tweet();
		tweet.setId(null);
		tweet.setTweet("An apple a day keeps the doctor away");
		tweet.setTag("Taste");
		tweet.setLoginId("Gouri@2");
		String result = "Tweet Posted";
		String username="Gouri@2";
		TweetDTO tweetText = new TweetDTO();
		when(tweetService.createTweet(username,tweetText)).thenReturn(result);
		assertEquals(result, tweetController.createTweet(username,tweetText));
	}
	
	@Test
	public void updateTweetTest () {
		Tweet tweet = new Tweet();
		tweet.setId("62e3ff2af3f29702c6c37c61");
		tweet.setTweet("Orange is my fav fruit");
		tweet.setTag("Category");
		tweet.setLoginId("Sourabh@3");
		String result = "Tweet Updated";
		String tweetId = "62e3ff2af3f29702c6c37c61";
		String username="Sourabh@3";
		TweetDTO tweetText = new TweetDTO();
		when(tweetService.updateTweet(username, tweetId,  tweetText)).thenReturn(result);
		assertEquals(result, tweetController.updateTweet(username, tweetId,  tweetText));
	}
	
	@Test
	public void deleteTweetTest () {
		Tweet tweet = new Tweet();
		tweet.setId("62e3ff2af3f29702c6c37c61");
		tweet.setTweet("Orange is my fav fruit");
		tweet.setTag("Category");
		tweet.setLoginId("Sourabh@3");
		String result = "Tweet Deleted";
		String tweetId = "62e397da6846f13409360da3";
		String username="Sourabh@3";
		when(tweetService.deleteById(username , tweetId)).thenReturn(result);
		assertEquals(result, tweetController.deleteTweet(username , tweetId));
	}
	
	@Test
	public void likeTweet () {
		Tweet tweet = new Tweet();
		List<String> likedTweets = new ArrayList<String>();
		likedTweets.add("Gouri@2");
		tweet.setLikedTweets(likedTweets);
		String tweetId = "62e397da6846f13409360da3";
		String username="Gouri@2";
		when(tweetService.likeTweet(username , tweetId)).thenReturn(likedTweets);
		assertEquals(likedTweets, tweetController.likeTweet(username , tweetId));
	}
	
	@Test
	public void replyToTweet () {
		Tweet tweet = new Tweet();
		List<String> reply = new ArrayList<String>();
		reply.add("Sourabh@3");
		reply.add("Hardword pays off");
		tweet.setReply(reply);
		String username="Sourabh@3";
		String tweetId = "62e397da6846f13409360da3";
		TweetDTO tweetText = new TweetDTO();
		when(tweetService.replyToTweet(username, tweetId,  tweetText)).thenReturn(reply);
		assertEquals(reply, tweetController.replyToTweet(username, tweetId,  tweetText));
	}
}

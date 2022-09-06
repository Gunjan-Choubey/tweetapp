package com.tweetapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tweetapp.dto.TweetDTO;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepository;


@ExtendWith(SpringExtension.class)
public class TweetServiceTest {
	
	@InjectMocks
	TweetService tweetService;
	
	@Mock
	private TweetRepository tweetRepository;
	

	
	@Test
	public void getAllTweetsOfUserTest() {
		String username = "Gunjan@1";
		when(tweetRepository.findTweetByUsername(username)).thenReturn(Stream.of(new Tweet(),
				new Tweet()).collect(Collectors.toList()));
		assertEquals(2, tweetService.getAllTweetsOfUser(username).size());
		
	}
	
	@Test
	public void getAllTweetsTest() {
		when(tweetRepository.findAll()).thenReturn(Stream.of(new Tweet(),
				new Tweet()).collect(Collectors.toList()));
		assertEquals(2, tweetService.getAllTweets().size());
	}
	
	@Test
	public void createTweetTest() {
		Tweet tweet = new Tweet();
		tweet.setId(null);
		tweet.setTweet("An apple a day keeps the doctor away");
		tweet.setTag("Taste");
		tweet.setLoginId("Gouri@2");
		String result = "Tweet Posted";
		when(tweetRepository.save(tweet)).thenReturn(tweet);
		String username="Gouri@2";
		TweetDTO tweetText = new TweetDTO();
		assertEquals(result, tweetService.createTweet(username,tweetText));
	}
	
	@Test
	public void updateTweetTest() {
		Tweet tweet = new Tweet();
		String tweetId = "62e3ff2af3f29702c6c37c61";
        //when(tweetRepository.findById(tweetId).get()).thenReturn(tweet);
		when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
		tweet.setId("62e3ff2af3f29702c6c37c61");
		tweet.setTweet("Orange is my fav fruit");
		tweet.setTag("Category");
		tweet.setLoginId("Sourabh@3");
		String result = "Tweet Updated";
		when(tweetRepository.save(tweet)).thenReturn(tweet);
		String username="Sourabh@3";
		TweetDTO tweetText = new TweetDTO();
		assertEquals(result, tweetService.updateTweet(username, tweetId,  tweetText));
	}
	
	
	@Test
	public void deleteByIdTest() {
		Tweet tweet = new Tweet();
		tweet.setId("62e3ff2af3f29702c6c37c61");
		tweet.setTweet("Orange is my fav fruit");
		tweet.setTag("Category");
		tweet.setLoginId("Sourabh@3");
		String tweetId = "62e397da6846f13409360da3";
		String result = "Tweet Deleted";
		when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
		String username="Sourabh@3";
		assertEquals(result, tweetService.deleteById(username , tweetId));
		
	}
	
	@Test
	public void deleteByIdTestCase() {
		Tweet tweet = new Tweet();
		tweet.setId("62e3ff2af3f29702c6c37c61");
		tweet.setTweet("Orange is my fav fruit");
		tweet.setTag("Category");
		tweet.setLoginId("!");
		String tweetId = "6";
		String result = "Tweet not Deleted as username is incorrect";
		when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
		String username= null;
		assertEquals(result, tweetService.deleteById(username , tweetId));
		
	}
	
	@Test
	public void likeTweetTest() {
		Tweet tweet = new Tweet();
		String tweetId = "62e397da6846f13409360da3";
		when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
		List<String> likedTweets = new ArrayList<String>();
		likedTweets.add("Gouri@2");
		tweet.setLikedTweets(likedTweets);
	    when(tweetRepository.save(tweet)).thenReturn(tweet);
		String username="Gouri@2";
		assertEquals(likedTweets, tweetService.likeTweet(username , tweetId));
	}
	
	@Test
	public void replyToTweetTest() {
		Tweet tweet = new Tweet();
		//tweet.setTweet("Orange is my fav fruit");
		String tweetId = "62e397da6846f13409360da3";
		when(tweetRepository.findById(tweetId)).thenReturn(Optional.of(tweet));
		List<String> reply = new ArrayList<String>();
		reply.add("Sourabh@3");
		reply.add(null);
		tweet.setReply(reply);
		when(tweetRepository.save(tweet)).thenReturn(tweet);
		String username="Sourabh@3";
		TweetDTO tweetText = new TweetDTO();
		assertEquals(reply, tweetService.replyToTweet(username, tweetId,  tweetText));
		 
	}

}

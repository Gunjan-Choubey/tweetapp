package com.tweetapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.dto.TweetDTO;
import com.tweetapp.model.Tweet;
import com.tweetapp.repository.TweetRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
public class TweetService {
	
	@Autowired
	private TweetRepository tweetRepository;
	
	
	/*
	 *  This method is used to retrieve all tweets of User based on the given username
	 *  @param username
	 *  
	 *  @return tweetDto
	 */
	public List<TweetDTO> getAllTweetsOfUser(String username) {
		log.info("Entering get all tweets of User - username:{}" , username);
		List<Tweet> tweets = tweetRepository.findTweetByUsername(username);
		List<TweetDTO> tweetDto = new ArrayList<>();
		tweets.forEach(tweet -> {
			tweetDto.add(getTweetDto(tweet));
		});
		log.info("Exiting get all tweets of User - username:{}" , username );
		return tweetDto;
	}

	
	/*
	 *  This method is used to retrieve all the Tweets
	 *  
	 *  @return tweetDtos
	 */
	public List<TweetDTO> getAllTweets() {
		log.info("Entering get all Tweets");
		List<Tweet> tweets = tweetRepository.findAll();
		List<TweetDTO> tweetDtos = new ArrayList<>();
		tweets.forEach(tweet -> {
			tweetDtos.add(getTweetDto(tweet));
		});
		log.info("Exiting get all Tweets");
		return tweetDtos;
	}
	
	private TweetDTO getTweetDto(Tweet tweet) {
		log.info("Entering get TweetDTO tweet:{}" , tweet );
		TweetDTO tweetDTO = new TweetDTO();
		tweetDTO.setTweetId(tweet.getId());
		tweetDTO.setTweet(tweet.getTweet());
		tweetDTO.setDate_time(tweet.getDate_time());
		tweetDTO.setLikedTweets(tweet.getLikedTweets());
		tweetDTO.setReply(tweet.getReply());
		tweetDTO.setTag(tweet.getTag());
		tweetDTO.setLoginId(tweet.getLoginId());
		log.info("Exiting get TweetDTO tweet:{}" , tweet);
		return tweetDTO;
	}

	
	/*
	 *  This method is used to Post a Tweet
	 *  @param username
	 *  @param tweetText
	 *  
	 *  @return result
	 */
	public String createTweet(String username, TweetDTO tweetText) {
		log.info("Entering create Tweet - username:{} tweetText:{}" , username , tweetText);
		Tweet tweet = new Tweet();
		tweet.setId(tweetText.getTweetId());
		tweet.setTweet(tweetText.getTweet());
		tweet.setTag(tweetText.getTag());
		tweet.setLoginId(username);
		tweetRepository.save(tweet);
		String result = "Tweet Posted";
		log.info("Exiting create Tweet");
		return result;
	}

	
	/*
	 *  This method is used to Update the Tweet
	 *  @param username
	 *  @param tweetId
	 *  @param tweetText
	 *  
	 *  @return result
	 */
	public String updateTweet(String username, String tweetId, TweetDTO tweetText) {
		log.info("Entering update Tweet username:{} tweetId:{} tweetText:{}" , username , tweetId , tweetText);
		Tweet tweet = tweetRepository.findById(tweetId).get();
		tweet.setId(tweetId);
		tweet.setTweet(tweetText.getTweet());
		tweet.setTag(tweetText.getTag());
		tweet.setLoginId(username);
		tweetRepository.save(tweet);
		String result = "Tweet Updated";
		log.info("Exiting update Tweet");
		return result;
	}
	
	
	/*
	 *  This method is used to Delete the Tweet
	 *  @param username
	 *  @param tweetId
	 *  
	 *  @return result
	 */
	public String deleteById(String username, String tweetId) {
		log.info("Entering delete Tweet username:{}  tweetId:{} " , username , tweetId);
		Tweet tweet = tweetRepository.findById(tweetId).get();
		if (tweet.getLoginId().equals(username)) {
			tweetRepository.deleteById(tweetId);
			String result = "Tweet Deleted";
			log.info("Exiting delete Tweet");
			return result;
		} else {
			String result = "Tweet not Deleted as username is incorrect";
			log.info("Exiting delete Tweet");
			return result;
		}
	}

	
	/*
	 *  This method is used to Like Tweet
	 *  @param username
	 *  @param tweetId
	 *  
	 *  @return likedTweets
	 */
	public List<String> likeTweet(String username, String tweetId) {
		log.info("Entering Like Tweet - username:{} tweetId:{} ", username , tweetId);
		Tweet tweet = tweetRepository.findById(tweetId).get();
		List<String> likedTweets = new ArrayList<String>();
			likedTweets.add(username);
			tweet.setLikedTweets(likedTweets);
			tweetRepository.save(tweet);
			log.info("Exiting Like tweet");
		    return likedTweets;
	}

	
	/*
	 *  This method is used to Reply to Tweet
	 *  @param username
	 *  @param tweetId
	 *  @param tweetText
	 *  
	 *  @return reply
	 */
	public List<String> replyToTweet(String username, String tweetId, TweetDTO tweetText) {
		log.info("Entering reply Tweet username:{} tweetId:{} tweetText:{}" , username , tweetId , tweetText);
		Tweet tweet = tweetRepository.findById(tweetId).get();
		List<String> reply = new ArrayList<String>();
		reply.add(username);
		reply.add(tweetText.getTweet());
		tweet.setReply(reply);
		tweetRepository.save(tweet);
		log.info("Exiting reply Tweet");
		return reply;	 
	}

}

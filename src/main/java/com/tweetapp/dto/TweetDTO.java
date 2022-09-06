package com.tweetapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetDTO {
	
	private String tweetId;
	private String tweet;
	private LocalDateTime date_time;
	private List<String> likedTweets;
    private String unlikeTweet;
	private List<String> reply;
	private String tag;
	private String loginId;
	
}

package com.tweetapp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("tweet")
public class Tweet {
	@Id
    private String id;
	 
	//private String tweetId;
	
	@NotBlank
	@Size(max = 144)
	private String tweet;

	@NotNull
	private LocalDateTime date_time;
	
	private List<String> likedTweets;
	private String unlikeTweet;
	private List<String> reply;
	private String tag;
	
	private String loginId;
	//private String username;
	
	public Tweet() {
		this.date_time = LocalDateTime.now();
	}
	
	/*public void setLikedTweets(List<String> likedTweets)
	{
	    this.likedTweets = likedTweets;
	}*/

}

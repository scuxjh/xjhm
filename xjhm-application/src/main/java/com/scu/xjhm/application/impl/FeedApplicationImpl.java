package com.scu.xjhm.application.impl;

import java.util.List;
import java.util.Set;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.scu.xjhm.application.FeedApplication;
import com.scu.xjhm.feed.core.domain.Feed;

@Named
@Transactional
public class FeedApplicationImpl implements FeedApplication {

	public Feed getFeed(Long id) {
		return Feed.get(Feed.class, id);
	}
	
	public void creatFeed(Feed feed) {
		
		feed.save();
	}
	
	public void updateFeed(Feed feed) {
		feed .save();
	}
	
	public void removeFeed(Feed feed) {
		if(feed != null){
			feed.remove();
		}
	}
	
	public void removeFeeds(Set<Feed> feeds) {
		for (Feed feed : feeds) {
			feed.remove();
		}
	}
	
	public List<Feed> findAllFeed() {
		return Feed.findAll(Feed.class);
	}
	
}

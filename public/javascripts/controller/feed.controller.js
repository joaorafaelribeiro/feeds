/**
 * 
 */

angular.module('app').controller('feedController',['Feed',function(Feed) {
	
	me = this;
	me.page = 1;
	me.feeds = [];
	me.sources = [];
	me.feed = {favorite:false};
	me.total = 0;
	me.word = null;
	
	me.setFavorite = function(idFeed) {
		me.feed.favorite = !me.feed.favorite;
		Feed.setFavorite(idFeed);
	}
	
	me.getFeeds = function(page) {
		if(page < 1) return;
		me.page = page;
		if(me.word) {
			Feed.search(me.word,page).then(function(response) {
				me.feeds = response.data;
				Feed.getTotalSearch(me.word,page).then(function(resp) {
					me.total = resp.data;
				}) 
			}); 
		} else {
			Feed.getFeeds(page).then(function(response) {
				me.feeds = response.data;
				Feed.getTotalFeeds().then(function(response) {
					me.total = response.data;
				});
			});
			
		}
	}
	
	me.getSources = function() {
		Feed.getSources().then(function(response) {
			me.sources = response.data;
		});
	}
	
	
	me.init = function(){
		me.getFeeds(1);
		me.getSources();
	}
	
}]);
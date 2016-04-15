
/**
 * 
 */

angular.module('app').controller('feedController',['Feed','$uibModal','$scope','hotkeys',function(Feed,$uibModal,$scope,hotkeys) {
	
	me = this;
	me.page = 1;
	me.feeds = [];
	me.sources = [];
	me.feed = {favorite:false,open:false};
	me.total = 0;
	me.source = {id:null,title:'Todos'};
	me.word = null;
	
	me.setFeed = function(feed) {
		me.feed = feed;
		$('#modal1').openModal();
		          
	}
	
	me.setFavorite = function(idFeed) {
		me.feed.favorite = !me.feed.favorite;
		Feed.setFavorite(idFeed);
	}

	me.getFeeds = function(page) {
		me.page = page;
		Feed.getFeeds(page,me.word,me.source.id).then(function(response) {
			me.feeds = response.data;
			Feed.count(me.word,me.source.id).then(function(response) {
				me.total = response.data;
				me.feed = me.feeds[0];
			});
		});
	};
	
	me.getAllFeeds = function(page) {
		me.source = {id:-1,title:'All'};
		me.getFeeds(page);
	}
	
	me.getTodayFeeds = function(page) {
		me.source = {id:-2,title:'Today'};
		me.getFeeds(page);
	}
	
	me.getFavorites = function(page) {
		me.source = {id:-3,title:'Favorites'};
		me.getFeeds(page);
	}
	
	me.setSource = function(source) {
		me.source = source;
		me.getFeeds(1);
	}
	
	
	me.getSources = function() {
		Feed.getSources().then(function(response) {
			me.sources = response.data;
			
		});
	}
	
	me.next = function() {
    	if(me.page < me.total/15) {
    		me.page = me.page + 1;
    	}
    	me.getFeeds(me.page);
    }
	
	me.preview = function() {
    	if(me.page > 1) {
    		me.page = me.page - 1;
    	}
    	me.getFeeds(me.page);
    }
	
	me.init = function(){
		me.getFeeds(1);
		me.getSources();
	}
	
	hotkeys.add({
	    combo: 'left',
	    description: 'Go to page preview',
	    callback: me.preview
	  });
	
	hotkeys.add({
	    combo: 'right',
	    description: 'Go to page next',
	    callback: me.next
	  });
	
	
	
}]);

/**
 * 
 */

angular.module('app').controller('feedController',['Feed','$uibModal','$scope','hotkeys',function(Feed,$uibModal,$scope,hotkeys) {
	
	me = this;
	me.page = 1;
	me.feeds = [];
	me.sources = [];
	me.feed = {favorite:false};
	me.total = 0;
	me.source = {id:null};
	me.word = null;
	
	me.setFavorite = function(idFeed) {
		me.feed.favorite = !me.feed.favorite;
		Feed.setFavorite(idFeed);
	}
	
	me.getFeeds = function(page) {
		if(page < 1) return;
		me.page = page;
		
		Feed.getFeeds(page,me.word,me.source.id).then(function(response) {
			me.feeds = response.data;
			var $i = 1;
			angular.forEach(me.feeds,function(feed) {
				feed.index = $i++;
			});
			Feed.getTotalFeeds(me.word,me.source.id).then(function(response) {
				me.total = response.data;
			});
		});
		
		me.getSources();
			
		
	};
	
	me.show = function(id) {
			Feed.getFeed(id).then(function(response) {
				
				var modalInstance = $uibModal.open({
				      animation: true,
				      templateUrl: 'myModalContent.html',
				      bindToController:true,
				      controller: 'FeedModalController',
				      controllerAs: 'modal',
				      resolve: {feed: response.data}
				    });
			});
			
	};
	
	
	me.setSource = function(source) {
		if(source == null)
			me.source = {id:null}
		else
			me.source = source;
		me.getFeeds(1);
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
	
	hotkeys.add({
	    combo: 'left',
	    description: 'Go to page preview',
	    callback: function() {
	    	if(me.page > 1) {
	    		me.page = me.page - 1;
	    	}
	    	me.getFeeds(me.page);
	    }
	  });
	
	hotkeys.add({
	    combo: 'right',
	    description: 'Go to page next',
	    callback: function() {
	    	if(me.page < me.total/9) {
	    		me.page = me.page + 1;
	    	}
	    	me.getFeeds(me.page);
	    }
	  });
	
}]);


angular.module('app').controller('FeedModalController', function ($scope, $uibModalInstance, feed) {
		modal = this;  
		$scope.feed = feed;
		
		modal.close = function() {
			$uibModalInstance.close();
		}
	});
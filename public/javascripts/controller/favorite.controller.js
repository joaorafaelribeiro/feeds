/**
 * 
 */

angular.module('app').controller('FavoriteController',['Feed','$uibModal','$scope','hotkeys',function(Feed,$uibModal,$scope,hotkeys) {
	
	me = this;
	me.page = 1;
	me.feeds = [];
	me.feed = {favorite:false};
	me.total = 0;
		
	me.getFavorites = function(page) {
		if(page < 1) return;
		me.page = page;
		
		Feed.getFeeds(me.page,null,null,true).then(function(response) {
			me.feeds = response.data;
			var $i = 1;
			angular.forEach(me.feeds,function(feed) {
				feed.index = $i++;
			});
			Feed.getTotalFeeds(null,null,true).then(function(response) {
				me.total = response.data;
			});
		});
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
	
	
	me.init = function(){
		me.getFavorites(1);
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


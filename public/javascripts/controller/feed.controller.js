/**
 * 
 */

angular.module('app').controller('feedController',['Feed','$uibModal','$scope',function(Feed,$uibModal,$scope) {
	
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


angular.module('app').controller('FeedModalController', function ($scope, $uibModalInstance, feed) {
		modal = this;  
		$scope.feed = feed;
		
		modal.close = function() {
			$uibModalInstance.close();
		}
	});
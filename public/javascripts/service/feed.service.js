/**
 * 
 */
angular.module('app').service('Feed',['$http',function($http) {
	
	me = this;
	
	me.getImage = function(idFeed) {
		return $http.get('/image/'+idFeed);
	};
	
	me.getFeeds = function(page,search,rssId,favorite) {
		if(!page) page = 1;
		return $http.get('/feeds',{params:{page:page,search:search,rssId:rssId,favorite:favorite}});
	};
	
	me.getSources = function(page) {
		if(page) 
			return $http.get('/sources?page='+page);
		else
			return $http.get('/sources');
	};
	
	me.getRsses = function() {
		return $http.get('/rsses');
	}

	me.getCategories = function(page) {
		if(!page) page = 1;
		return $http.get('/categories?page='+page);
	};
	
	me.getTotalCategories = function() {
		return $http.get('/categories?count=true');
	};
	
	me.getTotalSources = function() {
		return $http.get('/sources?count=true');
	};
	
	me.getTotalFeeds = function(search,rssId,favorite) {
		return $http.get('/feeds',{params:{search:search,rssId:rssId,count:true,favorite:favorite}});
	};
	
	me.setFavorite = function(idFeed){
		return $http.post('/favorite/'+idFeed);
	}
	
	
	me.getFeed = function(idFeed) {
		return $http.get('/feed/'+idFeed);
	}
	
}]);
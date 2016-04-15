/**
 * 
 */
angular.module('app').service('Feed',['$http',function($http) {
	
	me = this;
	
	me.getImage = function(idFeed) {
		return $http.get('/image/'+idFeed);
	};
	
	me.getSources = function(page) {
		if(page) 
			return $http.get('/sources?page='+page);
		else
			return $http.get('/sources');
	};
	
	me.getRss = function(id) {
		return $http.get('/rss/'+id);
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
	
	me.count = function(search,rssId) {
		return $http.get('/count',{params:{search:search,rssId:rssId}});
	};

	me.getFeed = function(idFeed) {
		return $http.get('/feed/'+idFeed);
	}

	me.getFeeds = function(page,search,rssId) {
		return $http.get('/feeds',{params:{page:page,search:search,rssId:rssId}});
	};
	
//	me.getTodayFeeds = function(page,search) {
//		return $http.get('/today',{params:{page:page,search:search}});
//	}
//	
//	me.getAllFeeds = function(page,search) {
//		return $http.get('/all',{params:{page:page,search:search}});
//	}
//	
//	me.getFavorites = function(page,search){
//		return $http.get('/favorites',{params:{page:page,search:search}});
//	}	
	
}]);
/**
 * control menu 
 */

angular.module('app').controller('MenuController',
		['$scope','Feed',function($scope,Feed) {
			
			var me = this;
			
			me.getFeeds = function(idRss) {
				
				Feed.getRss(idRss).then(function(response) {
					$scope.rss = response.data;
				})
				
				Feed.getFeeds(idRss).then(function(response){
					$scope.feeds = response.data;
				}); 
			}
			
		}]);
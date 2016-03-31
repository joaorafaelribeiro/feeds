/**
 * 
 */

angular.module('app').controller('CategoryController',['Feed',function(Feed) {
	
	me = this;
	me.page = 1;
	me.categories = [];
	me.total = 0;
	
	me.getCategories = function(page) {
		if(page < 1) return;
		me.page = page;
		Feed.getCategories(page).then(function(response) {
			me.categories = response.data;
		});
	};
	
	me.getTotal = function(){
		Feed.getTotalCategories().then(function(response) {
			me.total = response.data;
		});
	};
	
	me.init = function(){
		me.getCategories(1);
		me.getTotal();
	};
	
}]);
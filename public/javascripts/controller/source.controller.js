/**
 * 
 */

angular.module('app').controller('SourcesController',['Feed','$uibModal',function(Feed,$uibModal) {
	
	me = this;
	me.page = 1;
	me.sources = [];
	me.total = 0;
	
	me.getSources = function(page) {
		if(page < 1) return;
		me.page = page;
		Feed.getSources(page).then(function(response) {
			me.sources = response.data;
		});
	};
	
	me.getTotal = function(){
		Feed.getTotalSources().then(function(response) {
			me.total = response.data;
		});
	};
	
	me.init = function(){
		me.getSources(1);
		me.getTotal();
	};
	
}]);
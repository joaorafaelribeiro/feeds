
/**
 * Define o modulo da aplicação  
 */

angular.module('app',['ngSanitize','ui.bootstrap','cfp.hotkeys']);		
		
$(document).ready(function() {
	$('select').material_select();
	$(".button-collapse").sideNav();
	$('.modal-trigger').leanModal();
  });


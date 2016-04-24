
/**
 * Define o modulo da aplicação  
 */

angular.module('app',['ngSanitize','ui.bootstrap','cfp.hotkeys']);		
		
$(document).ready(function() {
	$('select').material_select();
	$(".button-collapse").sideNav();
	$('.modal-trigger').leanModal({
	      dismissible: true, // Modal can be dismissed by clicking outside of the modal
	      opacity: 0, // Opacity of modal background
	      in_duration: 300, // Transition in duration
	      out_duration: 200, // Transition out duration
	    });
	$('.collapsible').collapsible({
	      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
	    });
  });

function displayMenu() {
	if($('#nav-mobile').hasClass('hide')) {
		$('#nav-mobile').removeClass('hide');
		$('#nav-mobile').effect( 'slide', {}, 500  );
		//Materialize.showStaggeredList('#nav-mobile');
	} else {
		$('#nav-mobile').effect( 'drop', {}, 500, function(){$('#nav-mobile').addClass('hide');}  );
		//$('#nav-mobile').addClass('hide');/*function(){$('#nav-mobile').addClass('hide');}*/
	}
}




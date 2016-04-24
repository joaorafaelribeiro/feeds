/**
 * 
 */
$(document).ready(function(){
	
	$('#feedTable').DataTable({
		ajax		: {url:'/relatorio/feeds',dataSrc	: ''},
		serverSide	: true,
		columns 	: [{data:'title'},{data:'date'}]
	});
	
});
function Total(a,b){
	return parseInt(a)+parseInt(b);
}
function Bid(id){
	var bidmi=document.getElementById('bidminimum'+id).value;
	var bidin=document.getElementById('bidincremenent'+id).value;
	var dialog = $("<div title='Bid'>Ban muon dat gia "+Total(bidmi,bidin)+" ?</div>").dialog({
		
		autoOpen: false,
        modal: true,
    buttons: {
    	 "No":  function() {
             dialog.dialog('close');
         },
        "Yes": function() {
        	dialog.dialog('close');
        	if(document.getElementById(id).submit())
        		alert('done');
        	}
       
       
    }
});
	dialog.dialog('open');
}

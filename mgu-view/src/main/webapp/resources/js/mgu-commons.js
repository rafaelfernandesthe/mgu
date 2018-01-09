function getAppPath(){
	return window.location.pathname.substr(0, window.location.pathname.lastIndexOf('/'));
}

function getUrlParams( prop ) {
    var params = {};
    var search =  window.location.href.slice( window.location.href.indexOf( '?' ) + 1 );
    var definitions = search.split( '&' );

    definitions.forEach( function( val, key ) {
        var parts = val.split( '=', 2 );
        if(parts.length > 1){
        	params[ parts[ 0 ] ] = parts[ 1 ];
        }
    } );

    return ( prop && prop in params ) ? params[ prop ] : null;
}

function sleep (time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}

function puipicklist_refresh( value ){
	var selectedOptions = $(value).get(0).options;
	for(var i = 0; i < selectedOptions.length; i++){
		selectedOptions[i].setAttribute('selected','selected');
	}
}

function declare_puipicklist(identifierPicklist, sourceData, targetData, fieldName){
	$(identifierPicklist).puipicklist({
        sourceCaption: 'Disponíveis',
        targetCaption: 'Selecionados',
        filter: true,
        filterMatchMode: 'contains',
        sourceData: sourceData,
        targetData: targetData,
        reponsive: true,
	});
	puipicklist_refresh("#"+fieldName);
	
}

function show_puidialog(identifierDialog, reqType, urlTarget, formToSubmit){
	var token = $("meta[name='_csrf']").attr("content");
  	var header = $("meta[name='_csrf_header']").attr("content");
	
	$(identifierDialog).puidialog({
        showEffect: 'fade',
        hideEffect: 'fade',
        minimizable: false,
        maximizable: false,
        responsive: true,
        minWidth: 200,
        modal: true,
        buttons: 
        	[
        		{
	                text: 'Sim',
	                icon: 'fa-check',
	                click: function() {
	                	$.ajax({
		                    type: reqType,
		                    url: getAppPath() + urlTarget,
		                    beforeSend: function (xhr) {
		                        xhr.setRequestHeader(header, token);
		                    },
		                    context: this,
		                });
	                	
	                	if(formToSubmit != null && formToSubmit != undefined && formToSubmit != ''){
	                		$('#dlg').puidialog('show');
	                		sleep(1000).then(() => {
	                			$(formToSubmit).submit();
	                		});
	                	}
	                }
	            },
	            {
	                text: 'Não',
	                icon: 'fa-close',
	                click: function() {
	                    $(identifierDialog).puidialog('hide');
	                }
	            }
	        ]
    });
	
	$(identifierDialog).puidialog('show');
	
}
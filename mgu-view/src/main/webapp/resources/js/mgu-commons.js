function getAppPath(){
	return "/"+window.location.pathname.split('/')[1];
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

function removeUrlParams( prop ) {
	if(window.location.href.split('?')[1] == undefined)
		return;
	
    var urlRequest = window.location.href.split('?')[0];
    var params = '';
    var search =  window.location.href.slice( window.location.href.indexOf( '?' ) + 1 );
    
    var definitions = search.split( '&' );
    var firstParam = true;
    definitions.forEach( function( val, key ) {
        var parts = val.split( '=', 2 );
        if(parts.length > 1 && parts[0] != prop){
        	if(!firstParam){
        		params += "&"
        	} else{
        		params += "?"
        		firstParam = false;
        	}
        	params += parts[ 0 ] + '=' + parts[ 1 ];
        }
    } );

    window.history.replaceState(null, null, urlRequest + params);
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

function declare_puipicklistCustom(identifierPicklist, sourceData, targetData, fieldName){
	$(identifierPicklist).puipicklist({
		sourceCaption: 'Disponíveis',
		targetCaption: 'Selecionados',
		filter: true,
		filterMatchMode: 'contains',
		sourceData: sourceData,
		targetData: targetData,
		reponsive: true,
		content: function(option) {
			var labelParts = option.label.toString().split("+");
			if(!labelParts[1])
				labelParts[1]="";
			return '<div><span style="text-align:left">'+labelParts[0]+'</span><span style="float:right">'+ labelParts[1] +'</span></div>';
		},
	});
	puipicklist_refresh("#"+fieldName);
}

function show_puidialog(identifierDialog, reqType, urlTarget){
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
	                	$('#dlg').puidialog('show');
	                	
	                	$.ajax({
		                    type: reqType,
		                    url: getAppPath() + urlTarget,
		                    beforeSend: function (xhr) {
		                        xhr.setRequestHeader(header, token);
		                    },
		                    context: this,
		                }).done(function(msg) {
		                			var sucesso = msg.match(/sucesso/i) ? 1 : 0;
		                			document.location = document.location+'&success='+sucesso+'&msgText='+msg;
		                		});
	                	
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

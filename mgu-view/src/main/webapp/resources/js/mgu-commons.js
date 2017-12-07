function getUrlParams( prop ) {
    var params = {};
    var search = decodeURIComponent( window.location.href.slice( window.location.href.indexOf( '?' ) + 1 ) );
    var definitions = search.split( '&' );

    definitions.forEach( function( val, key ) {
        var parts = val.split( '=', 2 );
        if(parts.length > 1){
        	params[ parts[ 0 ] ] = parts[ 1 ];
        }
    } );

    return ( prop && prop in params ) ? params[ prop ] : null;
}
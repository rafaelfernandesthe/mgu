$('.pui-button').puibutton();
$('.pui-breadcrumb').puibreadcrumb();
$('.pui-panel-toggleable').puipanel({
	toggleable: true,
});
$('.pui-panel').puipanel();
$('#growl').puigrowl();
showMessage = function(level, msg) {
    $('#growl').puigrowl('show', [{severity: level, summary: '', detail: msg}]);
};
$('.messageError').puimessages();
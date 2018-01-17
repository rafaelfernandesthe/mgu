$('.pui-button').puibutton();
$('.pui-breadcrumb').puibreadcrumb();
$('.pui-panel-toggleable').puipanel({
	toggleable: true,
});
$('.pui-panel').puipanel();
$('.pui-dropdown').puidropdown();
$('#growl').puigrowl();
showMessage = function(level, msg) {
    $('#growl').puigrowl('show', [{severity: level, summary: '', detail: msg}]);
    
};
$('#dlg').puidialog({
	showEffect: 'fade',
    hideEffect: 'fade',
    effectSpeed: 'slow',
    closable: false,
    draggable: false,
    resizable: false,
    minimizable: false,
    maximizable: false,
	responsive: true,
    modal: true,
    width: 150,
    title: 'Carregando...'
});
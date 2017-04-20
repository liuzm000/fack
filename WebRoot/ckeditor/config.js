/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	//CKEDITOR.basePath = "%BASE_URL%/javascript/CKEditor/ckeditor/";
	config.toolbar = 'Full';
	config.toolbar_Full =
	[
		{ name: 'document', items : [ 'Source','-','DocProps','Preview','Print','-','Templates' ] },
		{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		{ name: 'editing', items : [ 'Find','Replace','-','SelectAll','-' ] },
		{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-' ] },
		{ name: 'links', items : [ 'Link','Unlink','-'] },
		{ name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'] },
		'/',
		{ name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
		{ name: 'colors', items : [ 'TextColor','BGColor' ] },
		{ name: 'tools', items : [ 'Maximize','-','About' ] }
	];
	
	 var ckfinderPath = ""; //ckfinder路径
	 config.filebrowserBrowseUrl = ckfinderPath + '../ckfinder/ckfinder.html'; 
	 config.filebrowserImageBrowseUrl = ckfinderPath + '../ckfinder/ckfinder.html?type=Images'; 
	 config.filebrowserFlashBrowseUrl = ckfinderPath + '../ckfinder/ckfinder.html?type=Flash'; 
	 config.filebrowserUploadUrl = ckfinderPath + '../ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files'; 
	 config.filebrowserImageUploadUrl = ckfinderPath + '../ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images';
	 config.filebrowserFlashUploadUrl = ckfinderPath + '../ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash';
};

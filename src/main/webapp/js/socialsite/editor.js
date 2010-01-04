/*
 *     Copyright SocialSite (C) 2009
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
// Main Package of the application
var SocialSite = SocialSite || {};
// util package
SocialSite.Util = SocialSite.Util || {};

// set the default option for wmd editor
wmd_options = {
	// format sent to the server. Use "Markdown" to return the markdown source.
	output : "HTML",

	// line wrapping length for lists, blockquotes, etc.
	lineLength : 40,

	// toolbar buttons. Undo and redo get appended automatically.
	buttons : "bold italic | link blockquote code image | ol ul heading hr",

	// option to automatically add WMD to the first textarea found. See
	// apiExample.html for usage.
	autostart : false
};

// FIXME send the converted(HTML) code to the server during the
// ajax form submit
SocialSite.Util.Editor = {
	setUp : function() {
		$('textarea.richEditor:not(textarea.processed)')
				.each(this.createEditor).TextAreaResizer();
		SocialSite.Util.Slider.setUp();
		// creates the editor after each ajax response
		SocialSite.Ajax.registerPostAjax(this.PostAjaxSetUp);
	},

	PostAjaxSetUp : function(changed$) {
		var that = SocialSite.Util.Editor;
		changed$.find('textarea.richEditor').each(that.createEditor)
				.TextAreaResizer();
	},
	// create the editor after the dom in loaded
	createEditor : function() {

		// console.log('creating editor');
		var textArea = $(this);
		var form = textArea.parents('form');
		// remove this
		if (textArea.hasClass('processed')) {
			alert('processessing already processed');
		}
		// can't add a hook to the form submit due to the bug WICKET-1448
		// textArea.parents('form')[0].onsubmit = (function(){
		// var form = $(this);
		// console.log('hook called', form);
		// form.find('textarea.richEditor').val(form.find('div.wmd-priview').html());
		// return false;
		// });

		// FIXME this won't work if the user submit the form using the return
		// key
		form.find('a.editor-text').mousedown(
				function() {
					form.find('textarea.richEditor').val(
							form.find('div.wmd-preview').html());
					return false;
				});

		var panes = {
			input : this,
			preview : $(this).next('div.wmd-preview')[0],
			output : null
		};

		// make sure that the other dependencies are loaded
		try {
			var previewManager = new Attacklab.wmd.previewManager(panes);
			// build the editor and tell it to refresh the preview after
			// commands
			var editor = new Attacklab.wmd.editor(this, previewManager.refresh);
		} catch (e) {
			// console.log('the dependencies needed to create editor are not
			// loaded',e);
			// FIXME this error is generated after the modal window is closed
		}
	}
};

$().ready(function() {
	SocialSite.Util.Editor.setUp();
	$('form').submit(function() {
		console.log('working');
		return false;
	});
});

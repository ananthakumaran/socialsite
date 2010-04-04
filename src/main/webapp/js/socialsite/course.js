/**
 * @author Ananth
 */
// Main Package of the application
var SocialSite = SocialSite || {};
// Home package
SocialSite.Course = SocialSite.Course || {};


SocialSite.Course.Note = {
	// attach all the event handler and hide all the event
	init : function() {
		$("div.note").hide();
		$("a.note").click(this.addNoteLinkHandler);
		$('div.note a.cancel').click(this.cancelLinkHandler);
	},

	// show the form when the reply link is clicked
	addNoteLinkHandler : function() {
		$(this).siblings('div.note').show();
		return false;
	},
	// hide the form when the cancel link is clicked
	cancelLinkHandler : function() {
		$(this).parent().parent().parent().hide();
		return false;
	}
};

$().ready(function(){
		SocialSite.Course.Note.init();
});
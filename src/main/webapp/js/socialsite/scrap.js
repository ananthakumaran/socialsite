// Main Package of the application
var SocialSite = SocialSite || {};
// scrap package
SocialSite.Scrap = SocialSite.Scrap || {};

SocialSite.Scrap.Reply = {
	// attach all the event handler and hide all the event
	init : function() {
		$("a.reply").click(this.ReplyLinkHandler);
		$('a.replyCancel').click(this.CancelLinkHandler);
	},
	
	// show the form when the reply link is clicked
	ReplyLinkHandler : function() {
		$(this).siblings('div.replyContainer').show();
		return false;
	},
	// hide the form when the cancel link is clicked
	CancelLinkHandler : function() {
		$(this).parent().parent().hide();
		return false;
	}
};

$().ready(function() {
	SocialSite.Scrap.Reply.init();
});
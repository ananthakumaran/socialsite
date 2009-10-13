// Main Package of the application
var SocialSite = SocialSite || {};
// scrap package
SocialSite.Home = SocialSite.Home || {};

SocialSite.Home.AddAsFriend = {
	// attach all the event handler and hide all the event
	init : function() {
		$("div.addAsFriendContainer").hide();
		$("a.addAsFriend").click(this.addAsFriendLinkHandler);
		$('a.cancel').click(this.cancelLinkHandler);
	},

	// show the form when the reply link is clicked
	addAsFriendLinkHandler : function() {
		$(this).siblings('div.addAsFriendContainer').show();
		return false;
	},
	// hide the form when the cancel link is clicked
	cancelLinkHandler : function() {
		$(this).parent().parent().hide();
		return false;
	},
	// hide the link and the form
	removeAll : function() {
		$("div.addAsFriendContainer").remove();
		$("a.addAsFriend").remove();
	}

}

$().ready(function() {
	SocialSite.Home.AddAsFriend.init();
});
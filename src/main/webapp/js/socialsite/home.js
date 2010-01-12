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
// scrap package
SocialSite.Home = SocialSite.Home || {};

// shows the message text box when the addasfriend link
// is clicked
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
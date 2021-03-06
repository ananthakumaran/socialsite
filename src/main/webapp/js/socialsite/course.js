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
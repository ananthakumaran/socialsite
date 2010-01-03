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
// shows the changeimage link when the user hovers over
// the image
SocialSite.Util.Image = {
    // setup the handlers
    setUp: function(){
        $('image').hover(this.showChangeLink, this.hideChangeLink);
        SocialSite.Util.Image.hideChangeLink();
    },
    // shows the link
    showChangeLink: function(){
        $('imagelink').show();
    },
    // hides the link
    hideChangeLink: function(){
        $('imagelink').hide();
    }
};
// registers the handlers
$(document).ready(function(){
    SocialSite.Util.Image.setUp();
});


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
SocialSite.SignUp = SocialSite.SignUp || {};
// shows the option for admin user
SocialSite.SignUp.Admin = {
    hide: function(){
        
        $("input[type='radio']:not(input.admin)").click(function(){
            $(".university").hide();
        });
		$("input.admin").click(function(){
			 $(".university").show();
		});
		
		// hide if the admin radio is not checked
		if(!$("input:radio:checked").hasClass("admin"))
		{
			$(".university").hide();
		}
    }
};
// register the handlers
$().ready(function(){
    SocialSite.SignUp.Admin.hide();
});




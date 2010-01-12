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

SocialSite.Ajax = {
    // contains the list of handler to be invoked
    // after ajax response
    postAjaxHandlers: [],
    // contains the ids of the changed elements
    changedDomIds: [],
    // registers the post ajax handles
    registerPostAjax: function(fn){
        this.postAjaxHandlers.push(fn);
    },
    // this should be invoked by the the wicket
    handle: function(changed){
        this.changedDomIds = changed;
    },
    // fires the post ajax event with collection of updated dom 
    firePostHandlers: function(){
        var that = SocialSite.Ajax;
        // fire the hanldler only if there is updated dom ids
        if (!that.changedDomIds.length == 0) {
            var selector = '';
            $.each(that.changedDomIds, function(){
                selector += '#' + this + ',';
            });
            var elements = $(selector);
            // invoke the handlers
            $.each(that.postAjaxHandlers, function(){
                this(elements);
            });
            // clear the ids
            that.changedDomIds = [];
        }
    }
};

// shows the busy indicator when
// the user clicks ajax and non-ajax links
SocialSite.Util.Busy = {
    // found in apache wicket wiki pages
    setUp: function(){
        // add hooks to the ajax calls
        // check whether the page contains any ajax links
        if (Wicket.Ajax) {
            $('body').click(this.clickFunc).prepend('<div id="busy_indicator" style="display: none;">Loading...</div>');
            Wicket.Ajax.registerPreCallHandler(this.showBusysign);
            Wicket.Ajax.registerPostCallHandler(this.hideBusysign);
            Wicket.Ajax.registerFailureHandler(this.hideBusysign);
        }
    },
    // hides the busy indicator
    hideBusysign: function(){
        $('#busy_indicator').hide();
    },
    // display the busy indicator
    showBusysign: function(){
        $('#busy_indicator').show();
    },
    // determines whether to display the busy indicator or not
    clickFunc: function(eventData){
        // TODO remove this and write some clean code using jquery helpers
        
        var clickedElement = (window.event) ? event.srcElement : eventData.target;
        if ((clickedElement.tagName.toUpperCase() == 'A' &&
        ((clickedElement.target == null) || (clickedElement.target.length <= 0)) &&
        (clickedElement.href.lastIndexOf('#') != (clickedElement.href.length - 1)) &&
        (!('nobusy' in clickedElement)) &&
        (clickedElement.href.indexOf('skype') < 0) &&
        (clickedElement.href.indexOf('mailto') < 0) &&
        (clickedElement.href.indexOf('WicketAjaxDebug') < 0) &&
        (clickedElement.href.lastIndexOf('.doc') != (clickedElement.href.length - 4)) &&
        (clickedElement.href.lastIndexOf('.csv') != (clickedElement.href.length - 4)) &&
        (clickedElement.href.lastIndexOf('.xls') != (clickedElement.href.length - 4)) &&
        ((clickedElement.onclick == null) ||
        (clickedElement.onclick.toString().indexOf('window.open') <=
        0))) ||
        (clickedElement.parentNode.tagName.toUpperCase() == 'A' &&
        ((clickedElement.parentNode.target == null) || (clickedElement.parentNode.target.length <= 0)) &&
        (clickedElement.parentNode.href.indexOf('skype') < 0) &&
        (clickedElement.parentNode.href.indexOf('mailto') < 0) &&
        (clickedElement.parentNode.href.lastIndexOf('#') != (clickedElement.parentNode.href.length - 1)) &&
        (clickedElement.parentNode.href.lastIndexOf('.doc') != (clickedElement.parentNode.href.length - 4)) &&
        (clickedElement.parentNode.href.lastIndexOf('.csv') != (clickedElement.parentNode.href.length - 4)) &&
        (clickedElement.parentNode.href.lastIndexOf('.xls') != (clickedElement.parentNode.href.length - 4)) &&
        ((clickedElement.parentNode.onclick == null) ||
        (clickedElement.parentNode.onclick.toString().indexOf('window.open') <=
        0))) ||
        (((clickedElement.onclick == null) ||
        ((clickedElement.onclick.toString().indexOf('confirm') <=
        0) &&
        (clickedElement.onclick.toString().indexOf('alert') <= 0) &&
        (clickedElement.onclick.toString().indexOf('Wicket.Palette') <=
        0))) &&
        (clickedElement.tagName.toUpperCase() ==
        'INPUT' &&
        (clickedElement.type.toUpperCase() ==
        'BUTTON' ||
        clickedElement.type.toUpperCase() ==
        'SUBMIT')))) {
            SocialSite.Util.Busy.showBusysign();
        }
    }
};

// slider
SocialSite.Util.Slider = {
    setUp: function(){
        this.attachSlider();
        // registers the slider after the ajax response
        SocialSite.Ajax.registerPostAjax(this.attachSlider);
    },
    
    // attaches slide effect to all the element with slideText class
    // slide$ should contains the list of all the dom updated by the ajax
    // response
    attachSlider: function(slide$){
    
        // apply to the whole body if
        // this is not a ajax call
        slide$ = slide$ || $('body');
        
        // attach the click handlers
        slide$.find('.slideText').click(function(){
            // alias
            var slideDom = $(this);
            var content = slideDom.next();
            var textArea = content.find('textarea');
            if (content.is(':hidden')) {
                textArea.show();
                content.slideDown('normal');
            }
            else {
                content.slideUp('normal', function(){
                    textArea.hide()
                });
            }
            // avoids scrolling to top
            return false;
        });
    }
};

// register the handlers
$(document).ready(function(){
    SocialSite.Util.Busy.setUp();
    Wicket.Ajax.registerPostCallHandler(SocialSite.Ajax.firePostHandlers);
});

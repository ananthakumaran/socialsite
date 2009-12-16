/**
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

package com.socialsite.ajax.fileupload;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.link.IPageLink;
import org.apache.wicket.markup.html.link.InlineFrame;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class UploadPanel extends Panel
{

	private class OnUploadedBehavior extends AbstractDefaultAjaxBehavior
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public String getCallback()
		{
			return generateCallbackScript(
					"wicketAjaxGet('" + getCallbackUrl(false)
							+ "&amp;amp;newFileUrl=' + encodeURIComponent(newFileUrl)"
							+ " + '&amp;amp;clientFileName=' + encodeURIComponent(clientFileName)")
					.toString();
		}

		@Override
		protected void respond(final AjaxRequestTarget target)
		{
			onUploadFinished(target, getRequest().getParameter("clientFileName"), getRequest()
					.getParameter("newFileUrl"));
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InlineFrame uploadIFrame = null;

	public UploadPanel(final String id)
	{
		super(id);
		addOnUploadedCallback();
		setOutputMarkupId(true);
	}

	/**
	 * Hackie method allowing to add a javascript in the page defining the
	 * callback called by the innerIframe
	 * 
	 */
	private void addOnUploadedCallback()
	{
		final OnUploadedBehavior onUploadBehavior = new OnUploadedBehavior();
		add(onUploadBehavior);
		add(new WebComponent("onUploaded")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(final MarkupStream markupStream,
					final ComponentTag openTag)
			{
				// calling it through setTimeout we ensure that the callback is
				// called
				// in the proper execution context, that is the parent frame
				replaceComponentTagBody(markupStream, openTag, "function onUpload_"
						+ UploadPanel.this.getMarkupId()
						+ "(clientFileName, newFileUrl) {window.setTimeout(function() { "
						+ onUploadBehavior.getCallback() + " }, 0 )}");
			}
		});
	}

	/**
	 * Create the iframe containing the upload widget
	 * 
	 */
	private void addUploadIFrame()
	{
		final IPageLink iFrameLink = new IPageLink()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Page getPage()
			{
				return new UploadIFrame()
				{
					@Override
					protected String getOnUploadedCallback()
					{
						return "onUpload_" + UploadPanel.this.getMarkupId();
					}

					@Override
					protected String manageInputSream(final FileUpload upload)
					{
						return UploadPanel.this.onFileUploaded(upload);
					}
				};
			}

			public Class<UploadIFrame> getPageIdentity()
			{
				return UploadIFrame.class;
			}
		};
		uploadIFrame = new InlineFrame("upload", getPage().getPageMap(), iFrameLink);
		add(uploadIFrame);
	}

	@Override
	protected void onBeforeRender()
	{
		super.onBeforeRender();
		if (uploadIFrame == null)
		{
			// the iframe should be attached to a page to be able to get its
			// pagemap,
			// that's why i'm adding it in onBeforRender
			addUploadIFrame();
		}
	}

	/**
	 * Called when the upload load is uploaded and ready to be used Return the
	 * url of the new uploaded resource
	 * 
	 * @param upload
	 *            {@link FileUpload}
	 */
	public abstract String onFileUploaded(FileUpload upload);

	/**
	 * Called once the upload is finished and the traitment of the
	 * {@link FileUpload} has been done in {@link UploadPanel#onFileUploaded}
	 * 
	 * @param target
	 *            an {@link AjaxRequestTarget}
	 * @param fileName
	 *            name of the file on the client side
	 * @param newFileUrl
	 *            Url of the uploaded file
	 */
	public abstract void onUploadFinished(AjaxRequestTarget target, String filename,
			String newFileUrl);;
}
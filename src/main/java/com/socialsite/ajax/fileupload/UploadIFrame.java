package com.socialsite.ajax.fileupload;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public abstract class UploadIFrame extends WebPage
{

	private boolean uploaded = false;
	private FileUploadField uploadField;
	private String newFileUrl;

	/** Feedback panel */
	private final FeedbackPanel feedback;

	public UploadIFrame()
	{
		add(new UploadForm("form"));
		// feedback panel
		feedback = new FeedbackPanel("feedback");
		feedback.setOutputMarkupId(true);
		add(feedback);
		addOnUploadedCallback();
	}

	/**
	 * return the callback url when upload is finished
	 * 
	 * @return callback url when upload is finished
	 */
	protected abstract String getOnUploadedCallback();

	/**
	 * Called when the input stream has been uploaded and when it is available
	 * on server side return the url of the uploaded file
	 * 
	 * @param upload
	 *            fileUpload
	 */
	protected abstract String manageInputSream(FileUpload upload);

	private class UploadForm extends Form<Void>
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UploadForm(String id)
		{
			super(id);
			uploadField = new FileUploadField("file");
			add(uploadField);
			add(new AjaxLink<Void>("submit")
			{
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(AjaxRequestTarget target)
				{
					target.appendJavascript("showProgressWheel()");
				}
			});
		}

		@Override
		public void onSubmit()
		{
			FileUpload upload = uploadField.getFileUpload();
			newFileUrl = manageInputSream(upload);
			// file is now uploaded, and the IFrame will be reloaded, during
			// reload we need to run the callback
			uploaded = true;
		}

	}

	private void addOnUploadedCallback()
	{
		// a hacked component to run the callback on the parent
		add(new WebComponent("onUploaded")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag)
			{
				if (uploaded)
				{
					if (uploadField.getFileUpload() != null)
					{
						replaceComponentTagBody(markupStream, openTag, "window.parent."
								+ getOnUploadedCallback() + "('"
								+ uploadField.getFileUpload().getClientFileName() + "','"
								+ newFileUrl + "')");
					}
					uploaded = false;
				}
			}
		});
	}
}
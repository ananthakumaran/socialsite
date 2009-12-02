package com.socialsite;

import org.apache.wicket.Application;
import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.socialsite.authentication.LoginPage;
import com.socialsite.authentication.UserRolesAuthorizer;
import com.socialsite.image.UserImageResource;

/**
 * 
 * starts and intializes all the settings the SocialSite Application
 * 
 * @author Ananth
 */
public class SocialSiteApplication extends WebApplication
{
	private static final long	serialVersionUID	= 1L;

	public static SocialSiteApplication get()
	{
		return (SocialSiteApplication) Application.get();
	}

	/**
	 * Constructor
	 */
	public SocialSiteApplication()
	{
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<LoginPage> getHomePage()
	{
		return LoginPage.class;
	}

	/*
	 * overide the init method to initialize the springDAo
	 */
	@Override
	protected void init()
	{

		super.init();

		getDebugSettings().setDevelopmentUtilitiesEnabled(true);

		// add spring listener to the application
		addComponentInstantiationListener(new SpringComponentInjector(this));

		// intializes the security listeners
		initSecurity();

		// enables wicket autolinking
		getMarkupSettings().setAutomaticLinking(true);

		// user image
		getSharedResources().add("userImageResource", new UserImageResource());

	}

	/**
	 * initializes all the security settings
	 * 
	 */
	public void initSecurity()
	{
		// set the authorization Strategy
		getSecuritySettings().setAuthorizationStrategy(
			new RoleAuthorizationStrategy(new UserRolesAuthorizer()));
		// MetaDataRoleAuthorizationStrategy.authorize(HomePage.class, "ADMIN");
		// getSecuritySettings().setUnauthorizedComponentInstantiationListener(
		// new SocialSiteUnauthorizedComponentInstantiationListener());
		//
		// // Register the component instatiation listener to check the
		// // authorization of each page
		// addComponentInstantiationListener(new
		// IComponentInstantiationListener() {
		//
		// public void onInstantiation(Component component) {
		// if (!getSecuritySettings().getAuthorizationStrategy()
		// .isInstantiationAuthorized(component.getClass())) {
		// getSecuritySettings()
		// .getUnauthorizedComponentInstantiationListener()
		// .onUnauthorizedInstantiation(component);
		// }
		// }
		// });
	}

	/**
	 * 
	 * defining the custom session
	 * 
	 * @return custom session object
	 */
	@Override
	public Session newSession(final Request request, final Response response)
	{
		return new SocialSiteSession(request);
	}

}

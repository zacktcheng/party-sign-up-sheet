package com.zacktcheng.signupsheet.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SignupSheetAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		Class[] configFiles = {SignupSheetAppConfig.class};
		return configFiles;
	}

	@Override
	protected String[] getServletMappings() {
		String[] mappings = {"/"};
		return mappings;
	}

}

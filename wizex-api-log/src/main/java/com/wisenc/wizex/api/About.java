package com.wisenc.wizex.api;

public class About {

	private static final String specifyTitle = "Wise & Consulting Product Platform for Java";
	private static final String majorVersion = "2023-01-12-00";
	private static final String vendorCorp = "Wise and Consulting Corp.,LTD.";
	private static final String verifyClass = About.class.getName();
	private static final String release = specifyTitle + ", V" + majorVersion;

	public static String getReleaseInfo()
	{
		return release;
	}

	public static void main( String[] args )
	{
		System.out.println( "Specify Title: " + specifyTitle );
		System.out.println( "Product Major Version: " + majorVersion ); // MajorVersion
		System.out.println( "Vendor Corp: " + vendorCorp );
		System.out.println( "Verify Class: " + verifyClass );
	}


}

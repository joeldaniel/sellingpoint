
package org.mav.sellingpoint.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestResourcesRegistry extends ResourceConfig{

	public RestResourcesRegistry(){
		packages("org.mav.sellingpoint.exception");
		packages("org.mav.sellingpoint.rest");
	}
}

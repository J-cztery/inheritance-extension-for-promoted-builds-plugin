package hudson.plugins.project_inheritance.extensions.promoted_builds;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import hudson.model.AbstractProject;
import hudson.model.Job;
import hudson.model.JobProperty;

public class JobPropertyOwnerSetter {
	private static Logger logger = Logger.getLogger(JobPropertyOwnerSetter.class);
	public static void setOwner(JobProperty jobProperty, Job owner) {
		
		
		try {
			Class[] cArg = new Class[1];
	        cArg[0] = AbstractProject.class;

			Method m =jobProperty.getClass().getDeclaredMethod("setOwner", cArg );

			m.setAccessible(true);
			m.invoke(jobProperty, owner);
		}  catch (Exception ex){
			logger.error("Error setting owner", ex);
		}
		
		
		
	}
}

package hudson.plugins.project_inheritance.extensions.promoted_builds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import hudson.model.AbstractItem;
import hudson.model.AbstractProject;
import hudson.model.Job;
import hudson.model.JobProperty;
import hudson.plugins.promoted_builds.JobPropertyImpl;
import hudson.plugins.promoted_builds.PromotionProcess;

public class JobPropertyImplReflectionHelper {
	private static Logger logger = Logger.getLogger(JobPropertyImplReflectionHelper.class);
	
	public static void copyActiveProcessNames(JobPropertyImpl from, JobPropertyImpl to){
		try {
			Field activeProcessNamesField = from.getClass().getDeclaredField("activeProcessNames");
			activeProcessNamesField.setAccessible(true);
			Set<String> fromValue = (Set<String>) activeProcessNamesField.get(from);
			Set<String> toValue = (Set<String>) activeProcessNamesField.get(to);
			toValue.clear();
			for (String s: fromValue){
				toValue.add(s);
			}
		} catch (Exception ex) {
			logger.error("Error copying active process names", ex);
		}
		
	}
	
	public static void copyProcesses(JobPropertyImpl from, JobPropertyImpl to){
		try {
			Field activeProcessNamesField = from.getClass().getDeclaredField("processes");
			activeProcessNamesField.setAccessible(true);
			List<PromotionProcess> fromValue = (List<PromotionProcess>) activeProcessNamesField.get(from);
			List<PromotionProcess> toValue = (List<PromotionProcess>) activeProcessNamesField.get(to);
			toValue.clear();
			for (PromotionProcess s: fromValue){
				toValue.add(s);
				setPromotionProcessParent(s, to);
			}
		} catch (Exception ex) {
			logger.error("Error copying processes", ex);
		}
	}
	
	private static void setPromotionProcessParent(PromotionProcess s, JobPropertyImpl to) {
		try {
			Field parentField = AbstractItem.class.getDeclaredField("parent");
			parentField.setAccessible(true);
			parentField.set(s,  to);
		} catch (Exception ex) {
			logger.error("Error setting process parent", ex);
		}
	}

	public static void buildActiveProcessJobPropertyImpl(JobPropertyImpl jobPropertyImpl){
		try{
			Method m = jobPropertyImpl.getClass().getDeclaredMethod("buildActiveProcess");	
			m.setAccessible(true);
			m.invoke(jobPropertyImpl);
		}catch (Exception ex){
			logger.error("Error init-ing job property ", ex);
		}
	}
	
}

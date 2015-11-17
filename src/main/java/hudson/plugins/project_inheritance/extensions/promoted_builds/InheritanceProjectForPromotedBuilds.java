package hudson.plugins.project_inheritance.extensions.promoted_builds;

import java.io.File;

import hudson.model.ItemGroup;
import hudson.model.JobProperty;
import hudson.plugins.project_inheritance.projects.InheritanceProject;
import hudson.plugins.promoted_builds.JobPropertyImpl;

public class InheritanceProjectForPromotedBuilds extends InheritanceProject{
	JobPropertyImpl originalProperty;
	JobPropertyImpl overridenProperty = null;
	public InheritanceProjectForPromotedBuilds (ItemGroup<?> parent, String name, boolean isTransient, JobPropertyImpl originalProperty) {
		super(parent,name, isTransient);
		this.originalProperty = originalProperty;
	}
	public void setOverridenJobProperty(JobPropertyImpl overridenProperty){
		this.overridenProperty = overridenProperty;
	}
	@Override
	public <T extends JobProperty> T getProperty(Class<T> clazz) {
		
		if (overridenProperty != null &&  clazz.equals(overridenProperty.getClass()) ){
			return (T) overridenProperty;
		}
		
		return (T) super.getProperty(clazz);
		
	};
	@Override
	public File getRootDir(){
		return originalProperty.getOwner().getRootDir();
	}
}

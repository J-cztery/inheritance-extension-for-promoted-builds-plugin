package hudson.plugins.project_inheritance.extensions.promoted_builds;

import java.io.File;

import hudson.model.AbstractProject;
import hudson.model.ItemGroup;
import hudson.model.JobProperty;
import hudson.model.Label;
import hudson.plugins.project_inheritance.projects.InheritanceProject;
import hudson.security.ACL;

public class InheritanceProjectForPromotedBuilds extends InheritanceProject{
	File originalRootDir;
	InheritanceProject original;
	public InheritanceProjectForPromotedBuilds (ItemGroup<?> parent, String name, boolean isTransient,InheritanceProject original, File originalRootDir) {
		super(parent,name+"_fake", isTransient);
		this.originalRootDir = originalRootDir;
		this.original = original;
	}
	
	@Override
	public File getRootDir(){
		return originalRootDir;
	}
	@Override public ACL getACL(){
		return original.getACL();
	}
	@Override public Label getAssignedLabel(){
		return original.getAssignedLabel();
	}
	@Override public <T extends JobProperty> T getProperty(Class<T> clazz){
		return original.getProperty(clazz);
	}
	@Override public JobProperty getProperty(String className){
		return original.getProperty(className);
	}
	@Override public AbstractProject<?,?> getRootProject(){
		return original.getRootProject();
	}
}

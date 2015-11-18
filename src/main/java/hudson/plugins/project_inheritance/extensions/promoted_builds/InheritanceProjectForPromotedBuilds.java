package hudson.plugins.project_inheritance.extensions.promoted_builds;

import java.io.File;

import hudson.model.ItemGroup;
import hudson.plugins.project_inheritance.projects.InheritanceProject;

public class InheritanceProjectForPromotedBuilds extends InheritanceProject{
	File originalRootDir;
	
	public InheritanceProjectForPromotedBuilds (ItemGroup<?> parent, String name, boolean isTransient, File originalRootDir) {
		super(parent,name, isTransient);
		this.originalRootDir = originalRootDir;
	}
	
	@Override
	public File getRootDir(){
		return originalRootDir;
	}
}

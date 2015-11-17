/**
 * Copyright (c) 2011-2013, Intel Mobile Communications GmbH
 * 
 * 
 * This file is part of the Inheritance plug-in for Jenkins.
 * 
 * The Inheritance plug-in is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation in version 3
 * of the License
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */

package hudson.plugins.project_inheritance.extensions.promoted_builds;

import hudson.Extension;
import hudson.model.JobProperty;
import hudson.model.ParametersDefinitionProperty;
import hudson.model.Descriptor.FormException;
import hudson.plugins.project_inheritance.projects.InheritanceProject;
import hudson.plugins.project_inheritance.projects.inheritance.InheritanceSelector;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import hudson.plugins.promoted_builds.JobPropertyImpl;


/**
 *  
 * @author Jacek Tomaka
 */
@Extension
public class JobPropertyImplSelector extends InheritanceSelector<JobProperty<?>> {
	private static final long serialVersionUID = 6297336734737164557L;
	private static final Logger logger = Logger.getLogger(JobPropertyImpl.class.getName());

	@Override
	public boolean isApplicableFor(Class<?> clazz){
		return JobProperty.class.isAssignableFrom(clazz);
	}
	
	@Override
	public InheritanceSelector.MODE getModeFor(Class<?> clazz){
		if (JobPropertyImpl.class.isAssignableFrom(clazz)) return MODE.USE_LAST;
		return MODE.NOT_RESPONSIBLE;
	}
	
	@Override
	public String getObjectIdentifier(JobProperty<?> obj){
		if (JobPropertyImpl.class.getName().equals(obj.getClass().getName())){
			return "JobPropertyImpl";
		}
		return null;
	}
	
	@Override
	public JobPropertyImpl merge(JobProperty<?> prior, JobProperty<?> latter, InheritanceProject caller){
		return null;
	}
	
	@Override
	public JobProperty<?> handleSingleton(JobProperty<?> object, InheritanceProject caller){
		try {
			if (!isApplicableFor(object.getClass())) return object;
			InheritanceProjectForPromotedBuilds fakeRootProject = new InheritanceProjectForPromotedBuilds(caller.getParent(), caller.getName(), true,(JobPropertyImpl) object);
			JobPropertyImpl jobPropertyImpl = new JobPropertyImpl(fakeRootProject);
			fakeRootProject.setOverridenJobProperty(jobPropertyImpl);
			
			return jobPropertyImpl;
		} catch (FormException e) {
			logger.log(Level.ERROR, e);
		} catch (IOException e) {
			logger.log(Level.ERROR, e);
		} catch (Exception e){
			logger.log(Level.ERROR, e);
		}
		return null;

	}
	
	
	
}


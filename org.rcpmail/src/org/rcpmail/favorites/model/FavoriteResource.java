package org.rcpmail.favorites.model;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;

public class FavoriteResource implements IFavoriteItem{
	private String name;
	private IResource resource;
	private IPath path;
	
/*
	public FavoriteResource(IProject iProject) {
		this.setLocation(iProject.getFullPath().toString());
		this.setName(iProject.getName());
	}
*/
	public FavoriteResource(IResource resource) {
		this.resource = resource;
		this.path = resource.getFullPath();		
	}

	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		if (null == name)
			return resource.getName();
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getLocation() {	
		if (null == path)
			return "";
		return path.toString();
	}

	
	public boolean isFavoriteFor(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getInfo() {
		if (null == path)
			return this.name + ": ";
		return this.name + ":"+ path.toString();
	}

	public FavoriteItemType getType() {
		return FavoriteItemType.getType(resource);
	}

}

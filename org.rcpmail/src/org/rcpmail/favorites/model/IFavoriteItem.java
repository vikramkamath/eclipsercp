package org.rcpmail.favorites.model;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;

public interface IFavoriteItem extends IAdaptable{
	String getName();
	void setName(String newName);
	String getLocation();
	boolean isFavoriteFor(Object obj);
	FavoriteItemType getType();
	/*
	 * This is to be able to return state of each item 
	 * so that the item can be correctly reconstructed
	 */
	String getInfo();
}

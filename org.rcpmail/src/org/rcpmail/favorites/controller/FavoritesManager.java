package org.rcpmail.favorites.controller;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.internal.resources.WorkspaceRoot;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.rcpmail.favorites.model.FavoriteResource;
import org.rcpmail.favorites.model.IFavoriteItem;

/*
 * Singleton Object
 */
public class FavoritesManager {
	private static FavoritesManager manager;
	private static Collection<IFavoriteItem> favorites;
	
	private FavoritesManager(){}
	public static FavoritesManager getInstance(){
		if (null == manager){
			manager = new FavoritesManager();
			favorites = new HashSet<IFavoriteItem>();
		}
		return manager;
	}
	
	public IFavoriteItem[] getFavorites() {
		if (null == favorites || favorites.isEmpty())
			//return new IFavoriteItem[0];
			loadFavorites();
		return favorites.toArray(new IFavoriteItem[favorites.size()]);
	}
	
	/*
	 * Load default Favorites
	 */
	private void loadFavorites(){
		IWorkspaceRoot workSpaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = workSpaceRoot.getFolder(new Path("org.rcpmail/icons"));
		favorites.add(new FavoriteResource(resource));
	}
	
	public void addFavorite(IResource resource){
		FavoriteResource favoriteResource = new FavoriteResource(resource);
		try{
			favorites.add(favoriteResource);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void addFavorites(IResource[] resources){
		for (IResource resource: resources){
			favorites.add(new FavoriteResource(resource));
		}
	}
	
	public void truncateFavorites() {
		if (favorites.size() > 0){
			favorites.clear();
		}		
	}
	
}

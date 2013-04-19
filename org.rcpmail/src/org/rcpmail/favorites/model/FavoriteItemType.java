package org.rcpmail.favorites.model;

import java.lang.reflect.Type;
import java.util.HashMap;

import org.eclipse.core.resources.IResource;

public enum FavoriteItemType {
	IFILE (org.eclipse.core.resources.IFile.class) {
		@Override
		public Class<?> getRunTimeClass() {
			//return this.getClazz();
			return org.eclipse.core.resources.IFile.class;
		}
	}
	,IPROJECT (org.eclipse.core.resources.IProject.class) {
		@Override
		public Class<?> getRunTimeClass() {
			//return this.getClazz();
			return org.eclipse.core.resources.IProject.class;
		}
	}
	,IWORKSPACEROOT (org.eclipse.core.resources.IWorkspaceRoot.class) {
		@Override
		public Class<?> getRunTimeClass() {
			//return this.getClazz();
			return org.eclipse.core.resources.IWorkspaceRoot.class;
		}
	}, IFOLDER (org.eclipse.core.resources.IFolder.class){

		@Override
		public Class<?> getRunTimeClass() {
			// TODO Auto-generated method stub
			return org.eclipse.core.resources.IFolder.class;
		}
	};
	
	private static final HashMap<Class<?>, FavoriteItemType> favoriteItemMap =
		new HashMap<Class<?>, FavoriteItemType>();
	/*
	 * Prepopulate
	 */
	static{
		for (FavoriteItemType type: FavoriteItemType.values()){
			favoriteItemMap.put(type.getRunTimeClass(), type);
		}
		//System.out.println(favoriteItemMap.toString());
	}
	private Class<?> clazz;
	
	private FavoriteItemType(Class<?> clazz) {
		System.out.println("clazz member:"+ clazz);
		this.setClazz(clazz);
	}
	
	
	
	public static FavoriteItemType getType(IResource resource){
		FavoriteItemType favoriteItemType = null;
		if (null == resource){
			throw new ResourceNullException();
		}
		
		
		/*
		 * try to match atleast one Interface
		 */
		Type[] interfaces = resource.getClass().getGenericInterfaces();
		for (Type type: interfaces){
			//System.out.println("looking for key:"+ type);
			favoriteItemType = favoriteItemMap.get(type);
			if (null != favoriteItemType){
				break;
			}
		}
		
		/*
		 * Throws IllegalFavoriteResourceTypeException
		 */
		if(null == favoriteItemType){
			throw new IllegalFavoriteResourceTypeException();
		}
				
		return favoriteItemType;
	}
	
	public abstract Class<?> getRunTimeClass();

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getClazz() {
		return clazz;
	}

}

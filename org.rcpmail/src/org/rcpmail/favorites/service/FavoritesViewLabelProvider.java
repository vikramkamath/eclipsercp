package org.rcpmail.favorites.service;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.graphics.Image;
import org.rcpmail.favorites.model.IFavoriteItem;

public class FavoritesViewLabelProvider implements ITableLabelProvider{

	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		StringBuilder columnText = new StringBuilder("");
		if (element instanceof IFavoriteItem){
			IFavoriteItem favoriteItem = (IFavoriteItem) element;
			switch(columnIndex){
			case 0: 
				columnText.append(favoriteItem.getType().toString());
				break;
			case 1:
				columnText.append(favoriteItem.getName());
				break;
			case 2:
				columnText.append(favoriteItem.getLocation());
				break;
			default:
				break;
			}
		}		
		return columnText.toString();
	}

}

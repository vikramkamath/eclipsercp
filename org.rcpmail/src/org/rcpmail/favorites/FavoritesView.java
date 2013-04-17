package org.rcpmail.favorites;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class FavoritesView extends ViewPart {
	public static final String ID = "org.rcpmail.favoritesView";
	
	public FavoritesView(){}
	
	
	@Override
	public void createPartControl(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText("Successful Setup");
	}

	@Override
	public void setFocus() {
	}

}

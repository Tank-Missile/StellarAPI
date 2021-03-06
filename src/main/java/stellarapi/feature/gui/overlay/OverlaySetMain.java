package stellarapi.feature.gui.overlay;

import stellarapi.api.gui.overlay.IOverlaySetType;
import stellarapi.api.gui.overlay.IRawOverlayElement;

public class OverlaySetMain implements IOverlaySetType {

	@Override
	public String getLanguageKey() {
		return "gui.overlay.main";
	}

	@Override
	public boolean acceptOverlayByDefault(IRawOverlayElement overlay) {
		return false;
	}

	@Override
	public boolean isMain() {
		return true;
	}

}

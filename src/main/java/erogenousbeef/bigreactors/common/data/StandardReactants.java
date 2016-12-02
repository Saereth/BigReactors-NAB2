package erogenousbeef.bigreactors.common.data;

import erogenousbeef.bigreactors.api.data.SourceProductMapping;
import erogenousbeef.bigreactors.api.registry.Reactants;
import erogenousbeef.bigreactors.common.BigReactors;

public class StandardReactants {

	public static final String yellorium = "yellorium";
	public static final String cyanite = "cyanite";
	public static final String blutonium = "blutonium";
	public static final String uFuel = "uFuel";
	
	public static final int colorYellorium = BigReactors.defaultFluidColorFuel;
	public static final int colorCyanite = BigReactors.defaultFluidColorWaste;
	public static final int coloruFuel = BigReactors.fluidColorUranium;
	
	// These are used as fallbacks
	public static SourceProductMapping yelloriumMapping;
	public static SourceProductMapping cyaniteMapping;
	public static SourceProductMapping uFuelMapping;
	
	public static void register() {
		Reactants.registerReactant(yellorium, 0, colorYellorium);
		Reactants.registerReactant(cyanite, 1, colorCyanite);
		Reactants.registerReactant(blutonium, 0, colorYellorium);
		Reactants.registerReactant(uFuel, 0, coloruFuel);
	}
	
}

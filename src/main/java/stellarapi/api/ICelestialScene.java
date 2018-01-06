package stellarapi.api;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.annotation.Nullable;

import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import stellarapi.api.celestials.ICelestialCollection;
import stellarapi.api.celestials.ICelestialObject;
import stellarapi.api.celestials.IEffectorType;

public interface ICelestialScene {
	/**
	 * Register celestial collections here.
	 * */
	public void onRegisterCollection(Consumer<ICelestialCollection> colRegistry,
			BiConsumer<IEffectorType, ICelestialObject> effRegistry);

	/**
	 * Creates coordinates, or returns <code>null</code> if this pack doesn't provide coordinates.
	 * Nonnull for now.
	 * TODO Refactor on 1.13
	 * */
	public ICelestialCoordinates createCoordinates();

	/**
	 * Creates sky effect, or returns <code>null</code> if this pack doesn't provide sky effect.
	 * Nonnull for now.
	 * */
	public ISkyEffect createSkyEffect();

	/**
	 * Replaces current WorldProvider with the new one. Return <code>null</code> to do nothing.
	 * Can put some things in the world as well.
	 * TODO Group this with some synchronization
	 * */
	public @Nullable WorldProvider replaceWorldProvider(WorldProvider provider);
}

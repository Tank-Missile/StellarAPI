package stellarapi.feature.celestial.tweakable;

import com.google.common.collect.ImmutableSet;

import net.minecraft.world.World;
import stellarapi.api.celestials.EnumCelestialCollectionType;
import stellarapi.api.celestials.ICelestialCollection;
import stellarapi.api.celestials.ICelestialObject;
import stellarapi.api.lib.math.SpCoord;

public class SAPICollection implements ICelestialCollection {

	public final SAPISun sun;
	public final SAPIMoon moon;

	public SAPICollection(World world, double day, double monthInDay) {
		this.sun = new SAPISun(world, day);
		this.moon = new SAPIMoon(world, day, monthInDay);
	}

	@Override
	public String getName() {
		return "Tweakable Vanilla";
	}

	@Override
	public ImmutableSet<ICelestialObject> getObjects() {
		return ImmutableSet.of(sun, moon);
	}

	@Override
	public ImmutableSet<ICelestialObject> getObjectInRange(SpCoord pos, double radius) {
		ImmutableSet.Builder<ICelestialObject> builder = ImmutableSet.builder();
		if (pos.distanceTo(sun.getCurrentHorizontalPos()) < radius)
			builder.add(sun);
		if (pos.distanceTo(moon.getCurrentHorizontalPos()) < radius)
			builder.add(moon);
		return builder.build();
	}

	@Override
	public ICelestialObject getNearerObject(SpCoord pos, ICelestialObject obj1, ICelestialObject obj2) {
		return (pos.distanceTo(obj1.getCurrentHorizontalPos()) < pos.distanceTo(obj2.getCurrentHorizontalPos())) ? obj1
				: obj2;
	}

	@Override
	public int searchOrder() {
		return 0;
	}

	@Override
	public boolean isBackground() {
		return false;
	}

	@Override
	public EnumCelestialCollectionType getCollectionType() {
		return EnumCelestialCollectionType.System;
	}

}

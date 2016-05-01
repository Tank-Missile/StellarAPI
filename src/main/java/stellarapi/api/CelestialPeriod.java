package stellarapi.api;

import stellarapi.api.lib.math.Spmath;

public class CelestialPeriod {
	
	private String periodName;
	private double periodLength;
	private double zeroTimeOffset;
	
	public CelestialPeriod(String periodName, double periodLength, double zeroTimeOffset) {
		this.periodName = periodName;
		this.periodLength = periodLength;
		this.zeroTimeOffset = zeroTimeOffset;
	}
	
	/**
	 * Name of this period. <p>
	 * Conventionally, each word consisting the name starts with capitals, and all other words are small letters. <p>
	 * For example, normal day is 'Day', normal lunar month is 'Lunar Month', and normal year is 'Year'. <p>
	 * */
	public String getPeriodName() {
		return this.periodName;
	}
	
	/**
	 * Length of the period in tick.
	 * */
	public double getPeriodLength() {
		return this.periodLength;
	}
	
	public double getZerotimeOffset() {
		return this.zeroTimeOffset;
	}
	
	/**
	 * Calculates offset for certain time, always in range [0, 1)
	 * @param worldTime the time of the world in tick
	 * @param partialTicks the partial tick
	 * @return the offset for this period on the time
	 * */
	public double getOffset(long worldTime, float partialTicks) {
		return Spmath.fmod(this.zeroTimeOffset + (worldTime + partialTicks) / this.periodLength, 1.0);
	}
	
	/**
	 * Calculates biased offset for certain time, always in range [0, 1)
	 * @param worldTime the time of the world in tick
	 * @param partialTicks the partial tick
	 * @param bias the biased offset
	 * @return the biased offset for this period on the time
	 * */
	public double getBiasedOffset(long worldTime, float partialTicks, double bias) {
		return Spmath.fmod(this.zeroTimeOffset + bias + (worldTime + partialTicks) / this.periodLength, 1.0);
	}
	
	/**
	 * Calculates time for certain offset after certain time.
	 * @param timeFrom the certain time before the time with the offset
	 * @param offset the offset for this period
	 * @return time in tick for the offset
	 * */
	public long getTimeForOffset(long timeFrom, double offset) {
		return timeFrom + (long)Math.floor(Spmath.fmod(
				(offset - this.zeroTimeOffset)*this.periodLength - Spmath.fmod(timeFrom, this.periodLength),
				this.periodLength));
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof CelestialPeriod) {
			return periodName.equals(((CelestialPeriod) obj).getPeriodName())
					&& this.periodLength == ((CelestialPeriod) obj).getPeriodLength();
		} else return false;
	}
}

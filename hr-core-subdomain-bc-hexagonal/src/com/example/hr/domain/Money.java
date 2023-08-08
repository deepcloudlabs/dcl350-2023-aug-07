package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject // Threat-safe
public final class Money {
	private final FiatCurrency currency;
	private final double value;

	private Money(FiatCurrency currency, double value) {
		this.currency = currency;
		this.value = value;
	}

	public static Money of(FiatCurrency currency,double value) {
		if (value <= 0.0)
			throw new IllegalArgumentException("Value must be posiitve.");
		Objects.requireNonNull(currency);
		return new Money(currency, value);
	}

	public FiatCurrency currency() {
		return currency;
	}

	public double value() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return currency == other.currency && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Money [currency=" + currency + ", value=" + value + "]";
	}

	public Money multiply(double rate) {
		return Money.of(currency, value * rate);
	}

}

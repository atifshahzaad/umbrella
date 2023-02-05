package com.ou.enumtype;

import com.ou.validator.EnumValidatorComparator;

public enum Gender implements EnumValidatorComparator<String> {
	
	Male,
	FeMale;

	@Override
	public boolean test(String other) {
		return this.toString().equalsIgnoreCase(other.trim().toLowerCase());
	}
}

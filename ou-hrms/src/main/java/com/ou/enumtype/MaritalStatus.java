package com.ou.enumtype;

import com.ou.validator.EnumValidatorComparator;

public enum MaritalStatus implements EnumValidatorComparator<String> {
	
	Single,
	Married;

	@Override
	public boolean test(String other) {
		return this.toString().equalsIgnoreCase(other.trim().toLowerCase());
	}
}

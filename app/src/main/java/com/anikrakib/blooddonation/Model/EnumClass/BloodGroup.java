package com.anikrakib.blooddonation.Model.EnumClass;

public enum BloodGroup{

    O_POS("O+"), O_NEG("O-"), A_POS("A+"), A_NEG("A-"), B_POS("B+"), B_NEG("B-"), AB_POS("AB+"), AB_NEG("AB-");

    private final String group;

    BloodGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return this.group;
    }
}

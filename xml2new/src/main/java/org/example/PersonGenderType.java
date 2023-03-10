package org.example;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "person-gender-type",namespace = "org.example")
@XmlEnum
public enum PersonGenderType {

    @XmlEnumValue("male")
    MALE("male"),
    @XmlEnumValue("female")
    FEMALE("female");
    private final String value;

    PersonGenderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PersonGenderType fromValue(String v) {
        for (PersonGenderType c: PersonGenderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
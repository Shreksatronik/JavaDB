package org.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-reference-type",namespace = "org.example")
public class PersonReferenceType {

    @XmlAttribute(name = "person-id", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object personId;


    public Object getPersonId() {
        return personId;
    }


    public void setPersonId(Object value) {
        this.personId = value;
    }

}
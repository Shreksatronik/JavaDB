package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-siblings-type",namespace = "org.example", propOrder = {
        "brotherRef",
        "sisterRef"
})
public class PersonSiblingType {

    @XmlElement(name = "brother-ref")
    protected List<PersonReferenceType> brotherRef;
    @XmlElement(name = "sister-ref")
    protected List<PersonReferenceType> sisterRef;
    @XmlAttribute(name = "count", required = true)
    protected int count;
    public List<PersonReferenceType> getBrotherRef() {
        if (brotherRef == null) {
            brotherRef = new ArrayList<PersonReferenceType>();
        }
        return this.brotherRef;
    }

    public List<PersonReferenceType> getSisterRef() {
        if (sisterRef == null) {
            sisterRef = new ArrayList<PersonReferenceType>();
        }
        return this.sisterRef;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int value) {
        this.count = value;
    }

}
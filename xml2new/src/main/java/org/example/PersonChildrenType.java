package org.example;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person-children-type",namespace = "org.example", propOrder = {
        "daughterRef",
        "sonRef"
})
public class PersonChildrenType {

    @XmlElement(name = "daughter-ref")
    protected List<PersonReferenceType> daughterRef;
    @XmlElement(name = "son-ref")
    protected List<PersonReferenceType> sonRef;
    @XmlAttribute(name = "count", required = true)
    protected int count;

    public List<PersonReferenceType> getDaughterRef() {
        if (daughterRef == null) {
            daughterRef = new ArrayList<PersonReferenceType>();
        }
        return this.daughterRef;
    }


    public List<PersonReferenceType> getSonRef() {
        if (sonRef == null) {
            sonRef = new ArrayList<PersonReferenceType>();
        }
        return this.sonRef;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int value) {
        this.count = value;
    }

}

package org.jibx.schema.org.oasis_open.committees.entity.release._1_0.catalog;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:simpleType xmlns:ns="urn:oasis:names:tc:entity:xmlns:xml:catalog" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="systemOrPublic">
 *   &lt;xs:restriction base="xs:string">
 *     &lt;xs:enumeration value="system"/>
 *     &lt;xs:enumeration value="public"/>
 *   &lt;/xs:restriction>
 * &lt;/xs:simpleType>
 * </pre>
 */
public enum SystemOrPublic {
    SYSTEM("system"), PUBLIC("public");
    private final String value;

    private SystemOrPublic(String value) {
        this.value = value;
    }

    public String xmlValue() {
        return value;
    }

    public static SystemOrPublic convert(String value) {
        for (SystemOrPublic inst : values()) {
            if (inst.xmlValue().equals(value)) {
                return inst;
            }
        }
        return null;
    }
}

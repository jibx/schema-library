
package org.jibx.schema.org.oasis_open.committees.entity.release._1_0.catalog;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="urn:oasis:names:tc:entity:xmlns:xml:catalog" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="uri">
 *   &lt;xs:complexContent>
 *     &lt;xs:restriction base="xs:anyType">
 *       &lt;xs:attribute type="xs:string" use="required" name="name"/>
 *       &lt;xs:attribute type="xs:string" use="required" name="uri"/>
 *       &lt;xs:attribute type="xs:string" name="id"/>
 *       &lt;xs:anyAttribute processContents="lax" namespace="##other"/>
 *     &lt;/xs:restriction>
 *   &lt;/xs:complexContent>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Uri
{
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String uri;
	private String name;
}

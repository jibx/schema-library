
package org.jibx.schema.org.oasis_open.committees.entity.release._1_0.catalog;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:complexType xmlns:ns="urn:oasis:names:tc:entity:xmlns:xml:catalog" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="catalog">
 *   &lt;xs:choice minOccurs="1" maxOccurs="unbounded">
 *     &lt;!-- Reference to inner class Choice -->
 *   &lt;/xs:choice>
 *   &lt;xs:attribute type="xs:string" name="id"/>
 *   &lt;xs:attribute type="ns:systemOrPublic" name="prefer"/>
 *   &lt;xs:anyAttribute processContents="lax" namespace="##other"/>
 * &lt;/xs:complexType>
 * </pre>
 */
public class Catalog
{
    private List<Choice> choiceList = new ArrayList<Choice>();
    private String id;
    private SystemOrPublic prefer;

    /** 
     * Get the list of choice items.
     * 
     * @return list
     */
    public List<Choice> getChoiceList() {
        return choiceList;
    }

    /** 
     * Set the list of choice items.
     * 
     * @param list
     */
    public void setChoiceList(List<Choice> list) {
        choiceList = list;
    }

    /** 
     * Get the 'id' attribute value.
     * 
     * @return value
     */
    public String getId() {
        return id;
    }

    /** 
     * Set the 'id' attribute value.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /** 
     * Get the 'prefer' attribute value.
     * 
     * @return value
     */
    public SystemOrPublic getPrefer() {
        return prefer;
    }

    /** 
     * Set the 'prefer' attribute value.
     * 
     * @param prefer
     */
    public void setPrefer(SystemOrPublic prefer) {
        this.prefer = prefer;
    }
    /** 
     * Schema fragment(s) for this class:
     * <pre>
     * &lt;xs:choice xmlns:ns="urn:oasis:names:tc:entity:xmlns:xml:catalog" xmlns:xs="http://www.w3.org/2001/XMLSchema" minOccurs="1" maxOccurs="unbounded">
     *   &lt;xs:element ref="ns:public"/>
     *   &lt;xs:element ref="ns:system"/>
     *   &lt;xs:element ref="ns:uri"/>
     *   &lt;xs:element ref="ns:rewriteSystem"/>
     *   &lt;xs:element ref="ns:rewriteURI"/>
     *   &lt;xs:element ref="ns:delegatePublic"/>
     *   &lt;xs:element ref="ns:delegateSystem"/>
     *   &lt;xs:element ref="ns:delegateURI"/>
     *   &lt;xs:element ref="ns:nextCatalog"/>
     *   &lt;xs:element ref="ns:group"/>
     *   &lt;xs:any processContents="skip" namespace="##other"/>
     * &lt;/xs:choice>
     * </pre>
     */
    public static class Choice
    {
        private int choiceListSelect = -1;
        private static final int PUBLIC_CHOICE = 0;
        private static final int SYSTEM_CHOICE = 1;
        private static final int URI_CHOICE = 2;
        private static final int REWRITE_SYSTEM_CHOICE = 3;
        private static final int REWRITE_URI_CHOICE = 4;
        private static final int DELEGATE_PUBLIC_CHOICE = 5;
        private static final int DELEGATE_SYSTEM_CHOICE = 6;
        private static final int DELEGATE_URI_CHOICE = 7;
        private static final int NEXT_CATALOG_CHOICE = 8;
        private static final int GROUP_CHOICE = 9;
        private static final int ANY_CHOICE = 10;
        private Public _public;
        private _System system;
        private Uri uri;
        private RewriteSystem rewriteSystem;
        private RewriteURI rewriteURI;
        private DelegatePublic delegatePublic;
        private DelegateSystem delegateSystem;
        private DelegateURI delegateURI;
        private NextCatalog nextCatalog;
        private Group group;
        private Element any;

        private void setChoiceListSelect(int choice) {
            if (choiceListSelect == -1) {
                choiceListSelect = choice;
            } else if (choiceListSelect != choice) {
                throw new IllegalStateException(
                        "Need to call clearChoiceListSelect() before changing existing choice");
            }
        }

        /** 
         * Clear the choice selection.
         */
        public void clearChoiceListSelect() {
            choiceListSelect = -1;
        }

        /** 
         * Check if Public is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifPublic() {
            return choiceListSelect == PUBLIC_CHOICE;
        }

        /** 
         * Get the 'public' element value.
         * 
         * @return value
         */
        public Public getPublic() {
            return _public;
        }

        /** 
         * Set the 'public' element value.
         * 
         * @param _public
         */
        public void setPublic(Public _public) {
            setChoiceListSelect(PUBLIC_CHOICE);
            this._public = _public;
        }

        /** 
         * Check if System is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifSystem() {
            return choiceListSelect == SYSTEM_CHOICE;
        }

        /** 
         * Get the 'system' element value.
         * 
         * @return value
         */
        public _System getSystem() {
            return system;
        }

        /** 
         * Set the 'system' element value.
         * 
         * @param system
         */
        public void setSystem(_System system) {
            setChoiceListSelect(SYSTEM_CHOICE);
            this.system = system;
        }

        /** 
         * Check if Uri is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifUri() {
            return choiceListSelect == URI_CHOICE;
        }

        /** 
         * Get the 'uri' element value.
         * 
         * @return value
         */
        public Uri getUri() {
            return uri;
        }

        /** 
         * Set the 'uri' element value.
         * 
         * @param uri
         */
        public void setUri(Uri uri) {
            setChoiceListSelect(URI_CHOICE);
            this.uri = uri;
        }

        /** 
         * Check if RewriteSystem is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifRewriteSystem() {
            return choiceListSelect == REWRITE_SYSTEM_CHOICE;
        }

        /** 
         * Get the 'rewriteSystem' element value.
         * 
         * @return value
         */
        public RewriteSystem getRewriteSystem() {
            return rewriteSystem;
        }

        /** 
         * Set the 'rewriteSystem' element value.
         * 
         * @param rewriteSystem
         */
        public void setRewriteSystem(RewriteSystem rewriteSystem) {
            setChoiceListSelect(REWRITE_SYSTEM_CHOICE);
            this.rewriteSystem = rewriteSystem;
        }

        /** 
         * Check if RewriteURI is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifRewriteURI() {
            return choiceListSelect == REWRITE_URI_CHOICE;
        }

        /** 
         * Get the 'rewriteURI' element value.
         * 
         * @return value
         */
        public RewriteURI getRewriteURI() {
            return rewriteURI;
        }

        /** 
         * Set the 'rewriteURI' element value.
         * 
         * @param rewriteURI
         */
        public void setRewriteURI(RewriteURI rewriteURI) {
            setChoiceListSelect(REWRITE_URI_CHOICE);
            this.rewriteURI = rewriteURI;
        }

        /** 
         * Check if DelegatePublic is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifDelegatePublic() {
            return choiceListSelect == DELEGATE_PUBLIC_CHOICE;
        }

        /** 
         * Get the 'delegatePublic' element value.
         * 
         * @return value
         */
        public DelegatePublic getDelegatePublic() {
            return delegatePublic;
        }

        /** 
         * Set the 'delegatePublic' element value.
         * 
         * @param delegatePublic
         */
        public void setDelegatePublic(DelegatePublic delegatePublic) {
            setChoiceListSelect(DELEGATE_PUBLIC_CHOICE);
            this.delegatePublic = delegatePublic;
        }

        /** 
         * Check if DelegateSystem is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifDelegateSystem() {
            return choiceListSelect == DELEGATE_SYSTEM_CHOICE;
        }

        /** 
         * Get the 'delegateSystem' element value.
         * 
         * @return value
         */
        public DelegateSystem getDelegateSystem() {
            return delegateSystem;
        }

        /** 
         * Set the 'delegateSystem' element value.
         * 
         * @param delegateSystem
         */
        public void setDelegateSystem(DelegateSystem delegateSystem) {
            setChoiceListSelect(DELEGATE_SYSTEM_CHOICE);
            this.delegateSystem = delegateSystem;
        }

        /** 
         * Check if DelegateURI is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifDelegateURI() {
            return choiceListSelect == DELEGATE_URI_CHOICE;
        }

        /** 
         * Get the 'delegateURI' element value.
         * 
         * @return value
         */
        public DelegateURI getDelegateURI() {
            return delegateURI;
        }

        /** 
         * Set the 'delegateURI' element value.
         * 
         * @param delegateURI
         */
        public void setDelegateURI(DelegateURI delegateURI) {
            setChoiceListSelect(DELEGATE_URI_CHOICE);
            this.delegateURI = delegateURI;
        }

        /** 
         * Check if NextCatalog is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifNextCatalog() {
            return choiceListSelect == NEXT_CATALOG_CHOICE;
        }

        /** 
         * Get the 'nextCatalog' element value.
         * 
         * @return value
         */
        public NextCatalog getNextCatalog() {
            return nextCatalog;
        }

        /** 
         * Set the 'nextCatalog' element value.
         * 
         * @param nextCatalog
         */
        public void setNextCatalog(NextCatalog nextCatalog) {
            setChoiceListSelect(NEXT_CATALOG_CHOICE);
            this.nextCatalog = nextCatalog;
        }

        /** 
         * Check if Group is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifGroup() {
            return choiceListSelect == GROUP_CHOICE;
        }

        /** 
         * Get the 'group' element value.
         * 
         * @return value
         */
        public Group getGroup() {
            return group;
        }

        /** 
         * Set the 'group' element value.
         * 
         * @param group
         */
        public void setGroup(Group group) {
            setChoiceListSelect(GROUP_CHOICE);
            this.group = group;
        }

        /** 
         * Check if Any is current selection for choice.
         * 
         * @return <code>true</code> if selection, <code>false</code> if not
         */
        public boolean ifAny() {
            return choiceListSelect == ANY_CHOICE;
        }

        /** 
         * Get the any value.
         * 
         * @return value
         */
        public Element getAny() {
            return any;
        }

        /** 
         * Set the any value.
         * 
         * @param any
         */
        public void setAny(Element any) {
            setChoiceListSelect(ANY_CHOICE);
            this.any = any;
        }
    }
}

package uk.co.boots.columbus.cmdb.model.domain;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * The first time the {@link #hash(Logger, Identifiable)} is called, we check if the primary key is present or not.
 * <ul>
 * <li>If yes: we use it to get the hash</li>
 * <li>If no: we use a VMID during the entire life of this instance even if later on this instance is assigned a primary key.</li>
 * </ul>
 */
public class IdentifiableHashBuilder implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object technicalId;

    public int hash(Logger log, Identifiable<?> identifiable, String compositeBusinessKey) {
        if (technicalId == null) {
            if (identifiable.isIdSet()) {
                technicalId = identifiable.getId();
            } else if (compositeBusinessKey != null) {
            	technicalId = compositeBusinessKey;
            } else {
                // NOT SAFE! You haven't got anyway to identify the object
            	// this section should never be called really.
            	technicalId = new java.rmi.dgc.VMID();
                log.warning("DEVELOPER: hashCode is not safe." //
                        + "If you encounter this message you should take the time to carefuly " //
                        + "review the equals/hashCode methods for: " + identifiable.getClass().getCanonicalName() //
                        + " You may consider using a business key.");
            }
        }
        return technicalId.hashCode();
    }
}

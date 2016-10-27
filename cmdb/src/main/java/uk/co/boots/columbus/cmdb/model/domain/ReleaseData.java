/*
 * Source code generated by Celerio, a Jaxio product.
 * Documentation: http://www.jaxio.com/documentation/celerio/
 * Follow us on twitter: @jaxiosoft
 * Need commercial support ? Contact us: info@jaxio.com
 * Template pack-angular:src/main/java/domain/Entity.java.e.vm
 */
package uk.co.boots.columbus.cmdb.model.domain;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.google.common.base.MoreObjects;

@Entity
@Table(name = "cm_releasedata")
public class ReleaseData implements Identifiable<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(ReleaseData.class.getName());

    // Raw attributes
    private String param;
    private String value;
    private Long id;

    // Many to one
    private Release release;
    private ReleaseDataType dataType;

    @Override
    public String entityClassName() {
        return ReleaseData.class.getSimpleName();
    }

    // -- [param] ------------------------

    @Size(max = 50)
    @Column(name = "ReleaseParam", length = 50)
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public ReleaseData param(String param) {
        setParam(param);
        return this;
    }
    // -- [value] ------------------------

    @Size(max = 50)
    @Column(name = "ReleaseValue", length = 50)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ReleaseData value(String value) {
        setValue(value);
        return this;
    }
    // -- [id] ------------------------

    @Override
    @Column(name = "ReleaseDataID", precision = 19)
    @GeneratedValue
    @Id
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ReleaseData id(Long id) {
        setId(id);
        return this;
    }

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }

    // -----------------------------------------------------------------
    // Many to One support
    // -----------------------------------------------------------------

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: ReleaseData.release ==> Release.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "ReleaseID")
    @ManyToOne
    public Release getRelease() {
        return release;
    }

    /**
     * Set the {@link #release} without adding this ReleaseData instance on the passed {@link #release}
     */
    public void setRelease(Release release) {
        this.release = release;
    }

    public ReleaseData release(Release release) {
        setRelease(release);
        return this;
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    // many-to-one: ReleaseData.dataType ==> ReleaseDataType.id
    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

    @JoinColumn(name = "DataTypeID")
    @ManyToOne
    public ReleaseDataType getDataType() {
        return dataType;
    }

    /**
     * Set the {@link #dataType} without adding this ReleaseData instance on the passed {@link #dataType}
     */
    public void setDataType(ReleaseDataType dataType) {
        this.dataType = dataType;
    }

    public ReleaseData dataType(ReleaseDataType dataType) {
        setDataType(dataType);
        return this;
    }

    /**
     * Apply the default values.
     */
    public ReleaseData withDefaults() {
        return this;
    }

    /**
     * Equals implementation using a business key.
     */
    @Override
    public boolean equals(Object other) {
        return this == other || (other instanceof ReleaseData && hashCode() == other.hashCode());
    }

    private IdentifiableHashBuilder identifiableHashBuilder = new IdentifiableHashBuilder();

    @Override
    public int hashCode() {
        return identifiableHashBuilder.hash(log, this);
    }

    /**
     * Construct a readable string representation for this ReleaseData instance.
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this) //
                .add("param", getParam()) //
                .add("value", getValue()) //
                .add("id", getId()) //
                .toString();
    }
}
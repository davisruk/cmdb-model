package uk.co.boots.columbus.cmdb.model.core.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import uk.co.boots.columbus.cmdb.model.environment.domain.EnvironmentType;

@MappedSuperclass
public abstract class BaseEntity implements LockableEntity, Identifiable<Long>{

	public abstract String entityClassName();
	
	@Column(precision = 19)
    @GeneratedValue
    @Id
    protected Long id;

	@Version
	protected Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public Long incrementVersion(){
		if (version == null)
			version = new Long(0L);
		else 
			version++;
		return version;
	}

    @Override
    @Transient
    public boolean isIdSet() {
        return id != null;
    }
}

package uk.co.boots.columbus.cmdb.model.hiera.dto;

import java.util.List;

import javax.inject.Inject;

import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTO;
import uk.co.boots.columbus.cmdb.model.globalconfig.dto.GlobalconfigDTOService;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTO;
import uk.co.boots.columbus.cmdb.model.release.dto.ReleaseConfigDTOService;

public class ConfigContainer {

	private List<GlobalconfigDTO> gcNonRepeat;
	private List<GlobalconfigDTO> gcEnvRepeat;
	private List<GlobalconfigDTO> gcSubEnvRepeat;
	private List<GlobalconfigDTO> gcReleaseRepeat;
	private List<ReleaseConfigDTO> relNonRepeat;
	private List<ReleaseConfigDTO> relEnvRepeat;
	private List<ReleaseConfigDTO> relSubEnvRepeat;

	private GlobalconfigDTOService gcService;
	
	private ReleaseConfigDTOService relService;
	
	public ConfigContainer(GlobalconfigDTOService gcService, ReleaseConfigDTOService relService) {
		super();
		this.relService = relService;
		this.gcService = gcService;
		this.gcNonRepeat = this.gcService.findRepeatingGlobalConfigs(false, false, false);
		this.gcEnvRepeat = this.gcService.findRepeatingGlobalConfigs(true, false, false);
		this.gcSubEnvRepeat = this.gcService.findRepeatingGlobalConfigs(false, true, false);
		this.gcReleaseRepeat = this.gcService.findRepeatingGlobalConfigs(false, false, true);
		this.relEnvRepeat = null;
		this.relSubEnvRepeat = null;
		this.relNonRepeat = null;
	}
	
	public List<GlobalconfigDTO> getGcNonRepeat() {
		return gcNonRepeat;
	}
	public void setGcNonRepeat(List<GlobalconfigDTO> gcNonRepeat) {
		this.gcNonRepeat = gcNonRepeat;
	}
	public List<GlobalconfigDTO> getGcEnvRepeat() {
		return gcEnvRepeat;
	}
	public void setGcEnvRepeat(List<GlobalconfigDTO> gcEnvRepeat) {
		this.gcEnvRepeat = gcEnvRepeat;
	}
	public List<GlobalconfigDTO> getGcSubEnvRepeat() {
		return gcSubEnvRepeat;
	}
	public void setGcSubEnvRepeat(List<GlobalconfigDTO> gcSubEnvRepeat) {
		this.gcSubEnvRepeat = gcSubEnvRepeat;
	}
	public List<GlobalconfigDTO> getGcReleaseRepeat() {
		return gcReleaseRepeat;
	}
	public void setGcReleaseRepeat(List<GlobalconfigDTO> gcReleaseRepeat) {
		this.gcReleaseRepeat = gcReleaseRepeat;
	}
	public List<ReleaseConfigDTO> getRelEnvRepeat() {
		return relEnvRepeat;
	}
	public void setRelEnvRepeat(List<ReleaseConfigDTO> relEnvRepeat) {
		this.relEnvRepeat = relEnvRepeat;
	}
	public List<ReleaseConfigDTO> getRelSubEnvRepeat() {
		return relSubEnvRepeat;
	}
	public void setRelSubEnvRepeat(List<ReleaseConfigDTO> relSubEnvRepeat) {
		this.relSubEnvRepeat = relSubEnvRepeat;
	}

	public List<ReleaseConfigDTO> getRelNonRepeat() {
		return relNonRepeat;
	}

	public void setRelNonRepeat(List<ReleaseConfigDTO> relNonRepeat) {
		this.relNonRepeat = relNonRepeat;
	}
	
	
}

package com.qx.eakay.model.asset;



/**
 * 资产领用详细
 * 
 * @author Administrator
 *
 */
public class AssetUseDetailPO {
	private Integer id;
	private Integer useId;
	private Integer assetsId;
	
	
	public AssetUseDetailPO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUseId() {
		return useId;
	}

	public void setUseId(Integer useId) {
		this.useId = useId;
	}

	public Integer getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Integer assetsId) {
		this.assetsId = assetsId;
	}
	
	
}

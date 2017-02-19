package lx.com.filter.vo;

import java.io.Serializable;
import java.util.List;

public class SaleAttributeNameVo implements Serializable{

	private String name;
	private String nameId;
	private List<SaleAttributeVo> saleVo;
	private String saleAttr;
	private boolean nameIsChecked;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public List<SaleAttributeVo> getSaleVo() {
		return saleVo;
	}

	public void setSaleVo(List<SaleAttributeVo> saleVo) {
		this.saleVo = saleVo;
	}

	public String getSaleAttr() {
		return saleAttr;
	}

	public void setSaleAttr(String saleAttr) {
		this.saleAttr = saleAttr;
	}

	public boolean isNameIsChecked() {
		return nameIsChecked;
	}

	public void setNameIsChecked(boolean nameIsChecked) {
		this.nameIsChecked = nameIsChecked;
	}

}

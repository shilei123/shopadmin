package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtKeyValue entity. @author MyEclipse Persistence Tools
 */

public class TXtKeyValue implements java.io.Serializable {

	// Fields

	private String kvKey;
	private String kvValue;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;

	// Constructors

	/** default constructor */
	public TXtKeyValue() {
	}

	/** minimal constructor */
	public TXtKeyValue(String kvKey, Timestamp lrSj) {
		this.kvKey = kvKey;
		this.lrSj = lrSj;
	}

	/** full constructor */
	public TXtKeyValue(String kvKey, String kvValue, String yxBj,
			Timestamp lrSj, Timestamp xgSj) {
		this.kvKey = kvKey;
		this.kvValue = kvValue;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.xgSj = xgSj;
	}

	// Property accessors

	public String getKvKey() {
		return this.kvKey;
	}

	public void setKvKey(String kvKey) {
		this.kvKey = kvKey;
	}

	public String getKvValue() {
		return this.kvValue;
	}

	public void setKvValue(String kvValue) {
		this.kvValue = kvValue;
	}

	public String getYxBj() {
		return this.yxBj;
	}

	public void setYxBj(String yxBj) {
		this.yxBj = yxBj;
	}

	public Timestamp getLrSj() {
		return this.lrSj;
	}

	public void setLrSj(Timestamp lrSj) {
		this.lrSj = lrSj;
	}

	public Timestamp getXgSj() {
		return this.xgSj;
	}

	public void setXgSj(Timestamp xgSj) {
		this.xgSj = xgSj;
	}

}
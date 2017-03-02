package framework.db.pojo;

import java.sql.Timestamp;

/**
 * TXtHelp entity. @author MyEclipse Persistence Tools
 */

public class TXtHelp implements java.io.Serializable {

	// Fields

	private TXtHelpId id;
	private String url;
	private String yxBj;
	private Timestamp lrSj;
	private Timestamp xgSj;
	private String content;

	// Constructors

	/** default constructor */
	public TXtHelp() {
	}

	/** minimal constructor */
	public TXtHelp(TXtHelpId id, Timestamp lrSj) {
		this.id = id;
		this.lrSj = lrSj;
	}

	/** full constructor */
	public TXtHelp(TXtHelpId id, String url, String yxBj, Timestamp lrSj,
			Timestamp xgSj,String content) {
		this.id = id;
		this.url = url;
		this.yxBj = yxBj;
		this.lrSj = lrSj;
		this.xgSj = xgSj;
		this.content = content;
	}

	// Property accessors

	public TXtHelpId getId() {
		return this.id;
	}

	public void setId(TXtHelpId id) {
		this.id = id;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
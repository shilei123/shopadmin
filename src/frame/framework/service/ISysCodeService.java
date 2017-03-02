package framework.service;

import java.util.List;

import framework.db.pojo.TXtSysCode;

public interface ISysCodeService {
	public List<TXtSysCode> querySysCodeByType(String type) throws Exception;
}
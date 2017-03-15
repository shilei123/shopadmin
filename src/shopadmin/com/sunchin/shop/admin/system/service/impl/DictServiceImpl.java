package com.sunchin.shop.admin.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.system.service.DictService;
import com.sunchin.shop.admin.dict.FlagEnum;
import com.sunchin.shop.admin.dict.IsEditEnum;
import com.sunchin.shop.admin.dict.IsUseEnum;
import com.sunchin.shop.admin.pojo.ScDictionary;
import com.sunchin.shop.admin.system.dao.ScDictionaryDAO;

import framework.bean.PageBean;
import framework.db.DBUtil;

@Service("dictService")
public class DictServiceImpl implements DictService {
	@Resource(name = "scDictionaryDAO")
	private ScDictionaryDAO dictDAO;
	
	@Override
	public PageBean queryDictList(PageBean pageBean) throws Exception {
		int total = dictDAO.queryDictCount(pageBean);
		pageBean.setTotal(total);
		List<ScDictionary> pageData = dictDAO.queryDictPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

	/**
	 * 查询单条记录
	 */
	public ScDictionary getDict(String id) {
		Object obj = DBUtil.getInstance().get(ScDictionary.class, id);
		if(obj != null) {
			return (ScDictionary) obj;
		}
		return null;
	}
	
	/**
	 * 保存
	 */
	public void saveDict(ScDictionary dict) {
		if (dict == null) {
			return;
		}
		DBUtil db = DBUtil.getInstance();
		// 新增
		if (StringUtils.isBlank(dict.getId())) {
			String id = UUID.randomUUID().toString();
			dict.setId(id);
			dict.setCreateTime(new Date());
			dict.setFlag(FlagEnum.ACT.getCode());
			dict.setIsuse(IsUseEnum.VALID.getCode());
			dict.setIsedit(IsEditEnum.EDIT.getCode());
			db.insert(dict);
		} else { // 修改
			ScDictionary vo = (ScDictionary) db.get(ScDictionary.class, dict.getId());
			vo.setType(dict.getType());
			vo.setCode(dict.getCode());
			vo.setIsedit(dict.getIsedit());
			vo.setIsuse(dict.getIsuse());
			vo.setName(dict.getName());
			vo.setEname(dict.getEname());
			vo.setRemark(dict.getRemark());
			vo.setSort(dict.getSort());
			db.update(vo);
		}
	}

	@Override
	public void deleteDict(String id) throws Exception {
		DBUtil db = DBUtil.getInstance();
		ScDictionary dict = getDict(id);
		if(dict != null) {
			dict.setFlag(FlagEnum.HIS.getCode());
			db.update(dict);
		}
	}

	@Override
	public List<ScDictionary> getDictByType(String type) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

}
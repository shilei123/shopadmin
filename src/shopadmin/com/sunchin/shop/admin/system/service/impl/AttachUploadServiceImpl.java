package com.sunchin.shop.admin.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.pojo.ScAttachments;
import com.sunchin.shop.admin.system.dao.ScAttachmentsDAO;
import com.sunchin.shop.admin.system.service.AttachUploadService;

import framework.bean.PageBean;

@Service("attachUploadService")
public class AttachUploadServiceImpl implements AttachUploadService {
	@Resource(name = "scAttachmentsDAO")
	private ScAttachmentsDAO attachmentsDAO;
	
	@Override
	public PageBean queryAttachs(PageBean pageBean) {
		int total = attachmentsDAO.queryAttachsCount(pageBean);
		pageBean.setTotal(total);
		List<ScAttachments> pageData = attachmentsDAO.queryAttachsPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

}
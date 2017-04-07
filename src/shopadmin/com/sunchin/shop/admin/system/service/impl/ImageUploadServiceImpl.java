package com.sunchin.shop.admin.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.pojo.ScImage;

import com.sunchin.shop.admin.system.dao.ScImageDAO;
import com.sunchin.shop.admin.system.service.ImageUploadService;

import framework.bean.PageBean;

@Service("imageUploadService")
public class ImageUploadServiceImpl implements ImageUploadService {
	@Resource(name = "scImageDAO")
	private ScImageDAO imageDAO;
	
	@Override
	public PageBean queryImage(PageBean pageBean) {
		int total = imageDAO.queryImagesCount(pageBean);
		pageBean.setTotal(total);
		List<ScImage> pageData = imageDAO.queryImagesPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

}
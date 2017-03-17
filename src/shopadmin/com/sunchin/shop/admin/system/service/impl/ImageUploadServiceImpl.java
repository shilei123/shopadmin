package com.sunchin.shop.admin.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sunchin.shop.admin.pojo.ScImages;

import com.sunchin.shop.admin.system.dao.ScImagesDAO;
import com.sunchin.shop.admin.system.service.ImageUploadService;

import framework.bean.PageBean;

@Service("imageUploadService")
public class ImageUploadServiceImpl implements ImageUploadService {
	@Resource(name = "scImagesDAO")
	private ScImagesDAO imagesDAO;
	
	@Override
	public PageBean queryImages(PageBean pageBean) {
		int total = imagesDAO.queryImagesCount(pageBean);
		pageBean.setTotal(total);
		List<ScImages> pageData = imagesDAO.queryImagesPagination(pageBean);
		pageBean.setPageData(pageData);
		return pageBean;
	}

}
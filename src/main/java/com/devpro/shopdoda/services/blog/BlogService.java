package com.devpro.shopdoda.services.blog;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shopdoda.dto.search.BlogSearch;
import com.devpro.shopdoda.entities.blog.Blog;
import com.devpro.shopdoda.repositories.blog.BlogRepo;
import com.devpro.shopdoda.taglibs.PaginationTaglib;
import com.devpro.shopdoda.utils.Constants;
import com.devpro.shopdoda.utils.Utilities;

@Service
public class BlogService implements Constants {

	@Autowired
	private BlogRepo blogRepo;

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Blog> search(BlogSearch blogSearch) {
		String jpql = "SELECT p FROM Blog p where p.status = true";

		if (!StringUtils.isEmpty(blogSearch.getSeo())) {
			jpql = jpql + " AND p.seo = '" + blogSearch.getSeo() + "'";
		}

		if (!StringUtils.isEmpty(blogSearch.getBlogTypeSeo())) {
			jpql = jpql + " AND p.blogType.seo = '" + blogSearch.getBlogTypeSeo() + "'";
		}
		if (!StringUtils.isEmpty(blogSearch.getSearchText())) {
			String st = "'%" + blogSearch.getSearchText() + "%'";
			jpql = jpql + " AND (p.title LIKE " + st + " OR p.detailDescription LIKE " + st + " ) ";
		}
		jpql = jpql + " ORDER BY p.createdDate DESC";

		Query query = entityManager.createQuery(jpql, Blog.class);

		// paging
		if (blogSearch.getOffset() != null) {
			blogSearch.setCount(query.getResultList().size());

			query.setFirstResult(blogSearch.getOffset());
			query.setMaxResults(PaginationTaglib.MAX);
		}
		return query.getResultList();
	}
	

	private boolean isEmptyUploadFile(MultipartFile images) {
		return images == null || images.getOriginalFilename().isEmpty();
	}

	@Transactional(rollbackOn = Exception.class)
	public void saveOrUpdate(Blog blog, MultipartFile avatar) throws Exception {
		try {
			// TH chỉnh sửa
			if (blog.getId() != null && blog.getId() > 0) {
				Blog blogInDB = blogRepo.findById(blog.getId()).get();
				// nếu upload lại avatar thì phải xóa avatar cũ đi
				if (!isEmptyUploadFile(avatar)) {
					String oldAvatarPath = blogInDB.getAvatar();
					if (!StringUtils.isEmpty(oldAvatarPath)) {
						new File(ROOT_UPLOAD_PATH + oldAvatarPath).delete();
					}
					String avatarPath = "blog/avatar/" + avatar.getOriginalFilename();
					blog.setAvatar(avatarPath);
					avatar.transferTo(new File(ROOT_UPLOAD_PATH + avatarPath));
				} else {
					blog.setAvatar(blogInDB.getAvatar());
				}
				blog.setViews(blogInDB.getViews());
				blog.setCreatedDate(blogInDB.getCreatedDate());
				blog.setUpdatedDate(new Date());
			}
			// TH tạo mới
			else {
				// nếu upload avatar
				if (!isEmptyUploadFile(avatar)) {
					String avatarPath = "blog/avatar/" + avatar.getOriginalFilename();
					blog.setAvatar(avatarPath);
					avatar.transferTo(new File(ROOT_UPLOAD_PATH + avatarPath));
				}
				blog.setCreatedDate(new Date());
			}

			blog.setSeo(Utilities.seo(blog.getTitle() + "-" + System.currentTimeMillis()));
			blogRepo.save(blog);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}

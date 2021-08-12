package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.User;
import com.entity.Video;
import com.utils.HibernateUtils;

public class VideoDAO {
private Session hSession;
	
	public VideoDAO() {
		this.hSession = HibernateUtils.getSession();
	}
	
	public List<Video> paginate(int offset, int limit) {
		String hql = "FROM Video";
		Query query = this.hSession.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);
		List<Video> listVideo= query.getResultList();

		return listVideo;
	}
	
	public Video idVideo() {
		String hql = "FROM Video";
		Query query = this.hSession.createQuery(hql);
		Video entity = (Video) query.getSingleResult();
		return entity;
	}
	
	public List<Video> showHome(int offset, int limit) {
		String hql = "FROM Video WHERE active=0";
		Query query = this.hSession.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);
		List<Video> listVideo= query.getResultList();

		return listVideo;
	}
	
	public Video insert(Video entity) {
		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity);
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}
	
	//Tìm user theo id
		public Video findById(int id) {
			Video entity = this.hSession.get(Video.class, id);

			return entity;
		}
		
		//update
		public void update(Video entity) {
			try {
				this.hSession.clear();
				this.hSession.beginTransaction();

				this.hSession.update(entity);

				this.hSession.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();

				this.hSession.getTransaction().rollback();
			}
		}
		
		public void delete(Video entity) {
			try {
				this.hSession.clear();

				this.hSession.beginTransaction();

				this.hSession.delete(entity);

				this.hSession.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();

				this.hSession.getTransaction().rollback();
			}
		}
}

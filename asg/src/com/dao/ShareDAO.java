package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.Favorite;
import com.entity.Share;
import com.entity.Video;
import com.utils.HibernateUtils;

public class ShareDAO {
	private Session hSession;

	public ShareDAO() {
		this.hSession = HibernateUtils.getSession();
	}
	
	
	public Video findById(int id) {
		Video entity = this.hSession.get(Video.class, id);

		return entity;
	}
	
	public Share insert(Share entity) {
		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity);
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}
	
	public List<Share> ShowShare(int userId,int offset, int limit) {

		String hql = "SELECT objVideo FROM Share objSh, Video objVideo WHERE objSh.videoId=objVideo.id AND objSh.userId = :userId";
		Query query = this.hSession.createQuery(hql);
		query.setParameter("userId", userId);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);
		List<Share> listSh = query.getResultList();
		return listSh;

	}
	
	public void UnShare(int vidID, int userID) {
		try {
			this.hSession.clear();
			this.hSession.beginTransaction();
			String hql = "SELECT objFSh FROM Share objFSh WHERE videoId = :videoid AND userId = :userid";
			
			Query query = this.hSession.createQuery(hql);
			query.setParameter("videoid", vidID);
			query.setParameter("userid", userID);
			
			List<Share> list = query.getResultList();
			this.hSession.delete(list.get(0));

			this.hSession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//huy ket qua neu bi loi
			this.hSession.getTransaction().rollback();
		}
	}
}

package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.Favorite;
import com.entity.User;
import com.entity.Video;
import com.utils.HibernateUtils;

public class FavoriteDAO {
	private Session hSession;

	public FavoriteDAO() {
		this.hSession = HibernateUtils.getSession();
	}


	public Favorite insert(Favorite entity) {
		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity);
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}

	public List<Video> ShowFavorite(int userId ,int offset, int limit) {

		String hql = "SELECT objVideo FROM Favorite objFr, Video objVideo WHERE objFr.videoId=objVideo.id AND objFr.userId = :userId";
		Query query = this.hSession.createQuery(hql);
		query.setParameter("userId", userId);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);
		List<Video> listFr = query.getResultList();
		return listFr;

	}

//	public List<Object[]> test(int userId) {
//
//		String hql = "SELECT objFr.id, objVideo FROM Favorite objFr, Video objVideo WHERE objFr.videoId=objVideo.id AND objFr.userId = :userId";
//		Query query = this.hSession.createQuery(hql);
//		query.setParameter("userId", userId);
//		List<Object[]> list = query.getResultList();
//		return list;
//
//	}
	
	public void UnLike(int vidID, int userID) {
		try {
			this.hSession.clear();
			this.hSession.beginTransaction();
			String hql = "SELECT objFvr FROM Favorite objFvr WHERE videoId = :videoid AND userId = :userid";
			
			Query query = this.hSession.createQuery(hql);
			query.setParameter("videoid", vidID);
			query.setParameter("userid", userID);
			
			List<Favorite> list = query.getResultList();
			this.hSession.delete(list.get(0));

			this.hSession.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			//huy ket qua neu bi loi
			this.hSession.getTransaction().rollback();
		}
	}
	
	

}

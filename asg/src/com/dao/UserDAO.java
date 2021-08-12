package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.entity.User;
import com.utils.HibernateUtils;

public class UserDAO {
	private Session hSession;

	public UserDAO() {
		this.hSession = HibernateUtils.getSession();
	}

	public List<User> paginate(int offset, int limit) {
		String hql = "FROM User";
		Query query = this.hSession.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(offset + limit);

		List<User> listUser = query.getResultList();

		return listUser;
	}

	// thêm mới user
	public User insert(User entity) {
		this.hSession.beginTransaction();
		Integer id = (Integer) this.hSession.save(entity);
		this.hSession.getTransaction().commit();

		entity.setId(id);
		return entity;
	}

	// Tìm user theo id
	public User findById(int id) {
		User entity = this.hSession.get(User.class, id);

		return entity;
	}

	// update
	public void update(User entity) {
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

	public void delete(User entity) {
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

	public User login(String user, String password) {

		try {
			String hql = "SELECT objUser FROM User objUser WHERE fullName= :fullName AND password = :password";
			Query query = this.hSession.createQuery(hql);
			query.setParameter("fullName", user);
			query.setParameter("password", password);
			User entity = (User) query.getSingleResult();
			if (entity != null) {
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public User forgot(String user, String email) {

		try {
			String hql = "SELECT objUser FROM User objUser WHERE fullName= :fullName AND email = :email";
			Query query = this.hSession.createQuery(hql);
			query.setParameter("fullName", user);
			query.setParameter("email", email);
			User entity = (User) query.getSingleResult();
			if (entity != null) {
				return entity;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

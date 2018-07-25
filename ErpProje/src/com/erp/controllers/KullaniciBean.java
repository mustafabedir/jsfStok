package com.erp.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.erp.entities.Kullanici;
import com.mergeCons.egitim.util.EntityUtil;

@ManagedBean
@SessionScoped
public class KullaniciBean {
private Kullanici kullanici = new Kullanici();
	
	public void kaydet()
	{
		EntityManager em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(kullanici);
		em.getTransaction().commit();
		kullanici = new Kullanici();
	}
	
	public void sil() {
		EntityManager em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(kullanici);
		em.getTransaction().commit();
		kullanici = new Kullanici();
	}

	public void duzenle() {
		EntityManager em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(kullanici);
		em.getTransaction().commit();
		kullanici = new Kullanici();
	}
	
	@SuppressWarnings("unchecked")
	public List<Kullanici> getKullaniciListesi() {
		EntityManager em = EntityUtil.getEntityManager();
		String queryString = "FROM Kullanici";
			Query query = em.createQuery(queryString);
		return query.getResultList();
		
	}
	public String kontrol() {
		EntityManager em = EntityUtil.getEntityManager();
	    String queryString = "SELECT a FROM Kullanici a " +
	                         "WHERE a.kullaniciAd=:ad and a.kullaniciSifre=:sifre";
	    Query query = em.createQuery(queryString);
	    query.setParameter("ad", kullanici.getKullaniciAd());
	    query.setParameter("sifre", kullanici.getKullaniciSifre());
	    
	    //return query.getResultList();
	    if(query.getResultList().isEmpty())
	    {
	    	return "Hata";
	    }
	    else
	    	return "Admin";
	}
	
		
	public Kullanici getKullanici() {
		return kullanici;
	}
	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}
	
	}
	
	
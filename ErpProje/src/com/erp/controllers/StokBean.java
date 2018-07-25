package com.erp.controllers;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.erp.entities.Stok;
import com.mergeCons.egitim.util.EntityUtil;

@ManagedBean
@SessionScoped
public class StokBean {
	private Stok stok = new Stok();

	public void kaydet() {
		hesapla();
		EntityManager em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(stok);
		em.getTransaction().commit();
		stok = new Stok();
	}

	public void sil() {
		EntityManager em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(stok);
		em.getTransaction().commit();
		stok = new Stok();
	}

	public void duzenle() {
		hesapla();
		EntityManager em = EntityUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(stok);
		em.getTransaction().commit();
		stok = new Stok();
	}

	public void hesapla() {
		stok.setToplamUcret(stok.getUrunFiyat(), stok.getStokAdet());
	}

	@SuppressWarnings("unchecked")
	public List<Stok> getStokListesi() {
		EntityManager em = EntityUtil.getEntityManager();
		String queryString = "FROM Stok";
		Query query = em.createQuery(queryString);
		return query.getResultList();

	}

	public Integer getBilgisayarListesi() {
		EntityManager em = EntityUtil.getEntityManager();
		String queryString = "SELECT a.urunStok FROM Stok a WHERE aurunAd=:ad";

		Query query = em.createQuery(queryString);
		query.setParameter("ad", stok.getUrunAd());
		Integer sonuc = (Integer) query.getSingleResult();
		return sonuc;

	}

	public List<Stok> UrunAdSırala() {
		EntityManager em = EntityUtil.getEntityManager();
		String queryString = "SELECT a FROM Stok a "
		+ "WHERE a.urunad=:ad";
		Query query = em.createQuery(queryString);
		query.setParameter("ad", stok.getUrunAd());
		return query.getResultList();
		
	}

	public Stok getStok() {
		return stok;
	}

	public void setStok(Stok stok) {
		this.stok = stok;
	}

}

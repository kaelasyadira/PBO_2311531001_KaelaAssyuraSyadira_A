package model;

public class Order {
	String id, id_customer, id_service, id_user, tanggal_order, tanggal_selesai, total, status_bayar, status_order;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_customer() {
		return id_customer;
	}

	public void setId_customer(String id_customer) {
		this.id_customer = id_customer;
	}

	public String getId_service() {
		return id_service;
	}

	public void setId_service(String id_service) {
		this.id_service = id_service;
	}

	public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public String getTanggal_order() {
		return tanggal_order;
	}

	public void setTanggal_order(String tanggal_order) {
		this.tanggal_order = tanggal_order;
	}

	public String getTanggal_selesai() {
		return tanggal_selesai;
	}

	public void setTanggal_selesai(String tanggal_selesai) {
		this.tanggal_selesai = tanggal_selesai;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getStatus_bayar() {
		return status_bayar;
		// paid atau unpaid
	}

	public void setStatus_bayar(String status_bayar) {
		this.status_bayar = status_bayar;
	}

	public String getStatus_order() {
		return status_order;
		// proses atau selesai
	}

	public void setStatus_order(String status_order) {
		this.status_order = status_order;
	}
	
}

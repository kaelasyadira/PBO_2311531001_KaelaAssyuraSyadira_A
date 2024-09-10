package model;

public class Service {
	String id, jenis, harga, satuan, status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJenis() {
		return jenis;
	}

	public void setJenis(String jenis) {
		this.jenis = jenis;
	}

	public String getHarga() {
		return harga;
		// kg atau helaian
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getSatuan() {
		return satuan;
		// kg atau helaian
	}

	public void setSatuan(String satuan) {
		this.satuan = satuan;
	}

	public String getStatus() {
		return status;
		// aktif atau nonaktif
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}

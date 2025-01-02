package DAO;

import java.util.List;

import model.OrderDetail;

public interface OrderDetailDao {
	void save(OrderDetail od);
	public List<OrderDetail> show(String order_id);
	public void delete(String id);
	public void update(OrderDetail od);
	public String total(String order_id);

}
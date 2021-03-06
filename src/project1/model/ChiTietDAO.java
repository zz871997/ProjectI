package project1.model;

import java.util.ArrayList;

public interface ChiTietDAO {
	public ArrayList<ChiTiet> getAllChiTietWithID(String maMT);
	public ArrayList<ChiTiet> getAllChiTiet();
	
	public ChiTiet getChiTiet(String maMT, String maXeMuon);
	
	public void insertChiTiet(ChiTiet chiTiet);
	
	public void updateChiTiet(ChiTiet chiTiet, String ngayTraMoi, int tienPhatMoi, int khuyenMaiMoi);
	
	public void deleteChiTiet(String maMT);
	
	public double tinhTongPhat(String maMT);
	
	public double tinhTongKhuyenMai(String maMT);
}

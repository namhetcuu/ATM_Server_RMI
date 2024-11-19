package Services;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChiTietGiaoDich implements Serializable{
	public long magiaodich;
    public Timestamp ngaygiaodich;
    public long sotiengiaodich;
    public String ghichu;
    public long sotaikhoan;

    public ChiTietGiaoDich() {
    }

    public ChiTietGiaoDich(long magiaodich, Timestamp ngaygiaodich, long sotiengiaodich, String ghichu, long sotaikhoan) {
        this.magiaodich = magiaodich;
        this.ngaygiaodich = ngaygiaodich;
        this.sotiengiaodich = sotiengiaodich;
        this.ghichu = ghichu;
        this.sotaikhoan = sotaikhoan;
    }
}

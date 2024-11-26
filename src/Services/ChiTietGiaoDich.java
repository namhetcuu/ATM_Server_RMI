package Services;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChiTietGiaoDich implements Serializable{
	
	private static final long serialVersionUID = 1L;
	public long magiaodich;
    public Timestamp ngaygiaodich;
    public long sotiengiaodich;
    public String ghichu;
    public long sotaikhoannguoinhan;

    public ChiTietGiaoDich() {
    }

    public ChiTietGiaoDich(long magiaodich, Timestamp ngaygiaodich, long sotiengiaodich, String ghichu, long sotaikhoannguoinhan) {
        this.magiaodich = magiaodich;
        this.ngaygiaodich = ngaygiaodich;
        this.sotiengiaodich = sotiengiaodich;
        this.ghichu = ghichu;
        this.sotaikhoannguoinhan = sotaikhoannguoinhan;
    }
}

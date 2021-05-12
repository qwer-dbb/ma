package cn.smbms.service.bill;

import java.util.List;

import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;


	@Override
	public boolean add(Bill bill) {
		return false;
	}

	@Override
	public List<Bill> getBillList(Bill bill) {
		return null;
	}

	@Override
	public boolean deleteBillById(String delId) {
		return false;
	}

	@Override
	public Bill getBillById(String id) {
		return null;
	}

	@Override
	public boolean modify(Bill bill) {
		return false;
	}
}

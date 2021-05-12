package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderDao providerDao;


	@Override
	public boolean add(Provider provider) {
		return false;
	}

	@Override
	public List<Provider> getProviderList(String proName, String proCode) {
		return null;
	}

	@Override
	public int deleteProviderById(String delId) {
		return 0;
	}

	@Override
	public Provider getProviderById(String id) {
		return null;
	}

	@Override
	public boolean modify(Provider provider) {
		return false;
	}
}

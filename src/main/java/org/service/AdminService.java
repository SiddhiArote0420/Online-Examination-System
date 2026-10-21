package org.service;

import org.model.AdminModel;
import org.repository.AdminRepository;

public class AdminService {
	AdminRepository repo =
			new AdminRepository();

			public boolean isAdminLogin(AdminModel model) {

				return repo.isAdminLogin(model);
			}
}

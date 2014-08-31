/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package martin.models.managers.impl;

import java.util.List;
import martin.models.dao.UserDao;
import martin.models.entities.User;
import martin.models.managers.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author martin
 */
@Service("userManager")
public class UserManagerImpl implements UserManager{

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}
}

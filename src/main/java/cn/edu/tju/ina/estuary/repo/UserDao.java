package cn.edu.tju.ina.estuary.repo;

import java.util.List;

import cn.edu.tju.ina.estuary.domain.user.User;
import cn.edu.tju.ina.estuary.repo.generic.GenericDao;

public interface UserDao extends GenericDao<User, Integer> {
    public List<User> findByPageByRandom(String property, Object value, int start, int length);
}

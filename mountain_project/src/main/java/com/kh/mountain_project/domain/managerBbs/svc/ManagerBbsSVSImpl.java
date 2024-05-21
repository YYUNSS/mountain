package com.kh.mountain_project.domain.managerBbs.svc;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import com.kh.mountain_project.domain.managerBbs.dao.ManagerBbsDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ManagerBbsSVSImpl implements ManagerBbsSVC{
  private ManagerBbsDAO managerBbsDAO;
  ManagerBbsSVSImpl(ManagerBbsDAO managerBbsDAO){
    this.managerBbsDAO = managerBbsDAO;
  }
  @Override
  public List<ManagerBbs> mReadAll() {
    return managerBbsDAO.mReadAll();
  }

  @Override
  public List<ManagerBbs> mReadComplain(Long bbsId) {
    return managerBbsDAO.mReadComplain(bbsId);
  }

  @Override
  public int deleteComplain(List<Long> bbsIds) {
    return managerBbsDAO.deleteComplain(bbsIds);
  }
}

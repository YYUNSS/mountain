package com.kh.mountain_project.domain.managerBbs.dao;

import com.kh.mountain_project.domain.entity.ManagerBbs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class ManagerBbsDAOImpl implements ManagerBbsDAO{
  private final NamedParameterJdbcTemplate template;
  ManagerBbsDAOImpl(NamedParameterJdbcTemplate template){
    this.template = template;
  }

  @Override
  public List<ManagerBbs> mReadAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select BBS_ID, MNTN_CODE, TITLE, BCONTENT, STATUS, CDATE, UDATE ");
    sql.append(" from BBS ");
    sql.append(" where STATUS != 'D' ");
    sql.append(" order by cdate desc ");
    List<ManagerBbs> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(ManagerBbs.class));
    return list;
  }

  @Override
  public List<ManagerBbs> mReadComplain() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select BBS_ID, MNTN_CODE, TITLE, BCONTENT, STATUS, CDATE, UDATE ");
    sql.append(" from BBS ");
    sql.append(" where STATUS = 'W' ");
    sql.append(" order by cdate desc ");
    List<ManagerBbs> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(ManagerBbs.class));
    return list;
  }

  @Override
  public int deleteComplain(List<Long> bbsIds) {
    StringBuffer sql = new StringBuffer();
    sql.append(" UPDATE BBS");
    sql.append(" SET STATUS = 'D' ");
    sql.append(" where bbs_id in (:bbsIds) ");

    Map<String, List<Long>> map = Map.of("bbsIds", bbsIds);
    int deletedRowCnt = template.update(sql.toString(), map);
    return deletedRowCnt;
  }

}

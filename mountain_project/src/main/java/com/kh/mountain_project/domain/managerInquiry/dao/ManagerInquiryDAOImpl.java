package com.kh.mountain_project.domain.managerInquiry.dao;

import com.kh.mountain_project.domain.entity.ManagerInquiry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ManagerInquiryDAOImpl implements ManagerInquiryDAO{
  private final NamedParameterJdbcTemplate template;
  ManagerInquiryDAOImpl(NamedParameterJdbcTemplate template){
    this.template = template;
  }
  @Override
  public List<ManagerInquiry> viewInquiryAll() {
    StringBuffer sql = new StringBuffer();
    sql.append(" select * from manager ");
    List<ManagerInquiry> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(ManagerInquiry.class));
    return list;
  }
}

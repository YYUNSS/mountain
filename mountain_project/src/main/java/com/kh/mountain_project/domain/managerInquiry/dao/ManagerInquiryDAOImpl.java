package com.kh.mountain_project.domain.managerInquiry.dao;

import com.kh.mountain_project.domain.entity.ManagerInquiry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    String query = " SELECT " +
            " m.INQUIRY_ID AS inquiry_id, " +
            " m.MEMBER_ID AS member_id, " +
            " m.INQUIRY_TITLE AS inquiry_title, " +
            " m.INQUIRY_CONTENT AS inquiry_content, " +
            " m.INQUIRY_COMMENT AS inquiry_comment, " +
            " m.INQUIRY_STATE AS inquiry_state, " +
            " m.INQUIRY_CDATE AS inquiry_cdate, " +
            " m.INQUIRY_UDATE AS inquiry_udate, " +
            " m.INQUIRY_COMMENT_CDATE AS inquiry_comment_cdate, " +
            " m.INQUIRY_COMMENT_UDATE AS inquiry_comment_udate, " +
            " mb.ID AS member_id, " +
            " mb.NICKNAME AS member_nickname " +
            " FROM manager m " +
            " LEFT JOIN member mb ON m.MEMBER_ID = mb.MEMBER_ID ";

    List<ManagerInquiry> list = template.query(query, (ResultSet rs) -> {
      List<ManagerInquiry> resultList = new ArrayList<>();
      while (rs.next()) {
        ManagerInquiry managerInquiry = new ManagerInquiry();
        managerInquiry.setInquiryId(rs.getLong("inquiry_id"));
        managerInquiry.setMemberId(rs.getLong("member_id"));
        managerInquiry.setInquiryTitle(rs.getString("inquiry_title"));
        managerInquiry.setInquiryContent(rs.getString("inquiry_content"));
        managerInquiry.setInquiryComment(rs.getString("inquiry_comment"));
        managerInquiry.setInquiryState(rs.getString("inquiry_state"));
        Timestamp inquiryCdateTimestamp = rs.getTimestamp("inquiry_cdate");
        managerInquiry.setInquiryCdate(inquiryCdateTimestamp != null ? inquiryCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryUdateTimestamp = rs.getTimestamp("inquiry_udate");
        managerInquiry.setInquiryUdate(inquiryUdateTimestamp != null ? inquiryUdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentCdateTimestamp = rs.getTimestamp("inquiry_comment_cdate");
        managerInquiry.setInquiryCommentCdate(inquiryCommentCdateTimestamp != null ? inquiryCommentCdateTimestamp.toLocalDateTime() : null);
        Timestamp inquiryCommentUdateTimestamp = rs.getTimestamp("inquiry_comment_udate");
        managerInquiry.setInquiryCommentUdate(inquiryCommentUdateTimestamp != null ? inquiryCommentUdateTimestamp.toLocalDateTime() : null);
        managerInquiry.setNickname(rs.getString("member_nickname"));
        resultList.add(managerInquiry);
      }
      return resultList;
    });
    return list;
  }

  //문의글 검색

}

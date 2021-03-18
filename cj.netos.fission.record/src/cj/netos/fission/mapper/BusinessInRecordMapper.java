package cj.netos.fission.mapper;

import cj.netos.fission.model.BusinessInRecord;
import cj.netos.fission.model.BusinessInRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessInRecordMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(BusinessInRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(BusinessInRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(BusinessInRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(BusinessInRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<BusinessInRecord> selectByExample(BusinessInRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    BusinessInRecord selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") BusinessInRecord record, @Param("example") BusinessInRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") BusinessInRecord record, @Param("example") BusinessInRecordExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(BusinessInRecord record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(BusinessInRecord record);

    void done(@Param(value = "sn") String sn, @Param(value = "status") int status, @Param(value = "message") String message);

    List<BusinessInRecord> page(@Param(value = "limit") int limit, @Param(value = "offset") long offset);

    List<BusinessInRecord> pageByShuntState(@Param(value = "shuntState") int shuntState, @Param(value = "limit") int limit, @Param(value = "offset") long offset);
}
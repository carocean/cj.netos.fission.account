package cj.netos.fission.mapper;

import cj.netos.fission.model.BusinessBill;
import cj.netos.fission.model.BusinessBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessBillMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(BusinessBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(BusinessBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(BusinessBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(BusinessBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<BusinessBill> selectByExample(BusinessBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    BusinessBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") BusinessBill record, @Param("example") BusinessBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") BusinessBill record, @Param("example") BusinessBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(BusinessBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(BusinessBill record);
}
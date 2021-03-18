package cj.netos.fission.mapper;

import cj.netos.fission.model.IncomeBill;
import cj.netos.fission.model.IncomeBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IncomeBillMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(IncomeBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(IncomeBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(IncomeBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(IncomeBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<IncomeBill> selectByExample(IncomeBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    IncomeBill selectByPrimaryKey(String sn);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") IncomeBill record, @Param("example") IncomeBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") IncomeBill record, @Param("example") IncomeBillExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(IncomeBill record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(IncomeBill record);
}
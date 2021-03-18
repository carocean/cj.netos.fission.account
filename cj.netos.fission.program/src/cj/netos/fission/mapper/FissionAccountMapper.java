package cj.netos.fission.mapper;

import cj.netos.fission.model.FissionAccount;
import cj.netos.fission.model.FissionAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FissionAccountMapper {

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    long countByExample(FissionAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByExample(FissionAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(String account);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(FissionAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insertSelective(FissionAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<FissionAccount> selectByExample(FissionAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    FissionAccount selectByPrimaryKey(String account);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExampleSelective(@Param("record") FissionAccount record, @Param("example") FissionAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByExample(@Param("record") FissionAccount record, @Param("example") FissionAccountExample example);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKeySelective(FissionAccount record);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(FissionAccount record);

    void updateBalance(@Param(value = "account") String account,@Param(value = "balance") long balance);

}
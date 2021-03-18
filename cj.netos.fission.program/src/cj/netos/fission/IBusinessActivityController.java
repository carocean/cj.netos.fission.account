package cj.netos.fission;

import cj.netos.fission.model.BusinessInRecord;

public interface IBusinessActivityController {
    void inBusiness(BusinessInRecord record);

    void error(BusinessInRecord record, int i, String msg);

}

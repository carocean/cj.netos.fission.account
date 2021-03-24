package cj.netos.fission;

import cj.netos.fission.model.AbsorbOutRecord;
import cj.studio.ecm.net.CircuitException;

public interface IOutAbsorbActivityController {
    AbsorbOutRecord receipt();

    void out(AbsorbOutRecord outRecord) throws CircuitException;

    void pushToRobot(AbsorbOutRecord outRecord) throws CircuitException;

    void error(AbsorbOutRecord outRecord, int i, String msg);

}

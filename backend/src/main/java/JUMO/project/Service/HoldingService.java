package JUMO.project.Service;

import JUMO.project.Controller.HoldingInvestmentDTO;
import JUMO.project.Entity.Holding;

import java.util.List;

public interface HoldingService {
    List<HoldingInvestmentDTO> findUserAllHoldingInfo(Long uid);
}

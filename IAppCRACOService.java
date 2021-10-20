package kz.ecf.ns.service;

import kz.ecf.ns.dto.UpdateStatusDTO;
import kz.ecf.ns.model.applications.craco.AppCRACO;
import kz.ecf.ns.dto.ApplicationCracoDTO;

import java.util.List;

public interface IAppCRACOService {
    AppCRACO save(ApplicationCracoDTO applicationCracoDTO);
    AppCRACO getById(Long id);
    void deleteById(Long id);
    List<AppCRACO> getAll();
    Boolean checkAppValidation(String bpmId, Long appId, String userId);

    void updateStatus(UpdateStatusDTO updateStatusDTO);
    void applicationIssued(UpdateStatusDTO updateStatusDTO);
}

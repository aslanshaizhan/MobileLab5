package kz.ecf.ns.service.impl;

import kz.ecf.ns.dto.UpdateStatusDTO;
import kz.ecf.ns.enums.AppStatus;
import kz.ecf.ns.enums.JobTypesEnum;
import kz.ecf.ns.model.applications.craco.AppCRACO;
import kz.ecf.ns.dto.ApplicationCracoDTO;
import kz.ecf.ns.repository.applications.craco.AppCRACORepository;
import kz.ecf.ns.service.*;
import kz.ecf.ns.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class AppCRACOService implements IAppCRACOService {

    private final AppCRACORepository cracoRepository;
    private final IAppCRACOHistoryService cracoHistoryService;
    private final ICompanyService companyService;
    private final IGuarantyService guarantyService;
    private final IInsuranceService insuranceService;
    private final IOffencesService offencesService;

    @Override
    public AppCRACO save(ApplicationCracoDTO app) {
        AppCRACO appCRACO;
        var ktrmCompany = companyService.update(app);

        if(app.getId() == null) {
            log.info("New application");
            appCRACO = AppCRACO
                .builder()
                .applicationStatus(AppStatus.DRAFT) // app.getApplicationStatus()
                .originCountryCode(app.getOriginCountryCode())
                .originCountryName(app.getOriginCountryName())
                .applicationName(app.getApplicationName())
                .applicationDate(app.getApplicationDate())
                .modifiedDate(app.getModifiedDate())
                .validFrom(app.getValidFrom())
                .validTo(app.getValidTo())
                .bin(app.getBin())
                .iin(app.getIin())
                .nicadNumber(app.getNicadNumber())
                .finalNicadNumber(app.getFinalNicadNumber())
                .registrationDate(app.getRegistrationDate())
                .declarantResident(app.getDeclarantResident())
                .declarantIin(app.getDeclarantIin())
                .declarantFirstName(app.getDeclarantFirstName())
                .declarantMiddleName(app.getDeclarantMiddleName())
                .declarantLastName(app.getDeclarantLastName())
                .declarantPosition(app.getDeclarantPosition())
                .declarantBirthDate(app.getDeclarantBirthDate())
                .agreementOfUsingInformation(app.getAgreementOfUsingInformation())
                .agreementOfInformationCorrectness(app.getAgreementOfInformationCorrectness())
                .noOutstandingPaymentObligations(app.getNoOutstandingPaymentObligations())
                .esfBlockingDate(app.getEsfBlockingDate())
                .esfRegistrationDate(app.getEsfRegistrationDate())
                .esfBlockingReason(app.getEsfBlockingReason())
                .registrationNumber(app.getRegistrationNumber())
                .chiefId(app.getChiefId())
                .coordinatorId(app.getCoordinatorId())
                .expertId(app.getExpertId())
                .issueDate(app.getIssueDate())
                .idSwsUser(app.getIdSwsUser())
                .idBpm(app.getIdBpm())
                .company(ktrmCompany)
                .build();
        } else {
            log.info("NOT New application");
            appCRACO = getById(app.getId());
            appCRACO.setApplicationStatus(app.getApplicationStatus());
            appCRACO.setOriginCountryCode(app.getOriginCountryCode());
            appCRACO.setOriginCountryName(app.getOriginCountryName());
            appCRACO.setApplicationName(app.getApplicationName());
            appCRACO.setApplicationDate(app.getApplicationDate());
            appCRACO.setModifiedDate(app.getModifiedDate());
            appCRACO.setValidFrom(app.getValidFrom());
            appCRACO.setValidTo(app.getValidTo());
            appCRACO.setBin(app.getBin());
            appCRACO.setIin(app.getIin());
            appCRACO.setNicadNumber(app.getNicadNumber());
            appCRACO.setFinalNicadNumber(app.getFinalNicadNumber());
            appCRACO.setRegistrationDate(app.getRegistrationDate());
            appCRACO.setDeclarantResident(app.getDeclarantResident());
            appCRACO.setDeclarantIin(app.getDeclarantIin());
            appCRACO.setDeclarantFirstName(app.getDeclarantFirstName());
            appCRACO.setDeclarantMiddleName(app.getDeclarantMiddleName());
            appCRACO.setDeclarantLastName(app.getDeclarantLastName());
            appCRACO.setDeclarantPosition(app.getDeclarantPosition());
            appCRACO.setDeclarantBirthDate(app.getDeclarantBirthDate());
            appCRACO.setAgreementOfUsingInformation(app.getAgreementOfUsingInformation());
            appCRACO.setAgreementOfInformationCorrectness(app.getAgreementOfInformationCorrectness());
            appCRACO.setNoOutstandingPaymentObligations(app.getNoOutstandingPaymentObligations());
            appCRACO.setEsfBlockingDate(app.getEsfBlockingDate());
            appCRACO.setEsfRegistrationDate(app.getEsfRegistrationDate());
            appCRACO.setEsfBlockingReason(app.getEsfBlockingReason());
            appCRACO.setRegistrationNumber(app.getRegistrationNumber());
            appCRACO.setChiefId(app.getChiefId());
            appCRACO.setCoordinatorId(app.getCoordinatorId());
            appCRACO.setExpertId(app.getExpertId());
            appCRACO.setIssueDate(app.getIssueDate());
            appCRACO.setIdSwsUser(app.getIdSwsUser());
            appCRACO.setIdBpm(app.getIdBpm());
        }
        log.info("Before save");
        var savedApp = cracoRepository.saveAndFlush(appCRACO);
        log.info("After save");

        // save guaranty infos
        log.info("save guaranty infos");
        guarantyService.updateGuarantiesByCompany(savedApp, app.getGuaranteeDTO());
        // save insurance infos
        log.info("save insurance infos");
        insuranceService.updateInsurancesByCompany(savedApp, app.getInsuranceDTO());
        // save offences infos
        log.info("save offences infos");
        offencesService.updateOffencesByCompany(savedApp, app.getOffencesDTO());

        savedApp = getById(savedApp.getId());
        cracoHistoryService.save(savedApp, ktrmCompany);
        log.info("~~~~~~~~~~~~~~~END~~~~~~~~~~~~~~~");
        return savedApp;
    }

    @Override
    public AppCRACO getById(Long id) {
        return cracoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppCRACO.class, id));
    }

    @Override
    public void deleteById(Long id) {
        cracoRepository.deleteById(id);
    }

    @Override
    public List<AppCRACO> getAll() {
        return cracoRepository.findAll();
    }

    @Override
    public Boolean checkAppValidation(String bpmId, Long appId, String userId) {
        log.info("checkAppValidation=====");
        boolean checkValidation = true;
        var appCRACO = cracoRepository.getById(appId);

        if(appCRACO == null) {
            return false;
        }

        checkValidation = appCRACO.getAgreementOfUsingInformation() && appCRACO.getAgreementOfInformationCorrectness() &&
                          appCRACO.getNoOutstandingPaymentObligations();
        // TODO need to add logic
        // 1) договора страхования риска гражданской ответственности
        // 2) обеспечение исполнения обязанностей юридического лица
        // 3) не исполненной в установленный срок обязанности по уплате ТПиН +
        // 4) Соглашение ЭСФ
        // 5) Отсутствие непогашенной судимости Уголовного кодекса Республики Казахстан у физических лиц, являющихся руководителями юридических
        // лиц, претендующих на включение в реестр таможенных представителей в случае, если ранее таможенный представитель был исключен из
        // реестра (за привлечение в течение одного календарного года более двух раз к административной ответственности в соответствии со статьями 551
        // и 536 КоАП РК, либо при не возобновлении деятельности в реестре в течении 60 календарных дней при выявлении нарушений, при ликвидации
        // либо реорганизации юр.лица, при привлечении к уголовной ответственности), лицо не имеет права включаться в реестр в течении 1 года
        //
        // Я согласен на использование сведений, составляющих охраняемую законом тайну, содержащуюся в информационных системах, исключительно
        // в рамках оказания государственной услуги. +
        //
        // Я подтверждаю достоверность представленной информации, осведомлен
        // об ответственности за предоставление недостоверных сведений в соответствии с законами Республики Казахстан +

        appCRACO.setCoordinatorId(userId);
        appCRACO.setIdBpm(bpmId);
        appCRACO.setApplicationStatus(AppStatus.SUBMITTED);
        cracoRepository.saveAndFlush(appCRACO);
        return checkValidation;
    }

    @Override
    public void updateStatus(UpdateStatusDTO updateStatusDTO) {
        var appCRACO = getById(updateStatusDTO.getAppId());

        appCRACO.setApplicationStatus(AppStatus.valueOf(updateStatusDTO.getAppStatus()));
        switch (JobTypesEnum.valueOf(updateStatusDTO.getJobType())) {
            case EXPERT:
                appCRACO.setExpertId(updateStatusDTO.getUserId());
                break;
            case COORDINATOR:
                appCRACO.setCoordinatorId(updateStatusDTO.getUserId());
                break;
            case CHIEF:
                appCRACO.setChiefId(updateStatusDTO.getUserId());
                break;
        }
        cracoRepository.saveAndFlush(appCRACO);
    }

    @Override
    public void applicationIssued(UpdateStatusDTO updateStatusDTO){
        var appCRACO = getById(updateStatusDTO.getAppId());

        appCRACO.setRegistrationDate(new Date());
        appCRACO.setApplicationStatus(AppStatus.valueOf(updateStatusDTO.getAppStatus()));

        cracoRepository.saveAndFlush(appCRACO);

        // history
        cracoHistoryService.save(appCRACO);
    }
}

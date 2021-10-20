package kz.ecf.ns.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import kz.ecf.ns.enums.AppNameEnum;
import kz.ecf.ns.enums.AppStatus;
import kz.ecf.ns.model.KtrmCompany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationCracoDTO {
    @JsonProperty("idApplication")
    @ApiModelProperty(required = true, example = "11")
    private Long id;

    @JsonProperty("ktrmCompanyId")
    @ApiModelProperty(required = true, example = "11")
    private UUID ktrmCompanyId;

    @JsonProperty("applicationStatus")
    @ApiModelProperty(example = "DRAFT")
    private AppStatus applicationStatus;
    
    @JsonProperty("originCountryCode")
    @ApiModelProperty(example = "KZ")
    private String originCountryCode;

    @JsonProperty("originCountryName")
    @ApiModelProperty(example = "Kazakhstan")
    private String originCountryName;

    @JsonProperty("applicationName")
    @ApiModelProperty(example = "ApplicationCustomsRepresentative")
    private AppNameEnum applicationName;

    @JsonProperty("applicationDate")
    @ApiModelProperty(example = "2014-01-01T23:28:56.782Z")
    private Date applicationDate;

    @JsonProperty("modifiedDate")
    @ApiModelProperty(example = "2014-01-01T23:28:56.782Z")
    private Date modifiedDate;

    @JsonProperty("validFrom")
    @ApiModelProperty(example = "2014-01-01T23:28:56.782Z")
    private Date validFrom;

    @JsonProperty("validTo")
    @ApiModelProperty(example = "2014-01-01T23:28:56.782Z")
    private Date validTo;

    @JsonProperty("resident")
    @ApiModelProperty(example = "true")
    private Boolean resident;

    @JsonProperty("bin")
    @ApiModelProperty(example = "KZ24070105KSN0000000")
    private String bin;

    @JsonProperty("iin")
    @ApiModelProperty(example = "760724300757")
    private String iin;

    @JsonProperty("nicadNumber")
    @ApiModelProperty(example = "444")
    private String nicadNumber;

    @JsonProperty("finalNicadNumber")
    @ApiModelProperty(example = "444")
    private String finalNicadNumber;

    @JsonProperty("registrationDate")
    @ApiModelProperty(example = "2014-01-01T23:28:56.782Z")
    private Date registrationDate;

    @JsonProperty("declarantResident")
    @ApiModelProperty(example = "true")
    private Boolean declarantResident;

    @JsonProperty("declarantIin")
    @ApiModelProperty(example = "760724300757")
    private String declarantIin;

    @JsonProperty("declarantFirstName")
    @ApiModelProperty(example = "First name")
    private String declarantFirstName;

    @JsonProperty("declarantMiddleName")
    @ApiModelProperty(example = "Middle name")
    private String declarantMiddleName;

    @JsonProperty("declarantLastName")
    @ApiModelProperty(example = "Last name")
    private String declarantLastName;

    @JsonProperty("declarantPosition")
    @ApiModelProperty(example = "backend developer")
    private String declarantPosition;

    @JsonProperty("declarantBirthDate")
    @ApiModelProperty(example = "1997-10-07T23:28:56.782Z")
    private Date declarantBirthDate;

    @JsonProperty("registrationNumber")
    @ApiModelProperty(example = "KZ.11.01.11.001.Е.000111.01.11")
    private String registrationNumber;

    @JsonProperty("companyName")
    @ApiModelProperty(example = "TOO <Company name>")
    private String companyName;

    @JsonProperty("chiefId")
    @ApiModelProperty(example = "11")
    private String chiefId;

    @JsonProperty("coordinatorId")
    @ApiModelProperty(example = "11")
    private String coordinatorId;

    @JsonProperty("expertId")
    @ApiModelProperty(example = "11")
    private String expertId;

    @JsonProperty("issueDate")
    @ApiModelProperty(example = "2020-01-01T23:28:56.782Z")
    private Date issueDate;

    @JsonProperty("idSwsUser")
    @ApiModelProperty(example = "11")
    private Long idSwsUser;

    @JsonProperty("idBpm")
    @ApiModelProperty(example = "11")
    private String idBpm;

    @ApiModelProperty(example = "true", value = "Я согласен на использование сведений, составляющих охраняемую законом" +
            " тайну, содержащуюся в информационных системах, исключительно в рамках оказания государственной услуги.")
    private Boolean agreementOfUsingInformation;

    @ApiModelProperty(example = "true", value = "Я подтверждаю достоверность представленной информации, осведомлен об" +
            " ответственности за предоставление недостоверных сведений в соответствии с законами Республики Казахстан.")
    private Boolean agreementOfInformationCorrectness;

    @ApiModelProperty(example = "true", value = "У меня отсутствуют неисполненные обязанности по уплате")
    private Boolean noOutstandingPaymentObligations;

    @JsonProperty("esfBlockingDate")
    @ApiModelProperty(example = "2021-07-13T16:07:05.391+0000")
    private Date esfBlockingDate;

    @JsonProperty("esfRegistrationDate")
    @ApiModelProperty(example = "2014-01-01T23:28:56.782Z")
    private Date esfRegistrationDate;

    @JsonProperty("esfBlockingReason")
    @ApiModelProperty(example = "OK")
    private String esfBlockingReason;

    @JsonProperty("address")
    @ApiModelProperty(value = "List of company addresses")
    private List<CompanyAddressDTO> addressDTO;

    @JsonProperty("communication")
    @ApiModelProperty(value = "List of company contacts")
    private List<CompanyCommunicationDTO> communicationDTO;

    @JsonProperty("guarantee")
    @ApiModelProperty(value = "List of guarantees")
    private List<ApplicantGuaranteeDTO> guaranteeDTO;

    @JsonProperty("insurance")
    @ApiModelProperty(value = "List of insurance")
    private List<ApplicantInsuranceDTO> insuranceDTO;

    @JsonProperty("offences")
    @ApiModelProperty(value = "List of offences")
    private List<ApplicantOffencesDTO> offencesDTO;

    @JsonProperty("ktrmCompany")
    @ApiModelProperty(value = "ktrm company")
    private KtrmCompany ktrmCompany;
}

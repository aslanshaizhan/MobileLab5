package kz.ecf.ns.model.applications.craco;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.ecf.ns.enums.AppNameEnum;
import kz.ecf.ns.enums.AppStatus;
import kz.ecf.ns.model.ApplicantGuarantee;
import kz.ecf.ns.model.ApplicantInsurance;
import kz.ecf.ns.model.KtrmCompany;
import kz.ecf.ns.model.base.BaseEntity;
import kz.ecf.ns.util.EnumTypePostgreSql;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "_app_cra_co")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "enum_postgre", typeClass = EnumTypePostgreSql.class)
})
@EqualsAndHashCode(callSuper = true)
public class AppCRACO extends BaseEntity<Long> implements Serializable {

    @Column(name = "external_id")
    private Long externalId;

    @Column(name = "id_sws_user")
    private Long idSwsUser;

    @Column(name = "id_bpm")
    private String idBpm;

//    @Column(name = "ktrm_company_id")
//    private UUID ktrmCompanyId;

    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    @Type(type = "enum_postgre")
    private AppStatus applicationStatus;

    @Column(name = "origin_country_code")
    private String originCountryCode;

    @Column(name = "origin_country_name")
    private String originCountryName;

    @Column(name = "application_name")
    @Enumerated(EnumType.STRING)
    @Type(type = "enum_postgre")
    private AppNameEnum applicationName;

    @Column(name = "application_date")
    private Date applicationDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "valid_from")
    private Date validFrom;

    @Column(name = "valid_to")
    private Date validTo;

    @Column(name = "bin")
    private String bin;

    @Column(name = "iin")
    private String iin;

    @Column(name = "nicad_number")
    private String nicadNumber;

    @Column(name = "final_nicad_number")
    private String finalNicadNumber;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "declarant_resident")
    private Boolean declarantResident;

    @Column(name = "declarant_iin")
    private String declarantIin;

    @Column(name = "declarant_first_name")
    private String declarantFirstName;

    @Column(name = "declarant_middle_name")
    private String declarantMiddleName;

    @Column(name = "declarant_last_name")
    private String declarantLastName;

    @Column(name = "declarant_position")
    private String declarantPosition;

    @Column(name = "declarant_birth_date")
    private Date declarantBirthDate;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "chief_id")
    private String chiefId;

    @Column(name = "coordinator_id")
    private String coordinatorId;

    @Column(name = "expert_id")
    private String expertId;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "agreement_of_using_information")
    private Boolean agreementOfUsingInformation;

    @Column(name = "agreement_of_information_correctness")
    private Boolean agreementOfInformationCorrectness;

    @Column(name = "no_outstanding_payment_obligations")
    private Boolean noOutstandingPaymentObligations;

    @Column(name = "esf_blocking_date")
    private Date esfBlockingDate;

    @Column(name = "esf_registration_date")
    private Date esfRegistrationDate;

    @Column(name = "esf_blocking_reason")
    private String esfBlockingReason;

    @JsonIgnore
    @OneToMany(mappedBy = "appCRACO", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
               orphanRemoval = true)
    private List<ApplicantGuarantee> applicantGuarantees;

    @JsonIgnore
    @OneToMany(mappedBy = "appCRACO", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<ApplicantInsurance> applicantInsurances;

    @JsonIgnore
    @OneToMany(mappedBy = "appCRACO", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<ApplicantInsurance> applicantOffenses;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ktrm_company_id", referencedColumnName = "id")
    private KtrmCompany company;
}

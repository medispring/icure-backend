package org.taktik.icure.be.ehealth.logic.kmehr.sumehr.impl.v20161201

import ma.glasnost.orika.MapperFacade
import org.mockito.Matchers.any
import org.mockito.Matchers.eq
import org.mockito.Mockito
import org.taktik.icure.entities.HealthcareParty
import org.taktik.icure.entities.Patient
import org.taktik.icure.entities.base.Code
import org.taktik.icure.entities.base.CodeStub
import org.taktik.icure.entities.embed.*
import org.taktik.icure.entities.embed.AddressType
import org.taktik.icure.entities.embed.ContractChangeType
import org.taktik.icure.entities.embed.Gender
import org.taktik.icure.entities.embed.ReferralPeriod
import org.taktik.icure.entities.embed.SuspensionReason
import org.taktik.icure.entities.embed.TelecomType
import org.taktik.icure.logic.impl.CodeLogicImpl
import org.taktik.icure.logic.impl.ContactLogicImpl
import org.taktik.icure.services.external.api.AsyncDecrypt
import org.taktik.icure.services.external.rest.v1.dto.CodeDto
import org.taktik.icure.services.external.rest.v1.dto.embed.*
import org.taktik.icure.utils.FuzzyValues
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

private const val DIR_PATH = "src/test/resources/org/taktik/icure/be/ehealth/logic/kmehr/sumehr/impl/v20161201/out"

private val sumehrExport = SumehrExport()

private val contactLogic = Mockito.mock(ContactLogicImpl::class.java)
private val codeLogic = Mockito.mock(CodeLogicImpl::class.java)
private val decryptor = Mockito.mock(AsyncDecrypt::class.java)
private val mapper = Mockito.mock(MapperFacade::class.java)

private const val language = "fr"

private const val adr = "adr"
private const val allergy = "allergy"
private const val socialrisk = "socialrisk"
private const val risk = "risk"
private const val patientwill = "patientwill"
private const val vaccine = "vaccine"
private const val medication = "medication"
private const val treatment = "treatment"
private const val healthissue = "healthissue"
private const val healthcareelement = "healthcareelement"

private val dateFormat = SimpleDateFormat("yyyyMMdd")
private val tomorrow = FuzzyValues.getFuzzyDate(LocalDateTime.now().plusDays(1), ChronoUnit.SECONDS)
private val today = FuzzyValues.getFuzzyDate(LocalDateTime.now(), ChronoUnit.SECONDS)
private val yesterday = FuzzyValues.getFuzzyDate(LocalDateTime.now().minusDays(1), ChronoUnit.SECONDS)
private val oneWeekAgo = FuzzyValues.getFuzzyDate(LocalDateTime.now().minusWeeks(1), ChronoUnit.SECONDS)
private val oneMonthAgo = FuzzyValues.getFuzzyDate(LocalDateTime.now().minusMonths(1), ChronoUnit.SECONDS)

private class MyContents {
    companion object {
        val medicationContent = mapOf(Pair(language, Content().apply {
            medicationValue = Medication().apply {
                medicinalProduct = Medicinalproduct().apply {
                    intendedname = "medicationName"
                }
            }
        }))
    }
}

private class MyCodes {
    companion object {
        val vaccineCode = CodeStub("CD-VACCINEINDICATION", "", "1.0")
    }
}

private class MyTags {
    companion object {
        val adrTag = CodeStub("type", adr, "1.0")
        val inactiveTag = CodeStub("CD-LIFECYCLE", "inactive", "1")
    }
}

private val services = mutableMapOf<String, List<Service>>()

private class MyServices {
    companion object {
        val validServiceADRAssessment = Service().apply {
            this.id = "1"
            this.endOfLife = null
            this.status = 0 // must be active => Assessment
            this.tags = mutableSetOf(MyTags.adrTag)
            this.codes = mutableSetOf(MyCodes.vaccineCode)
            this.label = medication
            this.content = MyContents.medicationContent
            this.comment = "It's a comment"
            this.openingDate = oneWeekAgo
            this.closingDate = today
        }

        val validServiceADRHistory = Service().apply {
            this.id = "1"
            this.endOfLife = null
            this.status = 1 // must be inactive => History
            this.tags = mutableSetOf(MyTags.adrTag, MyTags.inactiveTag)
            this.codes = mutableSetOf(MyCodes.vaccineCode)
            this.label = medication
            this.content = MyContents.medicationContent
            this.comment = "comment"
            this.openingDate = oneWeekAgo
            this.closingDate = today
        }
    }
}

fun main() {
    initializeSumehrExport()
    initializeMocks()

    generateMinimalist()
    generateFullPatientSumehr()
    generateSumehr1()
}

private fun initializeSumehrExport() {
    sumehrExport.contactLogic = contactLogic
    sumehrExport.codeLogic = codeLogic
    sumehrExport.mapper = mapper
}

private var index = 0
private val keys = listOf(adr, allergy, socialrisk, risk, patientwill, vaccine, medication, treatment, healthissue, healthcareelement)
private fun initializeMocks() {
    Mockito.`when`(contactLogic.getServices(any())).thenAnswer {
        services.getOrDefault(keys[index++ % keys.size], emptyList())
    }

    Mockito.`when`(codeLogic.isValid(any() ?: Code(), any())).thenAnswer {
        val code = it.getArgumentAt(0, Code::class.java) as Code
        when (code.type) {
            "CD-FED-COUNTRY" -> when (code.code) {
                "be", "belgium", "belgique" -> true
                "fr", "france" -> true
                else -> false
            }
            else -> false
        }
    }

    Mockito.`when`(decryptor.decrypt<ServiceDto>(any(), any())).thenAnswer {
        val encryptedServices = it.getArgumentAt(0, ArrayList::class.java) as ArrayList<ServiceDto>
        object : Future<List<ServiceDto>> {
            private val decryptedServices = encryptedServices.map { it.decrypt() }

            override fun isDone(): Boolean = true
            override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false
            override fun isCancelled(): Boolean = false
            override fun get(): List<ServiceDto> = decryptedServices
            override fun get(timeout: Long, unit: TimeUnit): List<ServiceDto> = decryptedServices
        }
    }

    Mockito.`when`(mapper.map<Service, ServiceDto>(any(Service::class.java), eq(ServiceDto::class.java))).thenAnswer {
        val service = it.getArgumentAt(0, ArrayList::class.java) as Service
        service.map()
    }
}

private fun clearServices() {
    index = 0
    services.clear()
}

private fun generateMinimalist() {
    clearServices()

    /// First parameter : os
    val os = File(DIR_PATH + "MinimalSumehr.xml").outputStream()

    /// Second parameter : pat
    val patient = Patient().apply {
        id = "316804da-9234-43d6-b18c-df0cccd46744"
        firstName = "Sargent"
        lastName = "Berie"
        ssin = "50010100156"
        gender = Gender.fromCode("M")
        dateOfBirth = 19500101
        languages = listOf("French")
    }

    /// Third parameter : sfks
    val sfks = listOf("sfks")

    /// Fourth parameter
    val sender = HealthcareParty().apply {
        id = "8e716232-04ce-4262-8f71-3c51521fd740"
        nihii = "18000032004"
        ssin = "50010100156"
        firstName = "Orville"
        lastName = "Flamand"
        addresses = listOf(Address().apply {
            addressType = AddressType.home
            street = "Rue de Berloz"
            houseNumber = "267"
            postalCode = "4860"
            city = "Cornesse"
            telecoms = listOf(Telecom().apply {
                telecomNumber = "0474301934"
            })
        })
        speciality = "persphysician"
    }

    /// Fifth parameter
    val recipient = HealthcareParty().apply {
        speciality = "persphysician"
    }

    /// Seventh parameter
    val comment = "All data is fake"

    /// Eighth parameter
    val excludedIds = emptyList<String>()

    // Execution
    sumehrExport.createSumehr(os, patient, sfks, sender, recipient, language, comment, excludedIds, decryptor)
}

private fun generateSumehr1() {
    clearServices()

    /// First parameter : os
    val os = File(DIR_PATH + "Sumehr1.xml").outputStream()

    /// Second parameter : pat
    val patient = Patient().apply {
        id = "idPatient"
        firstName = "firstNamePatient"
        lastName = "lastNamePatient"
        ssin = "50010100156"
        civility = "Mr"
        gender = Gender.fromCode("M")
        dateOfBirth = 19500101
        placeOfBirth = "Bruxelles"
        profession = "Cobaye"
        nationality = "Belge"
        addresses = listOf(Address().apply {
            addressType = AddressType.home
            street = "streetPatient"
            houseNumber = "1D"
            postalCode = "1050"
            city = "Ixelles"
            telecoms = listOf(Telecom().apply {
                telecomType = TelecomType.phone
                telecomNumber = "0423456789"
                telecomDescription = "personal phone"
            })
        })
        languages = listOf("French")
    }

    /// Third parameter : sfks
    val sfks = listOf("sfks")

    /// Fourth parameter
    val sender = HealthcareParty().apply {
        nihii = "18000032004"
        id = "idSender"
        ssin = "50010100156"
        firstName = "firstNameSender"
        lastName = "lastNameSender"
        addresses = listOf(Address().apply {
            addressType = AddressType.home
            street = "streetSender"
            houseNumber = "3A"
            postalCode = "1000"
            city = "Bruxelles"
            telecoms = listOf(Telecom().apply {
                telecomType = TelecomType.phone
                telecomNumber = "0423456789"
                telecomDescription = "personal phone"
            })
        })
        gender = Gender.fromCode("M")
        speciality = "persphysician"
        specialityCodes = listOf(CodeStub("CD-HCPARTY", "persphysician", "1"))
    }

    /// Fifth parameter
    val recipient = HealthcareParty().apply {
        nihii = "18000032004"
        id = "idRecipient"
        ssin = "50010100156"
        name = "PMGRecipient"
        addresses = listOf(Address().apply {
            addressType = AddressType.home
            street = "streetRecipient"
            houseNumber = "3A"
            postalCode = "1000"
            city = "Bruxelles"
        })
        gender = Gender.fromCode("M")
        speciality = "persphysician"
    }

    /// Seventh parameter
    val comment = "It's the comment done in main"

    /// Eighth parameter
    val excludedIds = emptyList<String>()

    services[adr] = listOf(MyServices.validServiceADRAssessment, MyServices.validServiceADRHistory)

    // Execution
    sumehrExport.createSumehr(os, patient, sfks, sender, recipient, language, comment, excludedIds, decryptor)
}

private fun generateFullPatientSumehr() {
    clearServices()

    /// First parameter : os
    val os = File(DIR_PATH + "FullPatientSumehr.xml").outputStream()

    /// Second parameter : pat
    val patient = Patient().apply {
        active = true
        administrativeNote = "This patient is fake"
        addresses = listOf(Address().apply {
            addressType = AddressType.home
            city = "De Moeren"
            country = "Belgium"
            descr = "This address is fake"
            houseNumber = "15"
            postalCode = "8630"
            postboxNumber = "15"
            street = "Industriestraat"
            telecoms = listOf(Telecom().apply {
                telecomDescription = "This phone number is fake"
                telecomNumber = "0490175135"
                telecomType = TelecomType.mobile
            })
        })
        this.alias = "Sarberie"
        this.author = "Dr Flamand"
        this.civility = "Mr"
        this.created = 20190101
        this.dateOfBirth = 19500101
        this.education = "Master en Cobayologie supérieure"
        this.externalId = "316804da-9234-43d6-b18c-df0cccd46744"
        this.financialInstitutionInformation = listOf(FinancialInstitutionInformation().apply {
            this.bankAccount = "553488836019"
            this.bic = "BPOTBEB1"
            this.key = "478"
            this.name = "MR SARGENT BERIE"
            this.preferredFiiForPartners = setOf("Foreign Institutional Investor")
            this.proxyBankAccount = "910638884355"
            this.proxyBic = "BPOTBEB"
        })
        this.firstName = "Sargent"
        this.gender = Gender.fromCode("M")
        this.id = "316804da-9234-43d6-b18c-df0cccd46744"
        this.insurabilities = listOf(Insurability().apply {
            this.ambulatory = true
            this.dental = true
            this.endDate = 20491231
            this.hospitalisation = true
            this.identificationNumber = "39672875"
            this.insuranceDescription = "All inclusive"
            this.insuranceId = "57827693"
            this.parameters = mapOf(Pair("Param", "Value"))
            this.startDate = 20000101
            this.titularyId = "35976872"
        })
        this.maidenName = "Beries"
        this.medicalHouseContracts = listOf(MedicalHouseContract().apply {
            this.changeType = ContractChangeType.suspension
            this.changedBy = "Damiane Drouin, Adviser"
            this.contractId = "92571275"
            this.endOfContract = 20290101
            this.endOfCoverage = 20283112
            this.endOfSuspension = tomorrow
            this.hcpId = "971c149d-62a4-4f0c-8aa9-9fbeca47465b"
            this.isForcedSuspension = true
            this.isGp = false
            this.isKine = true
            this.isNoGp = true
            this.isNoKine = false
            this.isNoNurse = false
            this.isNurse = true
            this.mmNihii = "18000131004"
            this.parentContractId = "57217592"
            this.startOfContract = 20183112
            this.startOfCoverage = 20190101
            this.startOfSuspension = yesterday
            this.suspensionReason = SuspensionReason.outsideOfCountry
            this.suspensionSource = "Drouin D., Adviser"
            this.unsubscriptionReasonId = 0
            this.validFrom = 20150101
            this.validTo = 20243112
        })
        this.languages = listOf("fr")
        this.lastName = "Berie"
        this.nationality = "be"
        this.parameters = mapOf(Pair("Param", listOf("Value1", "Value2")))
        this.partnerName = "Fayette Cadieux"
        this.partnerships = listOf(Partnership().apply {
            otherToMeRelationshipDescription = "Sœur"
            meToOtherRelationshipDescription = "Frère"
            partnershipDescription = "Jumeaux"
            partnerId = "793df193-6efb-4e63-b5b3-7bd5570f077a"
            status = PartnershipStatus.active
            type = PartnershipType.sister
        })
        this.patientHealthCareParties = listOf(PatientHealthCareParty().apply {
            this.healthcarePartyId = "3116d667-f3ba-4a9c-ab0a-313c9c3beeff"
            this.isReferral = true
            this.referralPeriods = sortedSetOf(
                    ReferralPeriod().apply {
                        this.comment = "First referral period"
                        this.endDate = dateFormat.parse("20141231").toInstant()
                        this.startDate = dateFormat.parse("20100101").toInstant()
                    },
                    ReferralPeriod().apply {
                        this.comment = "Second referral period"
                        this.endDate = dateFormat.parse("20191231").toInstant()
                        this.startDate = dateFormat.parse("20150101").toInstant()
                    },
                    ReferralPeriod().apply {
                        this.comment = "Third referral period"
                        this.endDate = dateFormat.parse("20241231").toInstant()
                        this.startDate = dateFormat.parse("20200101").toInstant()
                    }
            )
            this.sendFormats = mapOf(Pair(TelecomType.phone, "0484598271"))
            this.type = PatientHealthCarePartyType.doctor
        })
        this.profession = "Cobaye"
        this.patientProfessions = listOf(CodeStub("CD-PROFESSION", "Cobaye", "1.0"))
        this.personalStatus = PersonalStatus.married
        this.placeOfBirth = "Furnes"
        this.spouseName = "Fayette Cadieux"
        this.warning = "This patient is fake"
        this.ssin = "50010100156"
    }

    /// Third parameter : sfks
    val sfks = listOf("sfks")

    /// Fourth parameter
    val sender = HealthcareParty().apply {
        id = "8e716232-04ce-4262-8f71-3c51521fd740"
        nihii = "18000032004"
        ssin = "50010100156"
        firstName = "Orville"
        lastName = "Flamand"
        addresses = listOf(Address().apply {
            addressType = AddressType.home
            street = "Rue de Berloz"
            houseNumber = "267"
            postalCode = "4860"
            city = "Cornesse"
            telecoms = listOf(Telecom().apply {
                telecomNumber = "0474301934"
            })
        })
        speciality = "persphysician"
    }

    /// Fifth parameter
    val recipient = HealthcareParty().apply {
        speciality = "persphysician"
    }

    /// Seventh parameter
    val comment = "All data is fake"

    /// Eighth parameter
    val excludedIds = emptyList<String>()

    // Execution
    sumehrExport.createSumehr(os, patient, sfks, sender, recipient, language, comment, excludedIds, decryptor)
}

private fun Service.map(): ServiceDto {
    return ServiceDto().apply {
        this@apply.author = this@map.author
        this@apply.closingDate = this@map.closingDate
        this@apply.codes = this@map.codes.map { it.map() }.toSet()
        this@apply.comment = this@map.comment
        this@apply.content = this@map.content.map { entry -> Pair(entry.key, entry.value.map()) }.toMap()
        this@apply.contactId = this@map.contactId
        this@apply.created = this@map.created
        this@apply.encryptedSelf = this@map.encryptedSelf
        this@apply.encryptedContent = this@map.encryptedContent
        this@apply.encryptionKeys = this@map.encryptionKeys.map { entry -> Pair(entry.key, entry.value.map { it.map() }) }.toMap()
        this@apply.endOfLife = this@map.endOfLife
        this@apply.formId = this@map.formId
        this@apply.healthElementsIds = this@map.healthElementsIds.toMutableSet()
        this@apply.id = this@map.id
        this@apply.index = this@map.index
        this@apply.invoicingCodes = this@map.invoicingCodes.toMutableSet()
        this@apply.label = this@map.label
        this@apply.modified = this@map.modified
        this@apply.openingDate = this@map.openingDate
        this@apply.plansOfActionIds = this@map.plansOfActionIds.toMutableSet()
        this@apply.responsible = this@map.responsible
        this@apply.secretForeignKeys = this@map.secretForeignKeys.toMutableSet()
        this@apply.status = this@map.status
        this@apply.subContactIds = this@map.subContactIds.toMutableSet()
        this@apply.tags = this@map.tags.map { it.map() }.toSet()
        this@apply.textIndexes = this@map.textIndexes.map { Pair(it.key, it.value) }.toMap()
        this@apply.valueDate = this@map.valueDate
    }
}

private fun ServiceDto.copy(): ServiceDto {
    return ServiceDto().apply {
        this@apply.author = this@copy.author
        this@apply.closingDate = this@copy.closingDate
        this@apply.codes = this@copy.codes.map { it.copy() }.toSet()
        this@apply.comment = this@copy.comment
        this@apply.content = this@copy.content.map { entry -> Pair(entry.key, entry.value.copy()) }.toMap()
        this@apply.contactId = this@copy.contactId
        this@apply.created = this@copy.created
        this@apply.encryptedSelf = this@copy.encryptedSelf
        this@apply.encryptedContent = this@copy.encryptedContent
        this@apply.encryptionKeys = this@copy.encryptionKeys.map { entry -> Pair(entry.key, entry.value.map { it.copy() }) }.toMap()
        this@apply.endOfLife = this@copy.endOfLife
        this@apply.formId = this@copy.formId
        this@apply.healthElementsIds = this@copy.healthElementsIds.toMutableSet()
        this@apply.id = this@copy.id
        this@apply.index = this@copy.index
        this@apply.invoicingCodes = this@copy.invoicingCodes.toMutableSet()
        this@apply.label = this@copy.label
        this@apply.modified = this@copy.modified
        this@apply.openingDate = this@copy.openingDate
        this@apply.plansOfActionIds = this@copy.plansOfActionIds.toMutableSet()
        this@apply.responsible = this@copy.responsible
        this@apply.secretForeignKeys = this@copy.secretForeignKeys.toMutableSet()
        this@apply.status = this@copy.status
        this@apply.subContactIds = this@copy.subContactIds.toMutableSet()
        this@apply.tags = this@copy.tags
        this@apply.textIndexes = this@copy.textIndexes.map { Pair(it.key, it.value) }.toMap()
        this@apply.valueDate = this@copy.valueDate
    }
}

private fun RegimenItem.AdministrationQuantity.map(): RegimenItemDto.AdministrationQuantity {
    return RegimenItemDto.AdministrationQuantity().apply {
        this@apply.administrationUnit = this@map.administrationUnit.map()
        this@apply.quantity = this@map.quantity
        this@apply.unit = this@map.unit
    }
}

private fun AgreementAppendix.map(): AgreementAppendixDto {
    return AgreementAppendixDto().apply {
        this@apply.docSeq = this@map.docSeq
        this@apply.documentId = this@map.documentId
        this@apply.path = this@map.path
        this@apply.verseSeq = this@map.verseSeq
    }
}

private fun Code.map(): CodeDto {
    return CodeDto().apply {
        this@apply.code = this@map.code
        this@apply.data = this@map.data
        this@apply.flags = this@map.flags.map { it.map() }
        this@apply.label = this@map.label
        this@apply.level = this@map.level
        this@apply.links = this@map.links.toList()
        this@apply.parent = this@map.parent
        this@apply.qualifiedLinks = this@map.qualifiedLinks.map { Pair(it.key, it.value.toMutableList()) }.toMap()
    }
}

private fun CodeDto.copy(): CodeDto {
    return CodeDto().apply {
        this@apply.code = this@copy.code
        this@apply.data = this@copy.data
        this@apply.flags = this@copy.flags.toList()
        this@apply.label = this@copy.label
        this@apply.level = this@copy.level
        this@apply.links = this@copy.links.toList()
        this@apply.parent = this@copy.parent
        this@apply.qualifiedLinks = this@copy.qualifiedLinks.map { Pair(it.key, it.value.toMutableList()) }.toMap()
    }
}

private fun org.taktik.icure.entities.base.CodeFlag.map(): CodeFlag {
    return when (this@map) {
        org.taktik.icure.entities.base.CodeFlag.male_only -> CodeFlag.male_only
        org.taktik.icure.entities.base.CodeFlag.female_only -> CodeFlag.female_only
        else -> null
    } as CodeFlag
}

private fun CodeStub.map(): CodeDto {
    return CodeDto().apply {
        this@apply.code = this@map.code
    }
}

private fun Content.map(): ContentDto {
    return ContentDto().apply {
        this@apply.binaryValue = this@map.binaryValue
        this@apply.booleanValue = this@map.booleanValue
        this@apply.documentId = this@map.documentId
        this@apply.fuzzyDateValue = this@map.fuzzyDateValue
        this@apply.instantValue = this@map.instantValue?.toEpochMilli()
        this@apply.measureValue = this@map.measureValue?.map()
        this@apply.medicationValue = this@map.medicationValue?.map()
        this@apply.numberValue = this@map.numberValue
        this@apply.stringValue = this@map.stringValue
    }
}

private fun ContentDto.copy(): ContentDto {
    return ContentDto().apply {
        this@apply.binaryValue = this@copy.binaryValue
        this@apply.booleanValue = this@copy.booleanValue
        this@apply.documentId = this@copy.documentId
        this@apply.fuzzyDateValue = this@copy.fuzzyDateValue
        this@apply.instantValue = this@copy.instantValue
        this@apply.measureValue = this@copy.measureValue.copy()
        this@apply.medicationValue = this@copy.medicationValue.copy()
        this@apply.numberValue = this@copy.numberValue
        this@apply.stringValue = this@copy.stringValue
    }
}

private fun DelegationDto.copy(): DelegationDto {
    return DelegationDto().apply {
        this@apply.delegatedTo = this@copy.delegatedTo
        this@apply.key = this@copy.key
        this@apply.owner = this@copy.owner
        this@apply.tag = this@copy.tag
    }
}

private fun Delegation.map(): DelegationDto {
    return DelegationDto().apply {
        this@apply.delegatedTo = this@map.delegatedTo
        this@apply.key = this@map.key
        this@apply.owner = this@map.owner
        this@apply.tag = this@map.tags.firstOrNull { !it.isNullOrBlank() } ?: ""
    }
}

private fun Duration.map(): DurationDto {
    return DurationDto().apply {
        this@apply.unit = this@map.unit.map()
        this@apply.value = this@map.value
    }
}

private fun Measure.map(): MeasureDto {
    return MeasureDto().apply {
        this@apply.comment = this@map.comment
        this@apply.max = this@map.max
        this@apply.min = this@map.min
        this@apply.ref = this@map.ref
        this@apply.severity = this@map.severity
        this@apply.unit = this@map.unit
        this@apply.unitCodes = this@map.unitCodes.map { it.map() }.toSet()
        this@apply.value = this@map.value
    }
}

private fun MeasureDto.copy(): MeasureDto {
    return MeasureDto().apply {
        this@apply.comment = this@copy.comment
        this@apply.max = this@copy.max
        this@apply.min = this@copy.min
        this@apply.ref = this@copy.ref
        this@apply.severity = this@copy.severity
        this@apply.unit = this@copy.unit
        this@apply.unitCodes = this@copy.unitCodes
        this@apply.value = this@copy.value
    }
}

private fun Medication.map(): MedicationDto {
    return MedicationDto().apply {
        this@apply.compoundPrescription = this@map.compoundPrescription
        this@apply.substanceProduct = this@map.substanceProduct?.map()
        this@apply.medicinalProduct = this@map.medicinalProduct?.map()
        this@apply.numberOfPackages = this@map.numberOfPackages
        this@apply.batch = this@map.batch
        this@apply.instructionForPatient = this@map.instructionForPatient
        this@apply.commentForDelivery = this@map.commentForDelivery
        this@apply.drugRoute = this@map.drugRoute
        this@apply.temporality = this@map.temporality
        this@apply.duration = this@map.duration?.map()
        this@apply.renewal = this@map.renewal.map()
        this@apply.beginMoment = this@map.beginMoment
        this@apply.endMoment = this@map.endMoment
        this@apply.knownUsage = this@map.knownUsage
        this@apply.frequency = this@map.frequency.map()
        this@apply.reimbursementReason = this@map.reimbursementReason.map()
        this@apply.substitutionAllowed = this@map.substitutionAllowed
        this@apply.regimen = this@map.regimen.map { it.map() }
        this@apply.posology = this@map.posology
        this@apply.options = this@map.options.map { Pair(it.key, it.value.map()) }.toMap()
        this@apply.agreements = this@map.agreements.map { Pair(it.key, it.value.map()) }.toMap()
        this@apply.medicationSchemeIdOnSafe = this@map.medicationSchemeIdOnSafe
        this@apply.medicationSchemeSafeVersion = this@map.medicationSchemeSafeVersion
        this@apply.medicationSchemeTimeStampOnSafe = this@map.medicationSchemeTimeStampOnSafe
        this@apply.medicationSchemeDocumentId = this@map.medicationSchemeDocumentId
        this@apply.safeIdName = this@map.safeIdName
        this@apply.idOnSafes = this@map.idOnSafes
        this@apply.timestampOnSafe = this@map.timestampOnSafe
        this@apply.changeValidated = this@map.changeValidated
        this@apply.newSafeMedication = this@map.newSafeMedication
        this@apply.medicationUse = this@map.medicationUse
        this@apply.beginCondition = this@map.beginCondition
        this@apply.endCondition = this@map.endCondition
        this@apply.origin = this@map.origin
        this@apply.medicationChanged = this@map.medicationChanged
        this@apply.posologyChanged = this@map.posologyChanged
        this@apply.prescriptionRID = this@map.prescriptionRID
    }
}

private fun MedicationDto.copy(): MedicationDto {
    return MedicationDto().apply {
        this@apply.agreements = this@copy.agreements
        this@apply.batch = this@copy.batch
        this@apply.beginCondition = this@copy.beginCondition
        this@apply.beginMoment = this@copy.beginMoment
        this@apply.changeValidated = this@copy.changeValidated
        this@apply.commentForDelivery = this@copy.commentForDelivery
        this@apply.compoundPrescription = this@copy.compoundPrescription
        this@apply.drugRoute = this@copy.drugRoute
        this@apply.duration = this@copy.duration
        this@apply.endCondition = this@copy.endCondition
        this@apply.endMoment = this@copy.endMoment
        this@apply.frequency = this@copy.frequency
        this@apply.idOnSafes = this@copy.idOnSafes
        this@apply.instructionForPatient = this@copy.instructionForPatient
        this@apply.knownUsage = this@copy.knownUsage
        this@apply.medicationChanged = this@copy.medicationChanged
        this@apply.medicationSchemeDocumentId = this@copy.medicationSchemeDocumentId
        this@apply.medicationSchemeIdOnSafe = this@copy.medicationSchemeIdOnSafe
        this@apply.medicationSchemeSafeVersion = this@copy.medicationSchemeSafeVersion
        this@apply.medicationSchemeTimeStampOnSafe = this@copy.medicationSchemeTimeStampOnSafe
        this@apply.medicationUse = this@copy.medicationUse
        this@apply.medicinalProduct = this@copy.medicinalProduct
        this@apply.newSafeMedication = this@copy.newSafeMedication
        this@apply.numberOfPackages = this@copy.numberOfPackages
        this@apply.options = this@copy.options
        this@apply.origin = this@copy.origin
        this@apply.posology = this@copy.posology
        this@apply.posologyChanged = this@copy.posologyChanged
        this@apply.prescriptionRID = this@copy.prescriptionRID
        this@apply.regimen = this@copy.regimen
        this@apply.reimbursementReason = this@copy.reimbursementReason
        this@apply.renewal = this@copy.renewal
        this@apply.safeIdName = this@copy.safeIdName
        this@apply.substanceProduct = this@copy.substanceProduct
        this@apply.substitutionAllowed = this@copy.substitutionAllowed
        this@apply.temporality = this@copy.temporality
        this@apply.timestampOnSafe = this@copy.timestampOnSafe
    }
}

private fun Medicinalproduct.map(): MedicinalproductDto {
    return MedicinalproductDto().apply {
        this@apply.deliveredcds = this@map.deliveredcds.map { it.map() }
        this@apply.deliveredname = this@map.deliveredname
        this@apply.intendedcds = this@map.intendedcds.map { it.map() }
        this@apply.intendedname = this@map.intendedname
    }
}

private fun ParagraphAgreement.map(): ParagraphAgreementDto {
    return ParagraphAgreementDto().apply {
        this@apply.agreementAppendices = this@map.agreementAppendices.map { it.map() }
        this@apply.cancelationDate = this@map.cancelationDate
        this@apply.careProviderReference = this@map.careProviderReference
        this@apply.coverageType = this@map.coverageType
        this@apply.decisionReference = this@map.decisionReference
        this@apply.documentId = this@map.documentId
        this@apply.end = this@map.end
        this@apply.ioRequestReference = this@map.ioRequestReference
        this@apply.paragraph = this@map.paragraph
        this@apply.quantityUnit = this@map.quantityUnit
        this@apply.quantityValue = this@map.quantityValue
        this@apply.refusalJustification = this@map.refusalJustification
        this@apply.responseType = this@map.responseType
        this@apply.start = this@map.start
        this@apply.strength = this@map.strength
        this@apply.strengthUnit = this@map.strengthUnit
        this@apply.timestamp = this@map.timestamp
        this@apply.unitNumber = this@map.unitNumber
        this@apply.verses = this@map.verses
    }
}

private fun RegimenItem.map(): RegimenItemDto {
    return RegimenItemDto().apply {
        this@apply.administratedQuantity = this@map.administratedQuantity.map()
        this@apply.date = this@map.date
        this@apply.dayNumber = this@map.dayNumber
        this@apply.dayPeriod = this@map.dayPeriod.map()
        this@apply.timeOfDay = this@map.timeOfDay
        this@apply.weekday = this@map.weekday.map()
    }
}

private fun Renewal.map(): RenewalDto {
    return RenewalDto().apply {
        this@apply.decimal = this@map.decimal
        this@apply.duration = this@map.duration.map()
    }
}

private fun Substanceproduct.map(): SubstanceproductDto {
    return SubstanceproductDto().apply {
        this@apply.deliveredcds = this@map.deliveredcds.map { it.map() }
        this@apply.deliveredname = this@map.deliveredname
        this@apply.intendedcds = this@map.intendedcds.map { it.map() }
        this@apply.intendedname = this@map.intendedname
    }
}

private fun RegimenItem.Weekday.map(): RegimenItemDto.Weekday {
    return RegimenItemDto.Weekday().apply {
        this@apply.weekNumber = this@map.weekNumber
        this@apply.weekday = this@map.weekday.map()
    }
}

private fun ServiceDto.decrypt(): ServiceDto {
    return this@decrypt.copy().apply {
        this.content = mapOf(Pair("fr", ContentDto().apply {
            stringValue = when {
                this@decrypt.encryptedContent?.length ?: 0 > 0 -> this@decrypt.encryptedContent
                this@decrypt.encryptedSelf?.length ?: 0 > 0 -> this@decrypt.encryptedSelf
                else -> "UNKNOWN ENCRYPTED DATA"
            }
        }))
    }
}
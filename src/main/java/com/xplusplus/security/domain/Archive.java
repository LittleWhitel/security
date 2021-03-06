package com.xplusplus.security.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: zhouweixin
 * @Description: 档案
 * @Date: Created in 19:23 2018/5/7
 * @Modified By:
 */
@Entity
public class Archive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 用户
	 */
	@OneToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	/**
	 * 民族
	 */
	@ManyToOne(targetEntity = Nation.class)
	@JoinColumn(name = "nation_id", referencedColumnName = "id")
	private Nation nation;

	/**
	 * 婚姻状况
	 */
	@ManyToOne(targetEntity = MaritalStatus.class)
	@JoinColumn(name = "marital_status_id", referencedColumnName = "id")
	private MaritalStatus maritalStatus;

	/**
	 * 兵役情况
	 */
	@ManyToOne(targetEntity = MilitaryStatus.class)
	@JoinColumn(name = "military_status_id", referencedColumnName = "id")
	private MilitaryStatus militaryStatus;

	/**
	 * 政治面貌
	 */
	@ManyToOne(targetEntity = PoliticalStatus.class)
	@JoinColumn(name = "political_status_id", referencedColumnName = "id")
	private PoliticalStatus politicalStatus;

	/**
	 * 学历
	 */
	@ManyToOne(targetEntity = Education.class)
	@JoinColumn(name = "education_id", referencedColumnName = "id")
	private Education education;

	/**
	 * 专业
	 */
	private String major;

	/**
	 * 参加工作日期
	 */
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date firstWorkDate;

	/**
	 * 身高
	 */
	@Column(precision = 2)
	private Double height;

	/**
	 * 体重
	 */
	@Column(precision = 2)
	private Double weight;

	/**
	 * 健康状况
	 */
	@ManyToOne(targetEntity = HealthStatus.class)
	@JoinColumn(name = "health_status_id", referencedColumnName = "id")
	private HealthStatus healthStatus;

	/**
	 * 户籍所在地
	 */
	private String domicilePlace;

	/**
	 * 实际居住地
	 */
	private String livePlace;

	/**
	 * 有无保险:0无;1有
	 */
	private Integer insurance;

	/**
	 * 家庭成员名称
	 */
	private String familyMemberName;

	/**
	 * 家庭成员联系方式
	 */
	private String familyMemberContact;

	/**
	 * 身份证号
	 */
	private String identityNumber;

	/**
	 * 身份证扫描件地址
	 */
	private String identityCard;

    /**
     * 是否有保安证：0表示没有；1表示有
     */
	private Integer hasSecuCert = 0;

    /**
     * 保安证号
     */
	private String secuCertNum;

    /**
     * 介绍人名称
     */
	private String sponsorName;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public MilitaryStatus getMilitaryStatus() {
		return militaryStatus;
	}

	public void setMilitaryStatus(MilitaryStatus militaryStatus) {
		this.militaryStatus = militaryStatus;
	}

	public PoliticalStatus getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(PoliticalStatus politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Date getFirstWorkDate() {
		return firstWorkDate;
	}

	public void setFirstWorkDate(Date firstWorkDate) {
		this.firstWorkDate = firstWorkDate;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public HealthStatus getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatus healthStatus) {
		this.healthStatus = healthStatus;
	}

	public String getDomicilePlace() {
		return domicilePlace;
	}

	public void setDomicilePlace(String domicilePlace) {
		this.domicilePlace = domicilePlace;
	}

	public String getLivePlace() {
		return livePlace;
	}

	public void setLivePlace(String livePlace) {
		this.livePlace = livePlace;
	}

	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}

	public String getFamilyMemberName() {
		return familyMemberName;
	}

	public void setFamilyMemberName(String familyMemberName) {
		this.familyMemberName = familyMemberName;
	}

	public String getFamilyMemberContact() {
		return familyMemberContact;
	}

	public void setFamilyMemberContact(String familyMemberContact) {
		this.familyMemberContact = familyMemberContact;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

    public Integer getHasSecuCert() {
        return hasSecuCert;
    }

    public void setHasSecuCert(Integer hasSecuCert) {
        this.hasSecuCert = hasSecuCert;
    }

    public String getSecuCertNum() {
        return secuCertNum;
    }

    public void setSecuCertNum(String secuCertNum) {
        this.secuCertNum = secuCertNum;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    @Override
	public String toString() {
		return "Archive [id=" + id + ", user=" + user + ", nation=" + nation + ", maritalStatus=" + maritalStatus
				+ ", militaryStatus=" + militaryStatus + ", politicalStatus=" + politicalStatus + ", education="
				+ education + ", major=" + major + ", firstWorkDate=" + firstWorkDate + ", height=" + height
				+ ", weight=" + weight + ", healthStatus=" + healthStatus + ", domicilePlace=" + domicilePlace
				+ ", livePlace=" + livePlace + ", insurance=" + insurance + ", familyMemberName=" + familyMemberName
				+ ", familyMemberContact=" + familyMemberContact + ", identityNumber=" + identityNumber
				+ ", identityCard=" + identityCard + "]";
	}
}

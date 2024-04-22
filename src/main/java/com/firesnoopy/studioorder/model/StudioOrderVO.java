package com.firesnoopy.studioorder.model;


import com.firesnoopy.studioinfo.model.StudioInfoVO;
import com.firesnoopy.studiotimebooking.model.StudioTimeBooking;
import com.ren.administrator.model.AdministratorVO;
import com.roger.member.vo.MemberVO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "StudioOrder")
public class StudioOrderVO {
    @Id
    @Column(name = "sOrdNo")
    private Integer sOrdNo;
    @ManyToOne
    @JoinColumn(name = "memNo", referencedColumnName = "memNo")
    private MemberVO member;
    @ManyToOne
    @JoinColumn(name = "sNo", referencedColumnName = "sNo")
    private StudioInfoVO studioInfo;
    @ManyToOne
    @JoinColumn(name = "admNo", referencedColumnName = "admNo")
    private AdministratorVO administrator;
    @Column(name = "bookedDate")
    private Date bookedDate;
    @Column(name = "bookedTimeMorning")
    private Boolean bookedTimeMorning;
    @Column(name = "bookedTimeAfternoon")
    private Boolean bookedTimeAfternoon;
    @Column(name = "bookedTimeNight")
    private Boolean bookedTimeNight;
    @Column(name = "sOrdTime")
    private Timestamp sOrdTime;
    @Column(name = "sOrdStat")
    private Byte sOrdStat;
    @Column(name = "sTtlPrice")
    private BigDecimal sTtlPrice;
    @Column(name = "sDepPrice")
    private BigDecimal sDepPrice;
    @Column(name = "sByrName")
    private String sByrName;
    @Column(name = "sByrPhone")
    private String sByrPhone;
    @Column(name = "sByrEmail")
    private String sByrEmail;
    @Column(name = "sPayMethod")
    private Byte sPayMethod;
    @Column(name = "sPayStat")
    private Byte sPayStat;
    @Column(name = "checkInStat")
    private Boolean checkInStat;
    @Column(name = "sReturnMark")
    private String sReturnMark;
    @Column(name = "sCompensation")
    private BigDecimal sCompensation;
    @OneToMany(mappedBy = "studioOrder", cascade = CascadeType.ALL)
    private Set<StudioTimeBooking> studioTimeBookings;

    public Integer getsOrdNo() {
        return sOrdNo;
    }

    public void setsOrdNo(Integer sOrdNo) {
        this.sOrdNo = sOrdNo;
    }

    public MemberVO getMember() {
        return member;
    }

    public void setMember(MemberVO member) {
        this.member = member;
    }

    public StudioInfoVO getStudioInfo() {
        return studioInfo;
    }

    public void setStudioInfo(StudioInfoVO studioInfo) {
        this.studioInfo = studioInfo;
    }

    public AdministratorVO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorVO administrator) {
        this.administrator = administrator;
    }

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Boolean getBookedTimeMorning() {
        return bookedTimeMorning;
    }

    public void setBookedTimeMorning(Boolean bookedTimeMorning) {
        this.bookedTimeMorning = bookedTimeMorning;
    }

    public Boolean getBookedTimeAfternoon() {
        return bookedTimeAfternoon;
    }

    public void setBookedTimeAfternoon(Boolean bookedTimeAfternoon) {
        this.bookedTimeAfternoon = bookedTimeAfternoon;
    }

    public Boolean getBookedTimeNight() {
        return bookedTimeNight;
    }

    public void setBookedTimeNight(Boolean bookedTimeNight) {
        this.bookedTimeNight = bookedTimeNight;
    }

    public Timestamp getsOrdTime() {
        return sOrdTime;
    }

    public void setsOrdTime(Timestamp sOrdTime) {
        this.sOrdTime = sOrdTime;
    }

    public Byte getsOrdStat() {
        return sOrdStat;
    }

    public void setsOrdStat(Byte sOrdStat) {
        this.sOrdStat = sOrdStat;
    }

    public BigDecimal getsTtlPrice() {
        return sTtlPrice;
    }

    public void setsTtlPrice(BigDecimal sTtlPrice) {
        this.sTtlPrice = sTtlPrice;
    }

    public BigDecimal getsDepPrice() {
        return sDepPrice;
    }

    public void setsDepPrice(BigDecimal sDepPrice) {
        this.sDepPrice = sDepPrice;
    }

    public String getsByrName() {
        return sByrName;
    }

    public void setsByrName(String sByrName) {
        this.sByrName = sByrName;
    }

    public String getsByrPhone() {
        return sByrPhone;
    }

    public void setsByrPhone(String sByrPhone) {
        this.sByrPhone = sByrPhone;
    }

    public String getsByrEmail() {
        return sByrEmail;
    }

    public void setsByrEmail(String sByrEmail) {
        this.sByrEmail = sByrEmail;
    }

    public Byte getsPayMethod() {
        return sPayMethod;
    }

    public void setsPayMethod(Byte sPayMethod) {
        this.sPayMethod = sPayMethod;
    }

    public Byte getsPayStat() {
        return sPayStat;
    }

    public void setsPayStat(Byte sPayStat) {
        this.sPayStat = sPayStat;
    }

    public Boolean getCheckInStat() {
        return checkInStat;
    }

    public void setCheckInStat(Boolean checkInStat) {
        this.checkInStat = checkInStat;
    }

    public String getsReturnMark() {
        return sReturnMark;
    }

    public void setsReturnMark(String sReturnMark) {
        this.sReturnMark = sReturnMark;
    }

    public BigDecimal getsCompensation() {
        return sCompensation;
    }

    public void setsCompensation(BigDecimal sCompensation) {
        this.sCompensation = sCompensation;
    }

    public Set<StudioTimeBooking> getStudioTimeBookings() {
        return studioTimeBookings;
    }

    public void setStudioTimeBookings(Set<StudioTimeBooking> studioTimeBookings) {
        this.studioTimeBookings = studioTimeBookings;
    }
}

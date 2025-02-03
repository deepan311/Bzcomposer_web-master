package com.avibha.bizcomposer.configuration.forms;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

/**
 * @author sarfrazmalik
 */
public class DeductionListDto {

    private static final long serialVersionUID = 0;

    private Long deductionListId;
    private Integer deductionRate, useRate;
    private Integer deductionAmount;
    private String deductionListName;
    private Integer isTaxExempt;
    private String createdAt;

    public Long getDeductionListId() {
        return deductionListId;
    }

    public void setDeductionListId(Long deductionListId) {
        this.deductionListId = deductionListId;
    }

    public Integer getDeductionRate() {
        return deductionRate;
    }

    public void setDeductionRate(Integer deductionRate) {
        this.deductionRate = deductionRate;
    }

    public Integer getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(Integer deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public String getDeductionListName() {
        return deductionListName;
    }

    public void setDeductionListName(String deductionListName) {
        this.deductionListName = deductionListName;
    }

    public Integer getIsTaxExempt() {
        return isTaxExempt;
    }

    public void setIsTaxExempt(Integer isTaxExempt) {
        this.isTaxExempt = isTaxExempt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUseRate(Integer useRate) {
        this.useRate = useRate;
    }

    public Integer getUseRate() {
        return useRate;
    }
}

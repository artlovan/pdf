package org.pdf.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Support will be potentially developed in future releases.
 * For now pdf service won't make any connections to outside DBs.
 */
public class AppContext {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2, message = "State should be two characters representation (e.g. NY)")
    @JsonProperty("dealer_state")
    private String dealerState;

    @NotNull
    @NotEmpty
    @JsonProperty("form_type")
    private String formType;

    @NotNull
    @NotEmpty
    @JsonProperty("lender_id")
    private String lenderId;

    @NotNull
    @NotEmpty
    @JsonProperty("product_type")
    private String productType;

    public AppContext() {
    }

    public AppContext(String dealerState, String formType, String lenderId, String productType) {
        this.dealerState = dealerState;
        this.formType = formType;
        this.lenderId = lenderId;
        this.productType = productType;
    }

    public String getDealerState() {
        return dealerState;
    }

    public void setDealerState(String dealerState) {
        this.dealerState = dealerState;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getLenderId() {
        return lenderId;
    }

    public void setLenderId(String lenderId) {
        this.lenderId = lenderId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "AppContext{" +
                "dealerState='" + dealerState + '\'' +
                ", formType='" + formType + '\'' +
                ", lenderId='" + lenderId + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }
}

package org.pdf.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * Support will be potentially developed in future releases.
 * For now pdf service won't make any connections to outside DBs.
 */
@JsonIgnoreProperties
public class DocCenterContext {
    @JsonProperty("dealjacket_id")
    @NotNull
    @Min(1)
    private Long dealjacketId;

    @JsonProperty("user_code")
    @NotNull
    @Min(1)
    private Integer userCode;

    @NotNull
    @Min(1)
    private Integer dealer_code;

    @NotNull
    @NotEmpty
    private String host;

    @NotNull(message = "port can't be null")
    @Min(value = 1, message = "port has to be a int number")
    private Integer port;

    @JsonProperty("tenant_code")
    @NotNull
    @NotEmpty
    private String tenantCode;

    public Long getDealjacketId() {
        return dealjacketId;
    }

    public void setDealjacketId(Long dealjacketId) {
        this.dealjacketId = dealjacketId;
    }

    public Integer getUserCode() {
        return userCode;
    }

    public void setUserCode(Integer userCode) {
        this.userCode = userCode;
    }

    public Integer getDealer_code() {
        return dealer_code;
    }

    public void setDealer_code(Integer dealer_code) {
        this.dealer_code = dealer_code;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode;
    }

    @Override
    public String toString() {
        return "DocCenterContext{" +
                "dealjacketId=" + dealjacketId +
                ", userCode=" + userCode +
                ", dealer_code=" + dealer_code +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", tenantCode='" + tenantCode + '\'' +
                '}';
    }
}

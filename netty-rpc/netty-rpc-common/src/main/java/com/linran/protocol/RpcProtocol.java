package com.linran.protocol;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.linran.utils.Assert;
import com.linran.utils.JsonUtils;
import com.linran.utils.StringUtils;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.Data;

@Data
public class RpcProtocol implements Serializable {

  private static final long serialVersionUID = 6249905924754041787L;

  private String host;
  private Integer port;
  private List<ServiceInfo> serviceInfos;

  public static RpcProtocol of(
      String host,
      Integer port,
      List<ServiceInfo> serviceInfos
  ) {
    Preconditions.checkArgument(StringUtils.isNotEmpty(host));
    Preconditions.checkArgument(port > 1024);
    RpcProtocol target = new RpcProtocol();
    target.setHost(host);
    target.setPort(port);
    target.setServiceInfos(serviceInfos);
    return target;
  }

  public static RpcProtocol fromJson(String jsonStr) {
    Preconditions.checkArgument(StringUtils.isNotEmpty(jsonStr));
    return JsonUtils.deserialize(jsonStr, RpcProtocol.class);
  }

  public String toJson() {
    return JsonUtils.serialize(this);
  }

  /**
   * 循环引用
   * @return
   */
  @JsonIgnore
  public byte[] getRpcProtocolData() {
    String s = this.toJson();
    Assert.isTrue(StringUtils.isNotEmpty(s), "data not null");
    return s.getBytes(StandardCharsets.UTF_8);
  }

  public RpcProtocol() {
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

  public List<ServiceInfo> getServiceInfos() {
    return serviceInfos;
  }

  public void setServiceInfos(List<ServiceInfo> serviceInfos) {
    this.serviceInfos = serviceInfos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RpcProtocol)) {
      return false;
    }
    RpcProtocol that = (RpcProtocol) o;
    return Objects.equal(getHost(), that.getHost()) && Objects.equal(getPort(), that.getPort()) && Objects
        .equal(getServiceInfos(), that.getServiceInfos());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getHost(), getPort(), getServiceInfos());
  }
}

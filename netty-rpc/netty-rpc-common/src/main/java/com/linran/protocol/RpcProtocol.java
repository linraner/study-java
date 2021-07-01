package com.linran.protocol;


import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.List;

public class RpcProtocol implements Serializable {

  private static final long serialVersionUID = 6249905924754041787L;

  private String host;
  private Integer port;
  private List<ServiceInfo> serviceInfos;


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

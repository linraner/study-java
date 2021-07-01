package com.linran.protocol;

import com.google.common.base.Objects;
import java.io.Serializable;

public class ServiceInfo implements Serializable {

  private static final long serialVersionUID = 6078324345390601573L;

  private String name;
  private String version;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ServiceInfo)) {
      return false;
    }
    ServiceInfo that = (ServiceInfo) o;
    return Objects.equal(getName(), that.getName()) && Objects.equal(getVersion(), that.getVersion());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getName(), getVersion());
  }
}
